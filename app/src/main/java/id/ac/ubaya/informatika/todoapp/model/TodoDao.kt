package id.ac.ubaya.informatika.todoapp.model

import androidx.appcompat.widget.DialogTitle
import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: Todo)

    @Query("SELECT * FROM todo_table WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectAllTodo():List<Todo>

    @Query("SELECT * FROM todo_table WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("UPDATE todo_table SET judul = :title, notes= :notes, priority=:priority WHERE uuid = :uuid")
    suspend fun update(title: String, notes: String, priority: Int, uuid: Int)

    @Query("UPDATE todo_table SET is_done = :is_done WHERE uuid = :uuid")
    suspend fun updateIsDone(is_done: Int, uuid: Int)
}