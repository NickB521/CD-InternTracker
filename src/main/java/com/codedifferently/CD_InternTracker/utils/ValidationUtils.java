package com.codedifferently.CD_InternTracker.utils;
import com.codedifferently.CD_InternTracker.models.Intern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ValidationUtils {

    // Method to validate email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // Method to validate if a field is not null or empty
    public static boolean isNotNullOrEmpty(String field) {
        return field != null && !field.trim().isEmpty();
    }

    // Method to validate required fields for Intern (ID as Long)
    public static boolean areRequiredFieldsValid(String email, String name, Long id) {
        return isNotNullOrEmpty(name) && isNotNullOrEmpty(email) && id != null;}


}