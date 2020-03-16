package com.example.wizardapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserDTO {
    @PrimaryKey
    public int uid;
    public String name;
    public String address;
    public long dateOfBirth;
}
