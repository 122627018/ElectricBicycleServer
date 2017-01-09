package com.wxxiaomi.ebs.module.em.comm.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.ResponseWrapper;

public class ResponseUtils {
    public static JsonNode ResponseBodyToJsonNode(ResponseWrapper response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        return jsonNode;
    }
}
