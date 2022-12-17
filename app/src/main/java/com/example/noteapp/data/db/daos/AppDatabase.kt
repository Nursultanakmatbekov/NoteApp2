package com.example.noteapp.data.db.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.model.NoteModel

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}