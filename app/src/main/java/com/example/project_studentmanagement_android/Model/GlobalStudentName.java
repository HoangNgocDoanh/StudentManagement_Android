package com.example.project_studentmanagement_android.Model;

public class GlobalStudentName {
    private static String FlagName;

    public static void setFlagName(String flagName) {
        FlagName = flagName;
    }

    public static String getFlagName() {
        return FlagName;
    }
}
