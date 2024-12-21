package eu.pm.serdes;

import com.dslplatform.json.CompiledJson;

import java.util.Objects;

/**
 * TODO : comment !
 *
 * @author silviu ilie
 * @since 0.0.1 on feign-dsl-json
 **/
@CompiledJson
public class TestPayload {

    public String name;
    public String value;

    public TestPayload(String name, String value) {
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
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestPayload)) return false;

        TestPayload that = (TestPayload) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "TestPayload{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
