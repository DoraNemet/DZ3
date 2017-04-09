package com.ferit.dfundak.fundakdoradz3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private String title;
    private String description;
    private String urgency;

    EditText titleEditText, descriptionEditText;
    RadioGroup urgencyRadioGroup;
    TextView addNewTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        this.titleEditText = (EditText) findViewById(R.id.title_edit_text);
        this.descriptionEditText = (EditText) findViewById(R.id.description_edit_text);
        this.urgencyRadioGroup = (RadioGroup) findViewById(R.id.urgency_radio_group);

        this.addNewTaskBtn = (TextView) findViewById(R.id.new_task_button);

        addNewTaskBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                title = titleEditText.getText().toString();
                description = descriptionEditText.getText().toString();
                switch (urgencyRadioGroup.getCheckedRadioButtonId()){
                    case R.id.highest:
                        urgency = getString(R.string.highest);
                        break;
                    case R.id.middle:
                        urgency = getString(R.string.middle);
                        break;
                    case R.id.lowest:
                        urgency = getString(R.string.lowest);
                        break;
                    default:
                        urgency = getString(R.string.lowest);
                        break;
                }

                if(title.isEmpty())
                    Toast.makeText(AddTaskActivity.this, "Task has to have a title.", Toast.LENGTH_SHORT).show();

               else {
                    Task newTask = new Task(title, description, urgency);


                    TaskDBHelper.getInstance(getApplicationContext()).insertTask(newTask);
                    finish();
                }
            }
        });
    }


}
