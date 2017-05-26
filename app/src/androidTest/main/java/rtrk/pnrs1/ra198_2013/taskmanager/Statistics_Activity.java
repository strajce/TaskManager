package rtrk.pnrs1.ra198_2013.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Statistics_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyCanvas canvasView = new MyCanvas(getApplicationContext());
        setContentView(canvasView);
    }
}
