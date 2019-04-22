package com.example.pratik.weatherovercast.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pratik.weatherovercast.model.ResObj;

import java.util.List;

@Dao
public interface MyDao {

   /* @Insert
    public void addUser(User user);

    @Query("select * from users")
    public List<User> getUsers();*/

   @Insert
   public void addInfo(ResObj resObj);

   @Query("select * from resobj")
   public List<ResObj> getInfo();

}
