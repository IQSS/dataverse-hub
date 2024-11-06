package edu.harvard.iq.dataverse_hub.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class RestUtilService {

    public <T> T retrieveRestAPIObject(String url, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonImport = restTemplate.getForObject(url, String.class);
        T object = null;

        ObjectMapper mapper = new ObjectMapper();
        object = mapper.readValue(jsonImport, clazz);

        return object;
    }
}
