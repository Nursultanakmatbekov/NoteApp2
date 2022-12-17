package com.example.noteapp.model

import android.content.ClipData.Item
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Clock

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val clock: String,
    val data: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
