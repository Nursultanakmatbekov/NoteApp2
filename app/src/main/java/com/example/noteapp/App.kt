package com.example.noteapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.db.daos.AppDatabase
import com.example.noteapp.utils.PreferenceHelper

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        getInstance()
        val sharedPreferences = PreferenceHelper
        sharedPreferences.init(this)
    }

// Instance room
    companion object{
        private var appDatabase: AppDatabase? = null
        private var context: Context? = null

        fun getInstance(): AppDatabase?{
            if (appDatabase == null){
                appDatabase = context?.let {
                    Room.databaseBuilder(
                        it,AppDatabase::class.java,"note.database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
            }
            return appDatabase
        }
    }
}