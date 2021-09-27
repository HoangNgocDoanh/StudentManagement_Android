package com.example.project_studentmanagement_android.Model;

public class SetIdStudent {
    private static String FlagID;

    public static void setFlagID(String flagID) {
        FlagID = flagID;
    }

    public static String getFlagID() {
        return FlagID;
    }

    public static void SetGlobalFlagName(String ID)
    {
        FlagID = ID;
    }
}
