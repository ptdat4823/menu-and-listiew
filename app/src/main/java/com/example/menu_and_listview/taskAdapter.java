package com.example.menu_and_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class taskAdapter extends ArrayAdapter<taskModel> {
    private Context context;

    private final int resourceLayout;


    public taskAdapter(Context context, int resource, List<taskModel> task)
    {
        super(context, resource, task);
        this.context = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            view = vi.inflate(resourceLayout, null);
        }

        taskModel task = getItem(position);
        TextView stt = (TextView) view.findViewById(R.id.stt);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView date = (TextView) view.findViewById(R.id.date);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);

        stt.setText("#" + task.getStt());
        title.setText(task.getTitle());
        date.setText(task.getDate());
        checkBox.setChecked(task.isDone().equals("true"));
        return view;
    }

}
