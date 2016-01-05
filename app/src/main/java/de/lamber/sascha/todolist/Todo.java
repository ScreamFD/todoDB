package de.lamber.sascha.todolist;

/**
 * Created by Sascha on 05.01.2016.
 */
public class Todo {

    private int id;
    private String title;

    public Todo(String title){
        id = (int)(Math.random() * 500000) + 1;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }
}
