package eu.pm.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class resultsChart {

    public static void main(String[] args) {
        charter.chart(
                jmhResults.load()
        );
    }

    static class jmhResults {
        static ObjectMapper mapper = new ObjectMapper();

        static Object load() {
            try {

                String path = System.getProperty("user.dir") +
                        File.separator +
                        "jmh-result.json";

                Object jmhResult = mapper.readValue(
                        new File(path).toURI().toURL(), Object.class
                );

                return jmhResult;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    static class charter {
        static void chart(Object data) {

            String scoreUnit = "";
            String benchMarkType = "";

            // Create Chart
            CategoryChart chart =
                    new CategoryChartBuilder()
                            .width(1000)
                            .height(600)
                            .title("decoders/decoders -throughput")
                            .theme(Styler.ChartTheme.XChart)
                            .yAxisTitle(scoreUnit)
                            .xAxisTitle(benchMarkType)
                            .build();

            // Customize Chart
            chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
            chart.getStyler().setPlotGridLinesVisible(true);
            chart.getStyler().setAvailableSpaceFill(.96);

            // Histogram histogram1 = new Histogram(getData(10000), 10, -10, 10);
            // Series
            for (Object itemInSeriesObject : ((ArrayList) data)) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) itemInSeriesObject;
                benchMarkType = " iterations : " + linkedHashMap.get("measurementIterations") +
                        ",time : " + linkedHashMap.get("measurementTime");

                String xDataPoint = (String) linkedHashMap.get("measurementTime");
                double absValue = Double.parseDouble(xDataPoint.replace(" ms", ""));

                LinkedHashMap primaryMetric = (LinkedHashMap) linkedHashMap.get("primaryMetric");
                double absYValue = (double) primaryMetric.get("score");
                double absYValueError = (Double) defaultIfNull(primaryMetric.get("scoreError"), "0");

                scoreUnit = (String) primaryMetric.get("scoreUnit");

                chart.addSeries(
                        (String) linkedHashMap.get("benchmark"),
                        new double[]{absValue},
                        new double[]{absYValue},
                        new double[]{absYValueError}
                );
            }

            chart.setYAxisTitle(scoreUnit);
            chart.setXAxisTitle(benchMarkType);

            try {
                BitmapEncoder.saveBitmap(chart, "./jmh-result", BitmapEncoder.BitmapFormat.PNG);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static Double defaultIfNull(Object scoreError, String defaultValue) {
            return (((String) scoreError).equals("NaN") ? 0 : (double) scoreError);
        }
    }

}
