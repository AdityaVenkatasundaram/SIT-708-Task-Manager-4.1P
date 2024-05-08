package com.example.taskmanagertask41p;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText, dueDateEditText;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);


        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dueDateEditText = findViewById(R.id.dueDateEditText);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(view -> onSaveButtonClick());
        cancelButton.setOnClickListener(view -> onCancelButtonClick());
    }


    private void onSaveButtonClick() {
        // Retrieve input from EditText fields
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String dueDate = dueDateEditText.getText().toString().trim();

        // Validate input
        if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty()) {

            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        Task task = new Task(-1, title, description, dueDate);


        saveTask(task);


        finish();
    }


    private void onCancelButtonClick() {

        finish();
    }


    private void saveTask(Task task) {
        TaskDBHelper dbHelper = new TaskDBHelper(this);
        long newRowId = dbHelper.insertTask(task.getTitle(), task.getDescription(), task.getDueDate());
        if (newRowId != -1) {
            Toast.makeText(this, "Task saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save task", Toast.LENGTH_SHORT).show();
        }
    }

}
