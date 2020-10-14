package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class VerificationTelNumber {
    public static boolean checkTelNumber(String telNumber) {
        Pattern pattern = null;
        if (telNumber == null || telNumber.equals(""))
            return false;
        int count = 0;
        for(int i = 0; i < telNumber.length(); i++) {
            if (Character.isDigit(telNumber.charAt(i)))
                count++;
        }
        if (telNumber.charAt(0) == '+' && count == 12) pattern = Pattern.compile("[\\+]{1}\\d+([\\(]{1}[0-9]{3}[\\)]{1})?([\\-]?\\d+[\\-]?)?\\d+$");
        else if((Character.isDigit(telNumber.charAt(0)) && count == 10) || (telNumber.charAt(0) == '(' && count == 10))
            pattern = Pattern.compile("\\d*([\\(]{1}[0-9]{3}[\\)]{1})?([\\-]?\\d+[\\-]?)?\\d+$");
        else return false;

        Matcher matcher = pattern.matcher(telNumber);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("3805012345"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
    }
}
