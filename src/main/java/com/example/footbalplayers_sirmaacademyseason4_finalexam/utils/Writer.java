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
     * Writes a line in a CSV file via buffered writer, delimiter and fields
     * that should be written in the CSV file
     * @param bw the buffered writer
     * @param fields the array of String that represent the fields
     * @param delimiter the delimiter used to separate the fields in the CSV file
     * @throws IOException this exception is thrown if the buffered writer cannot write
     * because of any occurred issue in the writing process
     */
    private void writeLine(BufferedWriter bw, String[] fields, String delimiter) throws IOException {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                line.append(delimiter);
            }
            line.append(fields[i]);
        }
        bw.write(line.toString());
        bw.newLine();
    }

    /**
     * Writes all objects, represented of List of Map of String and
     * String, in a CSV file with a definite filename
     * @param filename the filename
     * @param objs the List of Map of String and String that represents the objects
     * @return true if the objects are written successfully and false if not
     */
    @Override
    public boolean write(String filename, List<Map<String, String>> objs) {
        String delimiter = ",";
        File file = new File(filename);
        boolean fileExists = file.exists();

        if (objs.isEmpty()) {
            System.out.println("No data to write!");
            return false;
        }

        String[] headers = objs.get(0).keySet().toArray(new String[0]);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            if (!fileExists) {
                writeLine(bw, headers, delimiter);
            }

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