package com.example.project_studentmanagement_android.controller.Singleton;

import com.example.project_studentmanagement_android.Model.User;

import java.util.ArrayList;
import java.util.List;

public class ManagerUser {
    private List<User> lstUser;
    private static ManagerUser instance;

    private ManagerUser() {

    }

    public static ManagerUser getInstance()
    {
        if ( instance == null)
        {
            instance = new ManagerUser();
        }
        return instance;
    }

    public void addUser(String username, String password)
    {
        if (lstUser == null)
        {
            lstUser = new ArrayList<>();
        }
        lstUser.add(new User(null,username,password,null));
    }

    public boolean checklogin(String username, String password){
        for (int i = 0; i < lstUser.size(); i++)
        {
            if ( lstUser.get(i).getUsername().equals(username) && lstUser.get(i).getPassword().equals(password))
                return true;
        }
        return false;
    }

}
