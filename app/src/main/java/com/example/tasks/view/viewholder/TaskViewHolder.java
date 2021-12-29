package com.example.tasks.view.viewholder;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasks.R;
import com.example.tasks.entities.Task;
import com.example.tasks.service.listener.TaskListener;
import com.example.tasks.service.repository.PriorityRepository;
import com.example.tasks.util.DateHandler;


public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final TaskListener mListener;
    private final PriorityRepository mRepository;

    private final ImageView mImageComplete = itemView.findViewById(R.id.image_complete);
    private final TextView mTextDescription = itemView.findViewById(R.id.text_description);
    private final TextView mTextPriority = itemView.findViewById(R.id.text_priority);
    private final TextView mTextDueDate = itemView.findViewById(R.id.text_duedate);

    public TaskViewHolder(@NonNull View itemView, TaskListener listener) {
        super(itemView);
        this.mListener = listener;
        this.mRepository = new PriorityRepository(itemView.getContext());
    }

    /**
     * Atribui valores aos elementos de interface e também eventos
     * @param task
     */
    public void bindData(final Task task) {
        this.mTextDescription.setText(task.getDescription());
        this.mTextDueDate.setText(DateHandler.format(task.getDueDate()));
        this.mTextPriority.setText(this.mRepository.getDescription(task.getPriorityId()));
        if (task.isComplete()) {
            this.mImageComplete.setImageResource(R.drawable.ic_done);
        } else {
            this.mImageComplete.setImageResource(R.drawable.ic_todo);
        }

        this.mTextDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListClick(task.getId());
            }
        });

        this.mTextDescription.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(itemView.getContext())
                        .setTitle(R.string.task_removal)
                        .setMessage(R.string.remove_task)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 mListener.onDeleteClick(task.getId());
                            }
                        })
                        .setNeutralButton(R.string.cancel, null).show();
                return false;
            }
        });

        this.mImageComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = task.getId();
                if (task.isComplete()) {
                    mListener.onUndoClick(id);
                } else {
                    mListener.onCompleteClick(id);
                }
            }
        });
    }

}
