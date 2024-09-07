package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.Readable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader implements Readable{
    /**
     *
     * @param cl
     * @param headerLine
     * @param delimiter
     * @return
     * @param <T>
     * @throws IllegalArgumentException
     */
    private static <T> String[] getHeaders(Class<T> cl, String headerLine, String delimiter) throws IllegalArgumentException {
        String[] headers = headerLine.split(delimiter);
        if(HeadersValidator.areValid(cl, headers)) {
            return headers;
        } else {
            throw new IllegalArgumentException("Invalid headers!");
        }
    }

    /**
     *
     * @param filename
     * @param cl
     * @return
     * @param <T>
     */
    @Override
    public <T> List<Map<String, String>> read(String filename, Class<T> cl) {
        String line;
        String delimiter = ",";
        List<Map<String, String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Read and validate the header line
            String headersLine = br.readLine();
            if (headersLine.isEmpty() || headersLine.isBlank()) {
                throw new IOException("CSV file is empty!");
            }

            headersLine = headersLine.replaceAll("\\s", "");
            String[] headers = getHeaders(cl, headersLine, delimiter);

            // Read and process each line of the CSV file
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
            ex.fillInStackTrace();
        }

        return data;
    }
}