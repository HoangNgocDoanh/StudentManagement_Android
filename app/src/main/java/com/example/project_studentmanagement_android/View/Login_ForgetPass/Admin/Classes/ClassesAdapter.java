package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.R;

import java.util.List;

public class ClassesAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Classes> ClassesLisst;

    public ClassesAdapter(Context context, int layout, List<Classes> classesLisst) {
        this.context = context;
        this.layout = layout;
        ClassesLisst = classesLisst;
    }

    @Override
    public int getCount() {
        return ClassesLisst.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class Holder
    {
        TextView txtNameClass, txtNote;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtNameClass = (TextView) convertView.findViewById(R.id.txtSubjectName);
            holder.txtNote = (TextView) convertView.findViewById(R.id.txtNoteSubject);

            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }

        Classes classes = ClassesLisst.get(position);

        holder.txtNameClass.setText(classes.getClassName());
        holder.txtNote.setText("Số lượng sinh viên: "+ classes.getNumberStudent()+"\n"+"Thời gian học: "+ classes.getTime());

        return convertView;
    }
}
