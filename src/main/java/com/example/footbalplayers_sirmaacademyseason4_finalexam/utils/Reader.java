package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.Readable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.validation.HeadersValidator;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Reader implements Readable{
    /**
     * Splits the headers line by definite delimiter of
     * a CSV file and validates the headers in dependence
     * of which class objects are stored in this file
     * @param cl the class
     * @param headerLine the header line
     * @param delimiter the delimiter symbol
     * @return an array of headers
     * @param <T> template argument that define for which
     * class objects are used these headers
     * @throws IllegalArgumentException this exception is thrown
     * if some of these headers is/are not valid
     */
    private <T> String[] getHeaders(Class<T> cl, String headerLine, String delimiter) throws IllegalArgumentException {
        String[] headers = headerLine.split(delimiter);
        if(HeadersValidator.areValid(cl, headers)) {
            return headers;
        } else {
            throw new IllegalArgumentException("Invalid headers!");
        }
    }

    /**
     * Reads CSV file with definite filename
     * @param filename the filename of the CSV file
     * @param cl the class
     * @return List of Map of String and String that represents
     * all lines of the CSV file
     * @param <T> template argument that define for which
     * class objects are used these headers
     */
    @Override
    public <T> List<Map<String, String>> read(String filename, Class<T> cl) {
        String line;
        String delimiter = ",";
        List<Map<String, String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String headersLine = br.readLine();
            if (headersLine.isEmpty() || headersLine.isBlank()) {
                throw new IOException("CSV file is empty!");
            }

            headersLine = headersLine.replaceAll("\\s", "");
            String[] headers = getHeaders(cl, headersLine, delimiter);

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(delimiter);
                int index = 0;
                for (String field : fields) {
                    field = field.replaceAll("\\s", "");
                    fields[index++] = field;
                }
                Map<String, String> obj = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    obj.put(headers[i], fields[i]);
                }

                data.add(obj);
            }
        } catch (Exception ex) {
            log.error("Exception occurred:" + ex.getMessage());
            return data;
        }

        return data;
    }
}