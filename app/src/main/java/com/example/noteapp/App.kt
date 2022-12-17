package com.example.noteapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.db.daos.AppDatabase
import com.example.noteapp.utils.PreferenceHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        getInstance()
        PreferenceHelper.init(this)
    }

    companion object {
         var appDataBase: AppDatabase? = null
         var context: Context? = null

        fun getInstance(): AppDatabase? {
            if (appDataBase == null) {
                appDataBase = context?.let {
                    Room.databaseBuilder(it, AppDatabase::class.java, "note.database")
                        .fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
            }
            return appDataBase
        }
    }
}