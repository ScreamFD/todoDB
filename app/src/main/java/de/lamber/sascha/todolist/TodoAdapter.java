package de.lamber.sascha.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sascha on 05.01.2016.
 */
public class TodoAdapter extends BaseAdapter{

    private List<Todo> aufgaben;
    private MainActivity activity;

    public TodoAdapter(MainActivity activity){

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
    public int getCount() {
        return aufgaben.size();
    }

    @Override
    public Object getItem(int position) {
        return aufgaben.get(position);
    }

    @Override
    public long getItemId(int position) {
        return aufgaben.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Todo aufgabe = aufgaben.get(position);

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.todoeintrag, parent, false);

        TextView todoTitle = (TextView) row.findViewById(R.id.todoeintrag);
        todoTitle.setText(aufgabe.getTitle());

        return row;
    }
}
