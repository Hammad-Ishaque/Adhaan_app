package com.example.usama.adhaan;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

public class prayer extends AppCompatActivity {

    RelativeLayout layout;

    String current;

    long seconds;

    TextView startTime;
    TextView endTime;
    TextView starting;
    TextView ending;
    TextView remainingTime;
    TextView timer;


    LocalTime now;
    LocalTime limit;

    CountDownTimer time;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);

        layout=findViewById(R.id.relative);
        startTime=findViewById(R.id.startTime);
        endTime= findViewById(R.id.endTime);
        starting=findViewById(R.id.starting);
        ending=findViewById(R.id.ending);
        remainingTime= findViewById(R.id.timeRemaining);
        timer=findViewById(R.id.timer);

        current= LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
        String currentP= LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        Bundle extras= getIntent().getExtras();
        int position= extras.getInt("get");
        if(position==0) {
            layout.setBackgroundResource(R.drawable.fajar);

            now=LocalTime.parse(currentP);
            limit=LocalTime.parse("05:32:00");
            seconds= SECONDS.between(now, limit);
            if(seconds<0)
            {
                seconds=seconds+(24*60*60);
            }
            starting.setText(current);
            ending.setText("5:32 AM");
        }
        else if(position==1){
            layout.setBackgroundResource(R.drawable.zuhar);

            now=LocalTime.parse(currentP);
            limit=LocalTime.parse("12:31:00");
            seconds= SECONDS.between(now, limit);
            if(seconds<0)
            {
                seconds=seconds+(24*60*60);
            }
            starting.setText(current);
            ending.setText("00:31 PM");

            startTime.setTextColor(Color.BLACK);
            endTime.setTextColor(Color.BLACK);
            starting.setTextColor(Color.BLACK);
            ending.setTextColor(Color.BLACK);
            remainingTime.setTextColor(Color.BLACK);
            timer.setTextColor(Color.BLACK);
        }
        else if(position==2){
            layout.setBackgroundResource(R.drawable.asr);

            now=LocalTime.parse(currentP);
            limit=LocalTime.parse("15:29:00");
            seconds= SECONDS.between(now, limit);
            if(seconds<0)
            {
                seconds=seconds+(24*60*60);
            }
            starting.setText(current);
            ending.setText("3:29 PM");
        }
        else if(position==3){
            layout.setBackgroundResource(R.drawable.maghrib);

            now=LocalTime.parse(currentP);
            limit=LocalTime.parse("15:49:00");
            seconds= SECONDS.between(now, limit);
            if(seconds<0)
            {
                seconds=seconds+(24*60*60);
            }
            starting.setText(current);
            ending.setText("5:49 PM");
        }
        else if(position==4){
            layout.setBackgroundResource(R.drawable.isha);

            now=LocalTime.parse(currentP);
            limit=LocalTime.parse("19:10:00");
            seconds= SECONDS.between(now, limit);
            if(seconds<0)
            {
                seconds=seconds+(24*60*60);
            }
            starting.setText(current);
            ending.setText("7:10 PM");
        }
        else if(position==5){
            layout.setBackgroundResource(R.drawable.jumma);

            Calendar calendar= Calendar.getInstance();
            int day= calendar.get(Calendar.DAY_OF_WEEK);

            now=LocalTime.parse(currentP);
            limit=LocalTime.parse("12:30:00");
            seconds= SECONDS.between(now, limit);

            if(day== Calendar.THURSDAY)
            {
                seconds=seconds+(24*60*60);

            }
            else if(day==Calendar.FRIDAY)
            {
                if(seconds<0)
                {
                    seconds=0;
                }
            }
            else {
                seconds=0;
            }

            starting.setText(current);
            ending.setText("00:30 PM");

            starting.setText(current);
            startTime.setTextColor(Color.BLACK);
            endTime.setTextColor(Color.BLACK);
            starting.setTextColor(Color.BLACK);
            ending.setTextColor(Color.BLACK);
            remainingTime.setTextColor(Color.BLACK);
            timer.setTextColor(Color.BLACK);
        }
        countdown(seconds*1000);
    }

    public void alarm(){
        AlarmManager manager= (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Calendar cal_alarm = Calendar.getInstance();
        Intent myIntent = new Intent(this, alarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
        manager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
    }

    public void countdown(long millis){
        new CountDownTimer( millis, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)-
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                timer.setText("Its Prayer Time");
                alarm();
            }
        }.start();
    }
}