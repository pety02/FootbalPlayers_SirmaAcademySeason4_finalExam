package com.example.footbalplayers_sirmaacademyseason4_finalexam.utils;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;

public class HeadersValidator {
    private static boolean areValidPlayerHeaders(String[] headers) {
        if(headers.length != 5) {
            return false;
        }
        return headers[0].equals("ID") && headers[1].equals("TeamNumber")
                && headers[2].equals("Position") && headers[3].equals("FullName")
                && headers[4].equals("TeamID");
    }

    private static boolean areValidTeamHeaders(String[] headers) {
        if(headers.length != 4) {
            return false;
        }
        return headers[0].equals("ID") && headers[1].equals("Name")
                && headers[2].equals("ManagerFullName") && headers[3].equals("Group");
    }

    private static boolean areValidRecordHeaders(String[] headers) {
        if(headers.length != 5) {
            return false;
        }
        return headers[0].equals("ID") && headers[1].equals("PlayerID")
                && headers[2].equals("MatchID") && headers[3].equals("fromMinutes")
                && headers[4].equals("toMinutes");
    }

    private static boolean areValidMatchHeaders(String[] headers) {
        if(headers.length != 5) {
            return false;
        }
        return headers[0].equals("ID") && headers[1].equals("ATeamID")
                && headers[2].equals("BTeamID") && headers[3].equals("Date")
                && headers[4].equals("Score");
    }

    /**
     *
     * @param cl
     * @param headers
     * @return
     * @param <T>
     */
    public static <T> boolean areValid(Class<T> cl, String[] headers) {
        if(cl == Player.class) {
            return areValidPlayerHeaders(headers);
        } else if (cl == Team.class) {
            return areValidTeamHeaders(headers);
        } else if (cl == Record.class) {
            return areValidRecordHeaders(headers);
        } else if (cl == Math.class) {
            return areValidMatchHeaders(headers);
        } else {
            return false;
        }
    }
}