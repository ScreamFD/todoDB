package de.lamber.sascha.tododb;


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
    private TextView todoId;

    public TodoFragment() {
        // Required empty public constructor
    }

    public static TodoFragment createInstance(int id){

        Bundle args = new Bundle();
        args.putSerializable("todoID", id);

        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        int todoId = (int)getArguments().getSerializable("todoID");
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

        todoId = (TextView) view.findViewById(R.id.todoId);
        todoId.setText(String.valueOf(aufgabe.getId()));

        return view;
    }

}
