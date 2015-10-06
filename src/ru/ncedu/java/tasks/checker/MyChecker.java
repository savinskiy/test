/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.checker;

import java.util.*;
import java.util.regex.*;

/**
 *
 * @author DimaZ
 */
public class MyChecker implements Checker {

    @Override
    public Pattern getPLSQLNamesPattern() {
        String pattern = "^[a-zA-Z][a-zA-z0-9_\\$]{0,29}$";
        return Pattern.compile(pattern);
    }

    @Override
    public Pattern getHrefURLPattern() {
        String pattern = "\"<\\\\s*a\\\\s+href\\\\s*"
                + "=\\\\s*(\\\"[^\\\"]*\\\"|[^\\\"\\\\s]+)\\\\s*/?>\"";
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public Pattern getEMailPattern() {
        String pattern = "^([a-zA-Z0-9][a-zA-Z0-9_.\\-]{0,20}[a-zA-Z0-9]|[a-zA-Z0-9])"
                + "[@]([a-zA-Z0-9]\\.|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9]\\.)+"
                + "(ru|com|net|org)$";
        return Pattern.compile(pattern);
    }

    @Override
    public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
        if (inputString == null && pattern == null) {
            return true;
        }
        return pattern.matcher(inputString).find();
    }

    @Override
    public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
        List<String> result = new ArrayList<String>();
        Matcher m = pattern.matcher(inputString);
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }
}
