package com.example.noteapp.data.db.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.model.NoteModel

@Database(entities = [NoteModel::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}