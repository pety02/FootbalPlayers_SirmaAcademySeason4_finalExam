package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils.validation;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;

// In methods below there are /*headers[0].equals("ID") &&*/ comments.
// I commented these pieces of code because they bugs the logic.
// headers[0] is equals to "ID" but the
public class HeadersValidator {
    /**
     * Validates the Player class headers used in a CSV file
     * @param headers the headers that should be validated
     * @return true if the headers are valid and false if not
     */
    private static boolean areValidPlayerHeaders(String[] headers) {
        if(headers.length != 5) {
            return false;
        }
        if(headers[0].startsWith("\uFEFF")) {
            headers[0] = headers[0].replace("\uFEFF", "");
        }
        return headers[0].equals("ID") && headers[1].equals("TeamNumber")
                && headers[2].equals("Position") && headers[3].equals("FullName")
                && headers[4].equals("TeamID");
    }

    /**
     * Validates the Team class headers used in a CSV file
     * @param headers the headers that should be validated
     * @return true if the headers are valid and false if not
     */
    private static boolean areValidTeamHeaders(String[] headers) {
        if(headers.length != 4) {
            return false;
        }
        if(headers[0].startsWith("\uFEFF")) {
            headers[0] = headers[0].replace("\uFEFF", "");
        }
        return headers[0].equals("ID") && headers[1].equals("Name")
                && headers[2].equals("ManagerFullName") && headers[3].equals("Group");
    }

    /**
     * Validates the Record class headers used in a CSV file
     * @param headers the headers that should be validated
     * @return true if the headers are valid and false if not
     */
    private static boolean areValidRecordHeaders(String[] headers) {
        if(headers.length != 5) {
            return false;
        }
        if(headers[0].startsWith("\uFEFF")) {
            headers[0] = headers[0].replace("\uFEFF", "");
        }
        return headers[0].equals("ID") && headers[1].equals("PlayerID")
                && headers[2].equals("MatchID") && headers[3].equals("fromMinutes")
                && headers[4].equals("toMinutes");
    }

    /**
     * Validates the Match class headers used in a CSV file
     * @param headers the headers that should be validated
     * @return true if the headers are valid and false if not
     */
    private static boolean areValidMatchHeaders(String[] headers) {
        if(headers.length != 5) {
            return false;
        }
        if(headers[0].startsWith("\uFEFF")) {
            headers[0] = headers[0].replace("\uFEFF", "");
        }
        return headers[0].equals("ID") && headers[1].equals("ATeamID")
                && headers[2].equals("BTeamID") && headers[3].equals("Date")
                && headers[4].equals("Score");
    }

    /**
     * Validates the headers in dependence of the class
     * @param cl the class
     * @param headers the headers that should be validated
     * @return true id the headers are valid and false if not
     * @param <T> a template argument that defines the type of the class
     */
    public static <T> boolean areValid(Class<T> cl, String[] headers) {
        if(cl == PlayerDTO.class) {
            return areValidPlayerHeaders(headers);
        } else if (cl == TeamDTO.class) {
            return areValidTeamHeaders(headers);
        } else if (cl == RecordDTO.class) {
            return areValidRecordHeaders(headers);
        } else if (cl == MatchDTO.class) {
            return areValidMatchHeaders(headers);
        } else {
            return false;
        }
    }
}