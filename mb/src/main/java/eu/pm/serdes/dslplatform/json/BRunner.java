package eu.pm.serdes.dslplatform.json;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.TimeUnit;

/**
 * TODO : comment !
 *
 * @author silviu ilie
 * @since on main
 **/
public class BRunner {
    public static void main(String[] args) throws RunnerException, RunnerException {
        Options options = new OptionsBuilder()
                .include(DslJsonDecoderService.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
    /*
    public static void main(String[] args) throws Exception {
         URLClassLoader classLoader = (URLClassLoader) BRunner.class.getClassLoader();
        StringBuilder classpath = new StringBuilder();
        for (URL url : classLoader.getURLs()) {
            classpath.append(url.getPath()).append(File.pathSeparator);
        }
        System.setProperty("java.class.path", classpath.toString());


        System.out.println("System.getProperty(\"java.class.path\") = " + System.getProperty("java.class.path"));
        
        org.openjdk.jmh.Main.main(args);
    }*/
}
