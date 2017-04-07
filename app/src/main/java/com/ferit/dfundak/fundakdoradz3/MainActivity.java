package com.ferit.dfundak.fundakdoradz3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = (ListView) findViewById(R.id.list);

        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                Task newTask = taskAdapter.getItem(position);
                TaskDBHelper.getInstance(getApplicationContext()).removeTask(newTask);
                taskAdapter.remove(position);

                return true;
            }
        });
        loadTasks();
        TextView addNewTaskBtn = (TextView) findViewById(R.id.new_task_button);
        addNewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getApplicationContext(), AddTaskActivity.class);
                startActivity(explicitIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }

    private void loadTasks() {
        ArrayList<Task> tasks = TaskDBHelper.getInstance(this).getAllTasks();
        taskAdapter = new TaskAdapter(tasks);
        listView.setAdapter(taskAdapter);
    }
}
