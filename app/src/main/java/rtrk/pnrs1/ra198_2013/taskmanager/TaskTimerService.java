package rtrk.pnrs1.ra198_2013.taskmanager;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Strahinja on 21.5.2017..
 */

/*Koriscen saj za notifikaciju
https://developer.android.com/guide/topics/ui/notifiers/notifications.html
 */

public class TaskTimerService extends Service {

    private ArrayList<CharacterTask> mCharacterTask;
    private TaskTimerClass timer = null;

    private static int NOTIFICATION_ID = 15;

    public TaskTimerService(){
        final Handler handler = new Handler();
        final Runnable thread = new Runnable() {
            @Override
            public void run() {
                mCharacterTask = MyAdapter.getList();
                for(CharacterTask itTask : mCharacterTask){
                    if(itTask.getReminde() == 1){
                        Calendar calendar = Calendar.getInstance();
                        Calendar tempCalendar = Calendar.getInstance();

                        if(calendar.getTimeInMillis() - tempCalendar.getTimeInMillis() < (900*1000)){// 15 Minuta ima 900 sekundi, posto je vreme u milisekundama pomnozim sa 1000
                            //Ako mi istekne tih 15 minuta znaci da treba da posalje notifikaciju
                            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(TaskTimerService.this)
                                    .setContentTitle("Task Manager")
                                    .setContentText(itTask.getName() + R.string.reminder);
                            MainActivity.mNotification.notify(NOTIFICATION_ID,mBuilder.build());
                            itTask.setReminde(0);
                        }
                    }
                }
                handler.postDelayed(this, 60 * 1000);
            }
        };

        handler.postDelayed(thread, 1000);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setContentTitle("Task Manager");
        if(timer == null){
            timer = new TaskTimerClass();
        }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return timer;
    }
}