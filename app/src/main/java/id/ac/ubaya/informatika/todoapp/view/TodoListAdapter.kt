package id.ac.ubaya.informatika.todoapp.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.todoapp.R
import id.ac.ubaya.informatika.todoapp.databinding.TodoItemLayoutBinding
import id.ac.ubaya.informatika.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnCLick:(Any) -> Unit, val adapterOnClickUpdate:(Int,Int) ->Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>()
    , TodoCheckedChangeListener, TodoEditClickListener{
    class TodoListViewHolder(var view: TodoItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

    fun updateTodoList(newTodoList:List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout,parent,false)
        return  TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.listener = this

        /*holder.view.checkTask.text = todoList[position].title + " " + todoList[position].priority

        holder.view.imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            //if (isChecked) {
            //    compoundButton.isChecked = false
            //    adapterOnCLick(todoList[position])
            //}
            //compoundButton.isChecked = false
            //adapterOnCLick(todoList[position])
            if (isChecked){
                compoundButton.isChecked = false
                adapterOnClickUpdate(1,todoList[position].uuid)
            }
            else{
                adapterOnClickUpdate(0,todoList[position].uuid)
            }
        }

        holder.view.checkBoxDone.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                compoundButton.isChecked = false
                adapterOnClickUpdate(1,todoList[position].uuid)
            }
            else{
                adapterOnClickUpdate(0,todoList[position].uuid)
            }
        }*/


    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if (isChecked){
            cb.isChecked = false
            adapterOnClickUpdate(1,obj.uuid)
        }
        else{
            adapterOnClickUpdate(0,obj.uuid)
        }
    }

    override fun onTodoEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

}