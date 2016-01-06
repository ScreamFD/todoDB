package de.lamber.sascha.tododb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Sascha on 05.01.2016.
 */
public class TodoPagerActivity extends FragmentActivity {

    private List<Todo> aufgaben;
    private ViewPager todoPager;

    public static Intent createIntent(Context context, int id){

        Intent intent = new Intent(context, TodoPagerActivity.class);
        intent.putExtra("todoID", id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todopager);

        todoPager = (ViewPager) findViewById(R.id.todoPager);
        aufgaben = TodoAdapter.getSingleton(this).getTodos();

        FragmentManager fragmentManager = getSupportFragmentManager();
        todoPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Todo todo = aufgaben.get(position);
                return TodoFragment.createInstance(todo.getId());
            }

            @Override
            public int getCount() {
                return aufgaben.size();
            }
        });

        int id = (int)getIntent().getSerializableExtra("todoID");

        for (int i = 0; i < aufgaben.size(); i++){

            if (aufgaben.get(i).getId() == id){
                todoPager.setCurrentItem(i);
            }

        }

    }
}
