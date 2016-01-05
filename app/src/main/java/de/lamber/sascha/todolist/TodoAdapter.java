package de.lamber.sascha.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sascha on 05.01.2016.
 */
public class TodoAdapter extends RecyclerView.Adapter{

    private static TodoAdapter mySingleton;

    private List<Todo> aufgaben;
    private Activity activity;

    public static TodoAdapter getSingleton(Activity activity){

        if (mySingleton == null){
            mySingleton = new TodoAdapter(activity);
        }

        return mySingleton;
    }

    public Todo getTodo(int id){

        for (Todo todo : aufgaben){
            if (todo.getId() == id){
                return todo;
            }
        }

        return null;
    }

    private static class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Todo currentTodo;
        public TextView todoTitle;
        public CheckBox isDone;

        public TodoViewHolder(View view){

            super(view);
            todoTitle = (TextView) view.findViewById(R.id.todoTitle);
            isDone = (CheckBox) view.findViewById(R.id.isDone);
            view.setOnClickListener(this);
        }

        public void setCurrentTodo(Todo todo){
            currentTodo = todo;
            todoTitle.setText(todo.getTitle());
            isDone.setChecked(todo.isDone());
        }

        @Override
        public void onClick(View v) {
            Intent intent = MainActivity.createTodoIntent(v.getContext(), currentTodo.getId());
            v.getContext().startActivity(intent);
        }
    }

    private TodoAdapter(Activity activity){

        this.activity = activity;

        aufgaben = new ArrayList<Todo>();
        aufgaben.add(new Todo("Aufgabe #1"));
        aufgaben.add(new Todo("Aufgabe #2"));
        aufgaben.add(new Todo("Aufgabe #3"));
        aufgaben.add(new Todo("Aufgabe #4"));
        aufgaben.add(new Todo("Aufgabe #5"));
        aufgaben.add(new Todo("Aufgabe #6"));
        aufgaben.add(new Todo("Aufgabe #7"));
        aufgaben.add(new Todo("Aufgabe #8"));
        aufgaben.add(new Todo("Aufgabe #9"));
        aufgaben.add(new Todo("Aufgabe #10"));
        aufgaben.add(new Todo("Aufgabe #11"));
        aufgaben.add(new Todo("Aufgabe #12"));
        aufgaben.add(new Todo("Aufgabe #13"));
        aufgaben.add(new Todo("Aufgabe #14"));

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = this.activity.getLayoutInflater().inflate(R.layout.komplexerer_eintrag, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Todo aufgabe = aufgaben.get(position);

        TodoViewHolder todoHolder = (TodoViewHolder)holder;
        todoHolder.setCurrentTodo(aufgabe);

    }

    @Override
    public int getItemCount() {
        return aufgaben.size();
    }
}
