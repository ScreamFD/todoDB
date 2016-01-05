package de.lamber.sascha.todolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoFragment extends Fragment {

    private Todo aufgabe;
    private TextView todoTitle;
    private CheckBox isDone;

    public TodoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        int todoId = (int)getActivity().getIntent().getSerializableExtra("todoID");

        aufgabe = TodoAdapter.getSingleton(getActivity()).getTodo(todoId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_todo, container, false);

        todoTitle = (TextView) view.findViewById(R.id.todoTitle);
        todoTitle.setText(aufgabe.getTitle());
        isDone = (CheckBox) view.findViewById(R.id.isDone);
        isDone.setChecked(aufgabe.isDone());
        isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                aufgabe.setDone(isChecked);
            }
        });

        return view;
    }

}
