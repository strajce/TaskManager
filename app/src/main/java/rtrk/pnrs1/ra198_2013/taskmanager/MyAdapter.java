package rtrk.pnrs1.ra198_2013.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static rtrk.pnrs1.ra198_2013.taskmanager.R.id;
import static rtrk.pnrs1.ra198_2013.taskmanager.R.layout;


public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<CharacterTask> mCharactersTask;

    private String msgDanas;
    private String msgSutra;
    private int year,month,day,hour,minute;

    public MyAdapter(Context context, int simple_list_item_1){
        mContext = context;
        mCharactersTask = new ArrayList<CharacterTask>();
    }

    public void addTask(CharacterTask mTask){
        mCharactersTask.add(mTask);
        notifyDataSetChanged();
    }

    public void removeTask(int position){
        mCharactersTask.remove(position);
        notifyDataSetChanged();
    }

    public class ViewHolder{
        public int priority = 0;
        public TextView name = null;
        public TextView date = null;
        public CheckBox done = null;
        public int reminde = 0;
    }

    public int getCount(){
        return mCharactersTask.size();
    }

    public  Object getItem(int position){
        Object returnValue = null;
        try {
            returnValue = mCharactersTask.get(position);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return  returnValue;
    }

    public long getItemId(int position){
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout.list_layout,null);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(id.taskName);
            holder.date = (TextView) view.findViewById(id.taskDate);
            holder.done = (CheckBox) view.findViewById(id.taskDone);
//            Button priorityColor = (Button) view.findViewById(R.id.priorityBtn);
//            switch (holder.priority){
//                case 1:
//                    priorityColor.setBackgroundColor(mContext.getResources().getColor(R.color.ColorForRedButton));
//                    break;
//                case 2:
//                    priorityColor.setBackgroundColor(mContext.getResources().getColor(R.color.ColorForYellowButton));
//                    break;
//                case 3:
//                    priorityColor.setBackgroundColor(mContext.getResources().getColor(R.color.ColorForGreenButton));
//                    break;
//            }
            view.setTag(holder);
        }

        CharacterTask mCharactersTask = (CharacterTask) getItem(position);
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        Button priorityColor = (Button) view.findViewById(R.id.priorityBtn);
        Button remindeColor = (Button) view.findViewById(R.id.btnReminde);
        viewHolder.name.setText(mCharactersTask.getName());
        viewHolder.date.setText(mCharactersTask.getDate());
        viewHolder.priority = mCharactersTask.getPriority();
        switch (viewHolder.priority){
            case 1:
                priorityColor.setBackgroundColor(mContext.getResources().getColor(R.color.ColorForRedButton));
                break;
            case 2:
                priorityColor.setBackgroundColor(mContext.getResources().getColor(R.color.ColorForYellowButton));
                break;
            case 3:
                priorityColor.setBackgroundColor(mContext.getResources().getColor(R.color.ColorForGreenButton));
                break;
        }
        viewHolder.reminde = mCharactersTask.getReminde();
        switch (viewHolder.reminde) {
            case 1:
                remindeColor.setBackgroundColor(mContext.getResources().getColor(R.color.ColorForGreenButton));
                break;
            case 0:
                remindeColor.setBackgroundColor(mContext.getResources().getColor(R.color.ColorForRedButton));
                break;
        }

        viewHolder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.name.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.done.setEnabled(false);
            }
        });

        return view;
    }

}
