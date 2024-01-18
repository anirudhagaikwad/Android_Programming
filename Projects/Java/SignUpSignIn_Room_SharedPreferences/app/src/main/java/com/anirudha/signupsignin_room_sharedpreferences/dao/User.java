package com.anirudha.signupsignin_room_sharedpreferences.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "user_name")
    public String firstName;

    @ColumnInfo(name = "password")
    public String passwd;
//Constructor
    public User(int uid, String firstName, String passwd) {
        this.uid = uid;
        this.firstName = firstName;
        this.passwd = passwd;
    }
//getters
    public int getUid() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPasswd() {
        return passwd;
    }
 // setters
    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
