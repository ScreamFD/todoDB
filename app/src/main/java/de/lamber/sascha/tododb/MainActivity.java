package de.lamber.sascha.tododb;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


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

}
