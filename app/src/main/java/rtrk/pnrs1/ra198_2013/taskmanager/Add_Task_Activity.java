package rtrk.pnrs1.ra198_2013.taskmanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Collection;

//import java.lang.*;

public class Add_Task_Activity extends Activity implements View.OnClickListener {
    private int priority = 0;
    private int msgPriority = 0;
    private int msgReminde = 0;
    private int kliker = 0;
    private int myYear, myMonth, myDay, myHour, myMinute;

    private EditText datePicker;
    private EditText timePicker;
    private EditText TaskName;
    private EditText Discription;

    private Button Red;
    private Button Yellow;
    private Button Green;

    private ImageView datePickerBtn;
    private ImageView timePickerBtn;
    private ImageView AddTask;
    private ImageView Quit;

    private Intent editing;

    private CharacterTask mCharacterTask;

    private CheckBox CheckBox;

    String msgName, msgDate, msgTime, msgDiscription;

    private ArrayAdapter<CharacterTask> mList;

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__task_);


        TaskName = (EditText) findViewById(R.id.NameOfTask);
        Discription = (EditText) findViewById(R.id.Discription);
        datePicker = (EditText) findViewById(R.id.in_date);
        timePicker = (EditText) findViewById(R.id.in_time);
        datePickerBtn = (ImageView) findViewById(R.id.datePickerBtn);
        timePickerBtn = (ImageView) findViewById(R.id.timePickerBtn);
        AddTask = (ImageView) findViewById(R.id.AddingButton);
        Quit = (ImageView) findViewById(R.id.QuitButton);
        Red = (Button) findViewById(R.id.RedButton);
        Yellow = (Button) findViewById(R.id.YellowButton);
        Green = (Button) findViewById(R.id.GreenButton);
        CheckBox = (CheckBox) findViewById(R.id.checkbox);

        AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TaskName.getText().toString().trim().length() > 0 && Discription.getText().toString().trim().length() > 0
                        && datePicker.getText().toString().trim().length() > 0 && timePicker.getText().toString().trim().length() > 0
                        && priority >= 1) {
                    Intent addIntent = new Intent();
                    Bundle extras = new Bundle();
                    msgName = TaskName.getText().toString();
                    //mCharacterTask.setName(TaskName.getText().toString());
                    Log.d(TAG,"NAME");
                    //extras.putString("key_name", "proba");
                    extras.putString("key_name",msgName);
                    msgDate = datePicker.getText().toString();
                    Log.d(TAG,"DATE");
                    extras.putString("key_date", msgDate);
                    msgTime = timePicker.getText().toString();
                    Log.d(TAG,"TIME");
                    extras.putString("key_time", msgTime);
                    msgDiscription = Discription.getText().toString();
                    Log.d(TAG,"DISCRIPTION");
                    extras.putString("key_discription", msgDiscription);
                    extras.putInt("key_priority", msgPriority);
                    extras.putInt("key_reminde",msgReminde);
                    //Vreme
                    //Godina
                    extras.putInt("key_year",myYear);
                    //Mesec
                    extras.putInt("key_month",myMonth);
                    //Dan
                    extras.putInt("key_day",myDay);
                    //Sat
                    extras.putInt("key_hour",myHour);
                    //Minut
                    extras.putInt("key_minute",myMinute);
                    addIntent.putExtras(extras);
                    mCharacterTask = new CharacterTask(msgName,msgDate,priority,msgReminde);
                    Log.d(TAG,"SEND");
                    setResult(RESULT_OK, addIntent);
                    finish();
                } else
                    Toast.makeText(Add_Task_Activity.this, R.string.empty_view, Toast.LENGTH_SHORT).show();
            }
        });
        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quitIntent = new Intent(Add_Task_Activity.this, MainActivity.class);
                startActivity(quitIntent);
                finish();
            }
        });
        Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(priority==0) {
                    Yellow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.DisableYellow));
                    Green.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.DisableGreen));
                    priority=1;
                    msgPriority = 1;
                    Toast.makeText(Add_Task_Activity.this,R.string.high,Toast.LENGTH_SHORT).show();
                }
                else if(priority==1){
                    Yellow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.ColorForYellowButton));
                    Green.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.ColorForGreenButton));
                    priority=0;
                }
            }
        });
        Yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(priority==0){
                    Red.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.DisableRed));
                    Green.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.DisableGreen));
                    priority=2;
                    msgPriority = 2;
                    Toast.makeText(Add_Task_Activity.this,R.string.medium,Toast.LENGTH_SHORT).show();
                }
                else if(priority==2){
                    Red.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.ColorForRedButton));
                    Green.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.ColorForGreenButton));
                    priority=0;
                }
            }
        });
        Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(priority==0) {
                    Red.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.DisableRed));
                    Yellow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.DisableYellow));
                    priority=3;
                    msgPriority = 3;
                    Toast.makeText(Add_Task_Activity.this,R.string.low,Toast.LENGTH_SHORT).show();
                }
                else if(priority==3){
                    Red.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.ColorForRedButton));
                    Yellow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.ColorForYellowButton));
                    priority=0;
                }
            }
        });

        CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kliker == 0) {
                    msgReminde = 1;
                    kliker = 1;
                }else if(kliker == 1){
                    msgReminde = 0;
                    kliker = 0;
                }
            }
        });

        datePicker.setEnabled(false);
        timePicker.setEnabled(false);
        datePicker.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
        timePicker.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
        datePickerBtn.setOnClickListener(this);
        timePickerBtn.setOnClickListener(this);
    }
    //Metoda za vracanje na pocetni Activity aplikacije
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backIntent = new Intent(Add_Task_Activity.this,MainActivity.class);
        startActivity(backIntent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == datePickerBtn) {
            final Calendar c = Calendar.getInstance();
            myYear = c.get(Calendar.YEAR);
            myMonth = c.get(Calendar.MONTH);
            myDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if(year<myYear){
                                Toast.makeText(Add_Task_Activity.this,"Birate godinu koja je prosla!!!",Toast.LENGTH_SHORT).show();
                            }
                            if((year == myYear)&&(monthOfYear<myMonth)){
                                Toast.makeText(Add_Task_Activity.this,"Birate mesec koji je prosao!!!",Toast.LENGTH_SHORT).show();
                            }
                            if ((year == myYear)&&(monthOfYear==myMonth)&&(dayOfMonth<myDay)){
                                Toast.makeText(Add_Task_Activity.this,"Birate dan koji je prosao!!!",Toast.LENGTH_SHORT).show();
                            }

                            datePicker.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);

                        }
                    }, myYear, myMonth, myDay);
            datePickerDialog.show();
        }
        if (v == timePickerBtn) {
            final Calendar c = Calendar.getInstance();
            myHour = c.get(Calendar.HOUR_OF_DAY);
            myMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            timePicker.setText(hourOfDay + " : " + minute);
                        }
                    }, myHour, myMinute, false);
            timePickerDialog.show();
        }


    }



}