package de.lamber.sascha.tododb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sascha on 07.01.2016.
 */
public class TodoDB {

    public static abstract class TodoItem implements BaseColumns{

        public static final String TABLE_NAME = "todo";
        public static final String COLNAME_TITLE = "title";
        public static final String COLNAME_ISDONE = "isDone";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoItem.TABLE_NAME + " (" +
                    TodoItem._ID + " INTEGER PRIMARY KEY," +
                    TodoItem.COLNAME_TITLE + " TEXT," +
                    TodoItem.COLNAME_ISDONE + " TINYINT(1)" +
            ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TodoItem.TABLE_NAME;

    public class TodoItemDbHelper extends SQLiteOpenHelper{

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Todo.db";

        public TodoItemDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }

    private Context context;
    private static TodoDB myInstance;

    private TodoDB(Context context){
        this.context = context;
    }

    public static TodoDB getInstance(Context context){

        if (myInstance == null){
            myInstance = new TodoDB(context);
        }

        return myInstance;
    }

    public List<Todo> getAll(){

        TodoItemDbHelper helper = new TodoItemDbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String tableColums[] = {TodoItem.COLNAME_TITLE, TodoItem.COLNAME_ISDONE};

        try {

            ArrayList<Todo> result = new ArrayList<>();
            Cursor cursor = db.query(TodoItem.TABLE_NAME,
                    tableColums, null, null, null, null, null);

            try {

                while (cursor.moveToNext()) {
                    Todo tempTodo = new Todo(cursor.getString(0), cursor.getInt(1));
                    result.add(tempTodo);
                }

                return result;

            }finally {
                cursor.close();
            }

        }finally {
            db.close();
        }
    }

    public long insert(String title, boolean isDone){

        TodoItemDbHelper helper = new TodoItemDbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put(TodoItem.COLNAME_TITLE, title);
            contentValues.put(TodoItem.COLNAME_ISDONE, isDone);

            return db.insert(TodoItem.TABLE_NAME, TodoItem.COLNAME_TITLE, contentValues);

        }finally {
            db.close();
        }

    }

    public int update(Todo todo){

        // TODO
        return 0;
    }

    // TODO: delete Todo-Entry
}
