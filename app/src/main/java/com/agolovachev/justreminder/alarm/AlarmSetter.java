package com.agolovachev.justreminder.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.agolovachev.justreminder.database.DBHelper;
import com.agolovachev.justreminder.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 22.09.2017.
 */

public class AlarmSetter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DBHelper dbHelper = new DBHelper(context);

        AlarmHelper.getInstance().init(context);
        AlarmHelper alarmHelper = AlarmHelper.getInstance();

        List<ModelTask> tasks = new ArrayList<>();
        tasks.addAll(dbHelper.query().getTasks(DBHelper.SELECTION_STATUS + " OR "
                + DBHelper.SELECTION_STATUS, new String[]{Integer.toString(ModelTask.STATUS_CURRENT),
                Integer.toString(ModelTask.STATUS_OVERDUE)}, DBHelper.TASK_DATE_COLUMN));

        for (ModelTask task : tasks) {
            if (task.getDate() != 0) {
                alarmHelper.setAlarm(task);
            }
        }

    }
}
