package de.lamber.sascha.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    RecyclerView todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = (RecyclerView) findViewById(R.id.todoRecycler);
        todoList.setHasFixedSize(true);
        todoList.setLayoutManager(new LinearLayoutManager(this));

        todoList.setAdapter(TodoAdapter.getSingleton(this));
    }

    public static Intent createTodoIntent(Context context, int id){

        Intent intent = new Intent(context, TodoActivity.class);
        intent.putExtra("todoID", id);

        return intent;
    }
}
