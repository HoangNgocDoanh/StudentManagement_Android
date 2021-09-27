package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.User;
import com.example.project_studentmanagement_android.controller.Singleton.ManagerUser;

import java.util.ArrayList;
import java.util.List;

public class Login_controller extends Database {


    public Login_controller(Context context) {
        super(context);
    }

    //insert
    //get data
    public Cursor GetUser()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    //check username, password
    public boolean checkusernamepassword(String username, String password)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_NAME+" WHERE username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    //check user
    public boolean checkusername(String username)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_NAME+" WHERE username = ?", new String[] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //Update pass
    public boolean updatepass(String username, String pass)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",pass);
        int row = database.update(TABLE_NAME,values,"username = ?",new String[]{username});
        if (row <= 0)
            return false;
        else
            return true;
    }

    public User GetIdUser (String username)
    {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_NAME,null,COL_2 + "= ? ", new String[]{String.valueOf(username)},null,null,null);
        if ( cursor != null)
            cursor.moveToFirst();
        User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return user;
    }


    public List<User> GetListUser() {
        String id_user;
        String username;
        String password;
        String role;
        List<User> LstUer = new ArrayList<User>();

        Cursor data = GetUser();
        data.moveToFirst();
        for(data.moveToFirst();!data.isAfterLast();data.moveToNext()) {
            id_user = data.getString(0);
            username = data.getString(1);
            password = data.getString(2);
            role = data.getString(3);
            LstUer.add( new User(id_user, username, password, role));
        }
        return LstUer;
    }


    public boolean login(String username, String password)
    {
        for (int i = 0; i < GetListUser().size(); i++)
        {
            ManagerUser.getInstance().addUser(GetListUser().get(i).getUsername(), GetListUser().get(i).getPassword());
        }
        return ManagerUser.getInstance().checklogin(username, password);
    }
}
