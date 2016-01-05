package de.lamber.sascha.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = (ListView) findViewById(R.id.todoList);
        // todoList.setAdapter(new ArrayAdapter<String>(this, R.layout.todoeintrag, aufgaben));
        todoList.setAdapter(new TodoAdapter(this));
    }
}
