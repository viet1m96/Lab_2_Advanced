package data_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import models.Result;

import java.util.List;

@ApplicationScoped
public class ResultJsonService {
    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    void init() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    public String toJson(Result r) {
        try {
            return mapper.writeValueAsString(r);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Result to JSON", e);
        }
    }

    public String toJson(List<Result> results) {
        try {
            return mapper.writeValueAsString(results);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting List<Result> to JSON", e);
        }
    }

}
