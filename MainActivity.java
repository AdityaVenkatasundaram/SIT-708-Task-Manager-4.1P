package com.example.taskmanagertask41p;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskItemClickListener {

    private TaskAdapter adapter;
    private TaskDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Task Manager");

        RecyclerView recyclerView = findViewById(R.id.taskRecyclerView);
        adapter = new TaskAdapter(this); // Pass the activity as the listener
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        dbHelper = new TaskDBHelper(this);

        loadTasksFromDatabase();

        FloatingActionButton addTaskButton = findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(view -> {
            startActivity(new Intent(this, AddEditTaskActivity.class));
        });
    }

    private void loadTasksFromDatabase() {
        List<Task> tasks = dbHelper.getAllTasks();
        adapter.updateTasks(tasks);
    }

    @Override
    public void onTaskItemClick(long taskId) {
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        intent.putExtra("task_id", taskId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}