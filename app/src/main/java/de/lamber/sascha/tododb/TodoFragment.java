package de.lamber.sascha.tododb;


import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoFragment extends Fragment {

    private Todo aufgabe;
    private EditText todoTitle;
    private CheckBox isDone;
    private TextView todoId;
    private Button deleteButton;

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
       final View view = inflater.inflate(R.layout.fragment_todo, container, false);

        todoTitle = (EditText) view.findViewById(R.id.todoTitle);
        todoTitle.setText(aufgabe.getTitle());
        todoTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                aufgabe.setTitle(s.toString());
                TodoDB.getInstance(view.getContext()).update(aufgabe);
            }
        });

        isDone = (CheckBox) view.findViewById(R.id.isDone);
        isDone.setChecked(aufgabe.isDone());
        isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                aufgabe.setDone(isChecked);
                TodoDB.getInstance(view.getContext()).update(aufgabe);
            }
        });

        todoId = (TextView) view.findViewById(R.id.todoId);
        todoId.setText(String.valueOf(aufgabe.getId()));

        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoDB.getInstance(view.getContext()).delete(aufgabe);
                getActivity().finish();
            }
        });

        return view;
    }

}
