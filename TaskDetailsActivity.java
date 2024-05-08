package com.example.taskmanagertask41p;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView dueDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        dueDateTextView = findViewById(R.id.dueDateTextView);

        long taskId = getIntent().getLongExtra("task_id", -1);
        if (taskId != -1) {
            TaskDBHelper dbHelper = new TaskDBHelper(this);
            Task task = dbHelper.getTaskById(taskId);
            if (task != null) {
                titleTextView.setText(task.getTitle());
                descriptionTextView.setText(task.getDescription());
                dueDateTextView.setText(task.getDueDate());
            }
            dbHelper.close();
        }
    }
}