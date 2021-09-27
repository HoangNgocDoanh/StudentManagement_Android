package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.Scores;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.ClassesAdapter;

import java.util.List;

public class StudyAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Scores> ScoresList;

    public StudyAdapter(Context context, int layout, List<Scores> scoresList) {
        this.context = context;
        this.layout = layout;
        ScoresList = scoresList;
    }

    @Override
    public int getCount() {
        return ScoresList.size();
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
        TextView txtidstudent, txtscorestudent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudyAdapter.Holder holder = new StudyAdapter.Holder();
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtidstudent = (TextView) convertView.findViewById(R.id.txtIdStudent);
            holder.txtscorestudent = (TextView) convertView.findViewById(R.id.txtScoreStudent);

            convertView.setTag(holder);
        }
        else {
            holder = (StudyAdapter.Holder) convertView.getTag();
        }

        Scores scores = ScoresList.get(position);

        holder.txtidstudent.setText(scores.getId_student());
        holder.txtscorestudent.setText("Midle Score: "+ scores.getMiddle()+"\n"+"Final Score: "+ scores.getEnd());

        return convertView;
    }
}
