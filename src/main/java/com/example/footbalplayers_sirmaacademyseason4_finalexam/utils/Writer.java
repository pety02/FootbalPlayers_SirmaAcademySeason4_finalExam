package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces.Writable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;

public class Writer implements Writable {
    /**
     *
     * @param bw
     * @param fields
     * @param csvSeparator
     * @throws IOException
     */
    private static void writeLine(BufferedWriter bw, String[] fields, String csvSeparator) throws IOException {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                line.append(csvSeparator);
            }
            line.append(fields[i]);
        }
        bw.write(line.toString());
        bw.newLine();
    }

    /**
     *
     * @param filename
     * @param objs
     * @return
     */
    @Override
    public boolean write(String filename, List<Map<String, String>> objs) {
        String delimiter = ",";
        File file = new File(filename);
        boolean fileExists = file.exists();

        // Check if there is data to write
        if (objs.isEmpty()) {
            System.out.println("No data to write!");
            return false;
        }

        // Get the headers from the first map's keys
        String[] headers = objs.get(0).keySet().toArray(new String[0]);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            // Write headers if the file does not exist
            if (!fileExists) {
                writeLine(bw, headers, delimiter);
            }

            // Write each row of data
            for (Map<String, String> obj : objs) {
                String[] fields = obj.values().toArray(new String[0]);
                writeLine(bw, fields, delimiter);
            }

            return true;
        } catch (IOException e) {
            e.fillInStackTrace();
            return false;
        }
    }
}