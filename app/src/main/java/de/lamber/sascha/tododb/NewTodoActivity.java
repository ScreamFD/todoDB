package de.lamber.sascha.tododb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class NewTodoActivity extends AppCompatActivity {

    EditText todoTitleText;
    CheckBox todoIsDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        todoTitleText = (EditText) findViewById(R.id.titleText);
        todoIsDone = (CheckBox) findViewById(R.id.isDoneCheckbox);
    }

    public void saveTodo(View view){

        String title = todoTitleText.getText().toString();
        boolean isDone = todoIsDone.isChecked();

        TodoDB.getInstance(view.getContext()).insert(title, isDone);

        // Todo: ?

        finish();
    }
}
