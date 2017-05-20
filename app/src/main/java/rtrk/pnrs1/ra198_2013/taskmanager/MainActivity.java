package rtrk.pnrs1.ra198_2013.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button NewTask, Statistics, priorityColor, done;
    private MyAdapter adapter;
    private ListView list;
    private CheckBox checkDone;
    String msgName, msgDate, msgTime, msgDiscription;
    private int msgPriority;
    private int msgReminde;
    private static final String TAG = "TAG1";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewTask = (Button) findViewById(R.id.NewTask);
        Statistics = (Button) findViewById(R.id.Statistics);
        priorityColor = (Button) findViewById(R.id.priorityBtn);
        done = (Button) findViewById(R.id.taskDone);

        NewTask.setOnClickListener(this);
        Statistics.setOnClickListener(this);



//        Intent addIntent = this.getIntent();
//        Bundle extras = addIntent.getExtras();
        adapter = new MyAdapter(MainActivity.this,android.R.layout.simple_list_item_1);//imao sam error kad stavim this
        //na netu sam nasao da je getBaseContext() ista stvar sto i this i da probam sa tim

//        if(extras!=null){
//            msgName = extras.getString("key_name");
//            msgDate = extras.getString("key_date");
//            msgTime = extras.getString("key_time");
//            msgDiscription = extras.getString("key_discription");
//            msgPriority = extras.getInt("key_priority");
//            Log.d("Bilo sta :",""+msgPriority);
//            msgCheck = extras.getBoolean("key_check");
//            //adapter.addTask(new CharacterTask(msgName,msgDate, msgPriority, msgCheck));
//        }

        adapter = new MyAdapter(this, android.R.layout.simple_list_item_1);
        list = (ListView) findViewById(R.id.taskovi);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Dugo drzite pritisnite opet.",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent openNewTask = new Intent(this,Add_Task_Activity.class);
        Intent openStatistics = new Intent(this,Statistics_Activity.class);
        switch (v.getId()){
            case R.id.NewTask:
                openNewTask.putExtra("new",1);
                startActivityForResult(openNewTask,1);
//                finish();
                break;
            case R.id.Statistics:
                openStatistics.putExtra("change",1);
                startActivityForResult(openStatistics,1);
//                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
                msgName = data.getExtras().getString("key_name");
                msgDate = data.getExtras().getString("key_date");
                msgPriority = data.getExtras().getInt("key_priority");
                msgReminde = data.getExtras().getInt("key_reminde");

            adapter.addTask(new CharacterTask(msgName, msgDate, msgPriority, msgReminde));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
