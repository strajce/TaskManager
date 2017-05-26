package rtrk.pnrs1.ra198_2013.taskmanager;

import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Strahinja on 21.5.2017..
 */

public class TaskTimerClass extends ITaskTimerAidlInterface.Stub {

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void TaskAdded(String taskName) throws RemoteException {

    }

    @Override
    public void TaskRemoved(String taskName) throws RemoteException {

    }

    @Override
    public void TaskUpdated(String taskName) throws RemoteException {

    }
}
