package de.lamber.sascha.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView todoList;

    String[] aufgaben = {
                "Aufgabe #1",
                "Aufgabe #2",
                "Aufgabe #3",
                "Aufgabe #4",
                "Aufgabe #5",
                "Aufgabe #6",
                "Aufgabe #7",
                "Aufgabe #8",
                "Aufgabe #9",
                "Aufgabe #10",
                "Aufgabe #11",
                "Aufgabe #12",
                "Aufgabe #13",
                "Aufgabe #14",
                "Aufgabe #15",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = (ListView) findViewById(R.id.todoList);
        todoList.setAdapter(new ArrayAdapter<String>(this, R.layout.todoeintrag, aufgaben));
    }
}
