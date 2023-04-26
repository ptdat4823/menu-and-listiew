package com.example.menu_and_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class edit_task extends AppCompatActivity {

    Button btnSave = null;
    EditText textTitle = null;
    EditText textDesc = null;
    EditText textDate = null;
    CheckBox checkBox = null;
    public String id = "";
    DatePickerDialog datePicker = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        textTitle = (EditText) findViewById(R.id.text_title);
        textDesc = (EditText) findViewById(R.id.text_desc);
        textDate = (EditText) findViewById(R.id.text_date);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        btnSave = (Button) findViewById(R.id.btn_save);

        setInfo();
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Tạo DatePickerDialog và hiển thị nó
                datePicker = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Xử lý khi người dùng chọn ngày xong
                                textDate.setText(String.format("%d/%d/%d",dayOfMonth, monthOfYear + 1,year));
                                datePicker.dismiss();
                            }
                        }, year, month, dayOfMonth);
                datePicker.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Gửi dữ liệu từ ActivityB sang ActivityA
                intent.putExtra("id", id);
                intent.putExtra("title", textTitle.getText().toString());
                intent.putExtra("desc", textDesc.getText().toString());
                intent.putExtra("date", textDate.getText().toString());
                intent.putExtra("isDone", Boolean.toString(checkBox.isChecked()));
                setResult(Activity.RESULT_OK, intent);
                // Kết thúc Activity B và trở về Activity A
                finish();
            }
        });
    }

    private void setInfo()
    {
        taskModel selectedtask = (taskModel) getIntent().getSerializableExtra("selectedtask");
        if(selectedtask != null)
        {
            id = selectedtask.getId();
            textTitle.setText(selectedtask.getTitle());
            textDesc.setText(selectedtask.getDesc());
            textDate.setText(selectedtask.getDate());
            checkBox.setChecked(selectedtask.isDone().equals("true"));
        }
    }
}