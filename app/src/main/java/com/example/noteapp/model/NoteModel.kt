package com.example.noteapp.model

import android.content.ClipData.Item
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
