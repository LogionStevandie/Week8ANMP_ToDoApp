package id.ac.ubaya.informatika.todoapp.model

import androidx.room.Entity

@Entity(tableName = "todo_table")
data class Todo(
    var title:String,
    var notes:String
)