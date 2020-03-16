package com.example.wizardapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user where uid = 1")
    UserDTO getUser();

    @Update
    int update(UserDTO user);

    @Insert
    void insert(UserDTO user);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

}
