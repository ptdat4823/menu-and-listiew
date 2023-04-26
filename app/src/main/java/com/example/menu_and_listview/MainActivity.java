package com.example.menu_and_listview;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;
    public List<taskModel> tasks = new ArrayList<taskModel>();
    ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        registerForContextMenu(listView);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String id = data.getStringExtra("id");
                        String title = data.getStringExtra("title");
                        String desc = data.getStringExtra("desc");
                        String date = data.getStringExtra("date");
                        String isDone = data.getStringExtra("isDone");


                        // Xử lý dữ liệu nhận được ở đây
                        if(id.equals(""))
                        {
                            taskModel newTask = new taskModel(title, desc ,date, isDone);
                            tasks.add(newTask);
                        }
                        else
                        {
                            taskModel updatedTask = new taskModel(id, title, desc ,date, isDone);
                            updateTasks(tasks, id, updatedTask);
                        }
                        ResetStt(tasks);
                        listView.setAdapter(new taskAdapter(this, R.layout.custom_listview_item, tasks));
                    }
                });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        taskModel selectedTask = (taskModel) listView.getItemAtPosition(position);

        switch (item.getItemId()) {
            case R.id.option_edit:
                Intent intent = new Intent(this, edit_task.class);
                intent.putExtra("selectedtask", selectedTask);
                activityResultLauncher.launch(intent);
                return true;
            case R.id.option_delete:
                tasks.remove(selectedTask);
                ResetStt(tasks);
                listView.setAdapter(new taskAdapter(this, R.layout.custom_listview_item, tasks));
                return true;
            case R.id.option_mark_as_done:
                selectedTask.setDone("true");
                listView.setAdapter(new taskAdapter(this, R.layout.custom_listview_item, tasks));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.option_new:
            {
                Intent intent = new Intent(this, edit_task.class);
                activityResultLauncher.launch(intent);
                break;
            }
        }
        return true;
    }

    private void updateTasks(List<taskModel> tasks,String id, taskModel updatedTask)
    {
        for (taskModel task : tasks)
        {
            System.out.println(task.getId() + " " + id);
            if(task.getId().equals(id))
            {
                int position = tasks.indexOf(task);
                tasks.remove(task);
                tasks.add(position, updatedTask);
            }
        }
    }

    private void ResetStt(List<taskModel> tasks)
    {
        int i = 0;
        for (taskModel task: tasks) {
            i++;
            task.setStt(String.valueOf(i));
        }
    }
}