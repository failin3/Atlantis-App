package com.tenf.atlantis;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //private WebView WebView;
    private static String url = "https://pot-atlantis.000webhostapp.com";
    private long alarmTime;

    TabLayout tabLayout;
    SimpleFragmentPagerAdapter adapter;

    public int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);

        adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        String message = "Check of het vuilnis al buiten staat";
        String message2 = "Laatste kans om het vuilnis buiten te zetten";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(MainActivity.this, Receiver.class);
        intent.putExtra("text", message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, intent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
            alarmTime = calendar.getTimeInMillis()+ (am.INTERVAL_DAY*7);
        } else {
            alarmTime = calendar.getTimeInMillis();
        }
        am.setRepeating(am.RTC_WAKEUP, alarmTime, am.INTERVAL_DAY*7, pendingIntent);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent2 = new Intent(MainActivity.this, Receiver.class);
        intent2.putExtra("text", message2);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, 2, intent2, PendingIntent.FLAG_ONE_SHOT);

        if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
            alarmTime = calendar.getTimeInMillis()+ (am.INTERVAL_DAY*7);
        } else {
            alarmTime = calendar.getTimeInMillis();
        }
        am.setRepeating(am.RTC_WAKEUP, alarmTime, am.INTERVAL_DAY*7, pendingIntent2);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (tabLayout.getSelectedTabPosition() == 0) {
                        Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                        boolean canGoBack = ((MainFragment) fragment).canGoBack();
                        if(canGoBack) {
                            ((MainFragment) fragment).goBack();
                        } else {
                            finish();
                        }
                    } else if (tabLayout.getSelectedTabPosition() == 1) {
                        Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                        boolean canGoBack = ((ShoppingFragment) fragment).canGoBack();
                        if(canGoBack) {
                            ((ShoppingFragment) fragment).goBack();
                        } else {
                            finish();
                        }
                    } else if (tabLayout.getSelectedTabPosition() == 2) {
                        Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                        boolean canGoBack = ((AanbiedingsFragment) fragment).canGoBack();
                        if(canGoBack) {
                            ((AanbiedingsFragment) fragment).goBack();
                        } else {
                            finish();
                        }
                    } else if (tabLayout.getSelectedTabPosition() == 3) {
                        Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                        boolean canGoBack = ((TaakjesFragment) fragment).canGoBack();
                        if (canGoBack) {
                            ((TaakjesFragment) fragment).goBack();
                        } else {
                            finish();
                        }
                    } else if (tabLayout.getSelectedTabPosition() == 4) {
                        Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                        boolean canGoBack = ((BierFragment) fragment).canGoBack();
                        if (canGoBack) {
                            ((BierFragment) fragment).goBack();
                        } else {
                            finish();
                        }
                    } else if (tabLayout.getSelectedTabPosition() == 5) {
                        Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                        boolean canGoBack = ((StreepFragment) fragment).canGoBack();
                        if (canGoBack) {
                            ((StreepFragment) fragment).goBack();
                        } else {
                            finish();
                        }
                    }

                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_refresh) {
            if (tabLayout.getSelectedTabPosition() == 0) {
                Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                ((MainFragment) fragment).refresh();
            } else if (tabLayout.getSelectedTabPosition() == 1) {
                Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                ((ShoppingFragment) fragment).refresh();
            } else if (tabLayout.getSelectedTabPosition() == 2) {
                Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                ((AanbiedingsFragment) fragment).refresh();
            } else if (tabLayout.getSelectedTabPosition() == 3) {
                Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                ((TaakjesFragment) fragment).refresh();
            } else if (tabLayout.getSelectedTabPosition() == 4) {
                Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                ((BierFragment) fragment).refresh();
            } else if (tabLayout.getSelectedTabPosition() == 5) {
                Fragment fragment = adapter.getRegisteredFragment(tabLayout.getSelectedTabPosition());
                ((StreepFragment) fragment).refresh();
            }
            final WebView WebView = (WebView)findViewById(R.id.webView);

            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
