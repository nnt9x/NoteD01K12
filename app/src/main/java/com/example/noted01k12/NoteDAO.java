package com.example.noted01k12;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO  {

    @Query("SELECT * FROM notes")
    List<Note> getAll();

    @Query("SELECT * FROM notes WHERE id = (:id)")
    Note getById(long id);

    @Insert
    long insert(Note note);

    @Delete
    void delete(Note...notes);

    @Update
    void updateAll(Note...notes);
}
