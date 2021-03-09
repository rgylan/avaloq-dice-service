package com.rgy.dice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MapConverter implements AttributeConverter<Map<Integer, Integer>, String> {

    private final ObjectMapper objectMapper;

    public MapConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(Map<Integer, Integer> attribute) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Override
    public Map<Integer, Integer> convertToEntityAttribute(String dbData) {
        Map<Integer, Integer> map = new HashMap<>();
        try {
            // Work around for H2
            String escapeQuote = dbData.substring(0, 1);
            String unescape = "";
            if (escapeQuote.equals("\"")) {
                unescape = StringEscapeUtils.unescapeJson(dbData);
                unescape = unescape.substring(1, unescape.length() - 1);
            } else {
                unescape = dbData;
            }

            map = objectMapper.readValue(unescape, new TypeReference<Map<Integer, Integer>>() {
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return map;
    }
}
