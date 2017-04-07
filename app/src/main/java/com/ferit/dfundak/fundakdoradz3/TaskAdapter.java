package com.ferit.dfundak.fundakdoradz3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dora on 04/04/2017.
 */

public class TaskAdapter extends BaseAdapter {
    private ArrayList<Task> mTasks;

    public TaskAdapter(ArrayList<Task> tasks) {
        mTasks = tasks;
    }

    @Override
    public int getCount() {
        return this.mTasks.size();
    }

    @Override
    public Task getItem(int position) {
        return this.mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder taskViewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            taskViewHolder = new ViewHolder(convertView);
            convertView.setTag(taskViewHolder);
        } else {
            taskViewHolder = (ViewHolder) convertView.getTag();
        }
        Task task = this.mTasks.get(position);
        taskViewHolder.tvTitle.setText(task.getTitle());
        taskViewHolder.tvDescription.setText(task.getDescription());

        if (task.getUrgency().toString().equals("highest")) {
            taskViewHolder.imageUrgency.setImageResource(R.drawable.red);
        } else if (task.getUrgency().toString().equals("middle")) {
            taskViewHolder.imageUrgency.setImageResource(R.drawable.yellow);
        } else {
            taskViewHolder.imageUrgency.setImageResource(R.drawable.green);
        }

        return convertView;
    }

    public void insert(Task task) {
        this.mTasks.add(task);
        this.notifyDataSetChanged();
    }

    public void remove(int position) {
        this.mTasks.remove(position);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder {
        public TextView tvTitle, tvDescription, tvUrgency;
        public ImageView imageUrgency;

        public ViewHolder(View bookView) {
            tvTitle = (TextView) bookView.findViewById(R.id.title_text_view);
            tvDescription = (TextView) bookView.findViewById(R.id.description_text_view);
            imageUrgency = (ImageView) bookView.findViewById(R.id.image);
        }
    }
}
