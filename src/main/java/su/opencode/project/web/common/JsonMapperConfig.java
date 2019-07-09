package su.opencode.project.web.common;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;

@Component("jacksonObjectMapper")
public class JsonMapperConfig extends ObjectMapper {
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        SerializationConfig serialConfig = getSerializationConfig().with(
                new SimpleDateFormat("dd.MM.yyyy HH:mm"));
        DeserializationConfig deserializationConfig = getDeserializationConfig().with(
                new SimpleDateFormat("dd.MM.yyyy"));
        this.setConfig(serialConfig);
        this.setConfig(deserializationConfig);
    }
}
