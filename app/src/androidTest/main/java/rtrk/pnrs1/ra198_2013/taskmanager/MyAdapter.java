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
import android.widget.ArrayAdapter;
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
    private static ArrayList<CharacterTask> mCharactersTask;

    private Date dani;
    private Calendar tmpCalendar;
    private Calendar tmpCalendar2;
    private Calendar tmpCalendar3;

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

    public static ArrayList<CharacterTask> getList(){
        return mCharactersTask;
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
            view.setTag(holder);
        }

        CharacterTask mCharactersTask = (CharacterTask) getItem(position);
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        dani = new Date();

        Button priorityColor = (Button) view.findViewById(R.id.priorityBtn);
        Button remindeColor = (Button) view.findViewById(R.id.btnReminde);
        viewHolder.name.setText(mCharactersTask.getName());
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

        tmpCalendar = Calendar.getInstance();
        tmpCalendar2 = Calendar.getInstance();
        tmpCalendar2.add(Calendar.DAY_OF_YEAR,1);
        tmpCalendar3 = Calendar.getInstance();
        tmpCalendar3.add(Calendar.DAY_OF_YEAR,7);

        if(tmpCalendar.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
            viewHolder.date.setText("Danas");
        }else if(tmpCalendar.get(Calendar.DAY_OF_YEAR) == tmpCalendar2.get(Calendar.DAY_OF_YEAR)){
            viewHolder.date.setText("Sutra");
        }else if(tmpCalendar.get(Calendar.DAY_OF_YEAR) < tmpCalendar3.get(Calendar.DAY_OF_YEAR)){
            switch (tmpCalendar.get(Calendar.DAY_OF_WEEK)){
                case Calendar.MONDAY:
                    viewHolder.date.setText("Ponedeljak");
                    break;
                case Calendar.TUESDAY:
                    viewHolder.date.setText("Utorak");
                    break;
                case Calendar.WEDNESDAY:
                    viewHolder.date.setText("Sreda");
                    break;
                case Calendar.THURSDAY:
                    viewHolder.date.setText("Cetvrtak");
                    break;
                case Calendar.FRIDAY:
                    viewHolder.date.setText("Petak");
                    break;
                case Calendar.SATURDAY:
                    viewHolder.date.setText("Subota");
                    break;
                case Calendar.SUNDAY:
                    viewHolder.date.setText("Nedelja");
                    break;
            }
        }
        else{
            viewHolder.date.setText(tmpCalendar.get(Calendar.DAY_OF_MONTH) + " / " + tmpCalendar.get(Calendar.MONTH) + " / " + tmpCalendar.get(Calendar.YEAR));
        }

        return view;
    }

}
