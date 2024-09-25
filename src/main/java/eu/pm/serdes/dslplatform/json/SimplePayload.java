package eu.pm.serdes.dslplatform.json;

import com.dslplatform.json.CompiledJson;

/**
 * TODO : comment !
 *
 * @author silviu ilie
 * @since 0.0.1 on feign-dsl-json
 **/
//@CompiledJson
public   class SimplePayload {

    String name;
    String value;

    public SimplePayload(String name, String value) {
        this.name = name;
        this.value = value;
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


    @Override
    public String toString() {
        return "SimplePayload{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
