package co.com.pragma.backend_challenge.user.infrastructure.configuration.feign;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.QueryMapEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FeignQueryBuilder implements QueryMapEncoder {
    @Override
    public Map<String, Object> encode(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(o, new TypeReference<>() {
        });
    }
}