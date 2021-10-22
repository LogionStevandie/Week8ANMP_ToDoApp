package id.ac.ubaya.informatika.todoapp.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.todoapp.R
import id.ac.ubaya.informatika.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnCLick:(Any) -> Unit, val adapterOnClickUpdate:(Int,Int) ->Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(){
    class TodoListViewHolder(var view:View) : RecyclerView.ViewHolder(view)

    fun updateTodoList(newTodoList:List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    fun updateIsDone(done: Int, uuid: Int){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return  TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.view.checkTask.text = todoList[position].title + " " + todoList[position].priority
        if (todoList[position].is_done == 1) holder.view.checkTask.setBackgroundColor(Color.YELLOW)
        else holder.view.checkTask.setBackgroundColor(Color.WHITE)
        holder.view.imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                adapterOnCLick(todoList[position])
            }
            //compoundButton.isChecked = false
            //adapterOnCLick(todoList[position])
        }

        holder.view.checkBoxDone.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                adapterOnClickUpdate(1,todoList[position].uuid)
            }
            else{
                adapterOnClickUpdate(0,todoList[position].uuid)
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

}