package com.example.tasks.view.viewholder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasks.R;
import com.example.tasks.entities.Task;
import com.example.tasks.service.listener.TaskListener;
import com.example.tasks.service.repository.PriorityRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final SimpleDateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
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
    public void bindData(Task task) {
        this.mTextDescription.setText(task.getDescription());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(task.getDueDate());
            if (date != null) {
                this.mTextDueDate.setText(mDateFormat.format(date));
            }
        } catch (ParseException e) {
            this.mTextDueDate.setText("--");
        }
        this.mTextPriority.setText(this.mRepository.getDescription(task.getPriorityId()));
        if (task.getComplete()) {
            this.mImageComplete.setImageResource(R.drawable.ic_done);
        } else {
            this.mImageComplete.setImageResource(R.drawable.ic_todo);

        }
        //this.mTextPriority.setText(task.get);
        /*
        new AlertDialog.Builder(itemView.getContext())
                .setTitle(R.string.remocao_de_tarefa)
                .setMessage(R.string.remover_tarefa)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // mListener.onDeleteClick(task.id);
                    }
                })
                .setNeutralButton(R.string.cancelar, null).show();*/


    }

}
