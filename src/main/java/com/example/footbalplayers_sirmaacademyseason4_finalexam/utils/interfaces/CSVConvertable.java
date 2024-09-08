package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.interfaces;

import java.util.List;
import java.util.Map;
public interface CSVConvertable<T> {
    List<T> convertToListOfModel(List<Map<String, String>> data);
    List<Map<String, String>> convertToCSV(List<T> objs);
}