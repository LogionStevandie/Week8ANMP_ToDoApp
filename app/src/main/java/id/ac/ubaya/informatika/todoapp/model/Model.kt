package id.ac.ubaya.informatika.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @ColumnInfo(name = "judul")
    var title:String,
    @ColumnInfo(name = "notes")
    var notes:String,
    @ColumnInfo(name = "priority")
    var priority:Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}