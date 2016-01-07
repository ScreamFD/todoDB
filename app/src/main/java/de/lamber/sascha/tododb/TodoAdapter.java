package de.lamber.sascha.tododb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

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

    public List<Todo> getTodos(){
        return aufgaben;
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
            Intent intent = TodoPagerActivity.createIntent(v.getContext(), currentTodo.getId());
            v.getContext().startActivity(intent);
        }
    }

    private TodoAdapter(Activity activity){

        this.activity = activity;

        reload();
    }

    public void reload(){
        TodoDB todoDB = TodoDB.getInstance(activity.getApplicationContext());
        aufgaben = todoDB.getAll();
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
