package com.ferit.dfundak.fundakdoradz3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Dora on 04/04/2017.
 */

public class TaskDBHelper extends SQLiteOpenHelper {

    private static TaskDBHelper mTaskDBHelper = null;

    public TaskDBHelper(Context context) {
        super(context.getApplicationContext(), Schema.DATABASE_NAME, null, Schema.SCHEMA_VERSION);
    }

    public static synchronized TaskDBHelper getInstance(Context context){
        if(mTaskDBHelper == null){
            mTaskDBHelper = new TaskDBHelper(context);
        }
        return mTaskDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE_TASKS);
        this.onCreate(db);
    }

    //SQL statements
    static final String CREATE_TABLE_TASKS = "CREATE TABLE " + Schema.TABLE_TASKS + " (" + Schema.TITLE + " TEXT," + Schema.DESCRIPTION + " TEXT," + Schema.URGENCY + " TEXT);";
    static final String DROP_TABLE_TASKS = "DROP TABLE IF EXISTS " + Schema.TABLE_TASKS;
    static final String SELECT_ALL_TASKS = "SELECT " + Schema.TITLE + "," + Schema.DESCRIPTION + "," + Schema.URGENCY + " FROM " + Schema.TABLE_TASKS;

    public void insertTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.TITLE, task.getTitle());
        contentValues.put(Schema.DESCRIPTION, task.getDescription());
        contentValues.put(Schema.URGENCY, task.getUrgency());
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.insert(Schema.TABLE_TASKS, null ,contentValues);
        writableDatabase.close();
    }

    public ArrayList<Task> getAllTasks(){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor tasksCursor = writableDatabase.rawQuery(SELECT_ALL_TASKS,null);
        ArrayList<Task> tasks = new ArrayList<>();
        if(tasksCursor.moveToFirst()){
            do{
                String title = tasksCursor.getString(0);
                String description = tasksCursor.getString(1);
                String urgency = tasksCursor.getString(2);
                tasks.add(new Task(title, description, urgency));
            }while(tasksCursor.moveToNext());
        }
        tasksCursor.close();
        writableDatabase.close();
        return tasks;
    }

    public static class Schema{
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "tasks.db";
        static final String TABLE_TASKS = "my_books";
        static final String DESCRIPTION = "description";
        static final String TITLE = "title";
        static final String URGENCY = "urgency";
    }
}
