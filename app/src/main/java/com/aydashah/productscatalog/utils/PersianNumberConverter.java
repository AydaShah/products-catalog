package com.aydashah.productscatalog.utils;

public class PersianNumberConverter {

    public static String convertToPersianFromString(String number) {
        String persianNumber = "";
        for (int i = 0; i < number.length(); i++) {
            String numberChar = number.substring(i, i + 1);
            persianNumber = persianNumber + convertEnglishNumbersToPersian(numberChar);
        }
        return persianNumber;
    }

    public static String convertToEngNumber(String persianNumber) {
        String englishNumber = "";
        for (int i = 0; i < persianNumber.length(); i++) {
            String numberChar = persianNumber.substring(i, i + 1);
            englishNumber = englishNumber + convertPersianNumbersToEnglish(numberChar);
        }
        return englishNumber;
    }

    public static String convertToPersianFromInteger(int number) {
        String persianNumber = "";
        String numberString = String.valueOf(number);
        for (int i = 0; i < numberString.length(); i++) {
            String numberChar = numberString.substring(i, i + 1);
            persianNumber = persianNumber + convertEnglishNumbersToPersian(numberChar);
        }
        return persianNumber;
    }

    private static String convertEnglishNumbersToPersian(String number) {
        switch (number) {
            case "0":
                return "۰";
            case "1":
                return "۱";
            case "2":
                return "۲";
            case "3":
                return "۳";
            case "4":
                return "۴";
            case "5":
                return "۵";
            case "6":
                return "۶";
            case "7":
                return "۷";
            case "8":
                return "۸";
            case "9":
                return "۹";
            case "?":
                return "؟";

            default:
                return number;
        }
    }

    private static String convertPersianNumbersToEnglish(String number) {
        switch (number) {
            case "۰":
                return "0";
            case "۱":
                return "1";
            case "۲":
                return "2";
            case "۳":
                return "3";
            case "۴":
                return "4";
            case "۵":
                return "5";
            case "۶":
                return "6";
            case "۷":
                return "7";
            case "۸":
                return "8";
            case "۹":
                return "9";

            default:
                return number;
        }
    }

    public static String separateBy3(int i){
        return String.format("%,d", Long.parseLong(i+""));
    }

    public static String separateBy3(long i) {
        return String.format("%,d", Long.parseLong(i+""));
    }

    public static String separateBy3(String S){
        try {
            return String.format("%,d", Long.parseLong(S));
        } catch (Exception e) {
            return S;
        }
    }
    public static String separateCurrencyBy3(String S){
        try {
            return String.format("%,d", Long.parseLong(S));
        } catch (Exception e) {
            return S;
        }
    }

}