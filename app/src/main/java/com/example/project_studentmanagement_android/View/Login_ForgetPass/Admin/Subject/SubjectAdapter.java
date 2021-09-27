package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.R;


import java.util.List;

public class SubjectAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Subject> subjectLst;

    public SubjectAdapter(Context context, int layout, List<Subject> lst) {
        this.context = context;
        this.layout = layout;
        this.subjectLst = lst;
    }

    @Override
    public int getCount() {
        return subjectLst.size();
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
        TextView txtNamesubject, txtNote;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtNamesubject = (TextView) convertView.findViewById(R.id.txtSubjectName);
            holder.txtNote = (TextView) convertView.findViewById(R.id.txtNoteSubject);

            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }

        Subject subject = subjectLst.get(position);

        holder.txtNamesubject.setText(subject.getSubjectName());
        holder.txtNote.setText("Số lượng lớp: "+ subject.getNumberClass()+"\n"+"số lượng lớp tối đa: "+ subject.getMaxClass());

        return convertView;
    }
}
