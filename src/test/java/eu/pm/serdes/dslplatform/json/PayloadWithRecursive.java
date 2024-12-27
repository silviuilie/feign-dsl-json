package eu.pm.serdes.dslplatform.json;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 * @author silviu ilie
 * @since 1.0-SNAPSHOT
 **/
//@CompiledJson
public class PayloadWithRecursive {

    String name;
    String value;

    private List<PayloadWithRecursive> list = new ArrayList<PayloadWithRecursive>(10);


    public PayloadWithRecursive() {
    }

    public PayloadWithRecursive(String name, String value) {
        this.name = name;
        this.value = value;

    }

    public PayloadWithRecursive(String name, String value, List<PayloadWithRecursive> list) {
        this.name = name;
        this.value = value;
        if (null != list) {
            this.list = list;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<PayloadWithRecursive> getList() {
        return list;
    }

    public void setList(List<PayloadWithRecursive> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
