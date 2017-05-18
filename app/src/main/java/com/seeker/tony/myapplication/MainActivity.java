package com.seeker.tony.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Test;
import com.seeker.tony.myapplication.annotation.ContentView;
import com.seeker.tony.myapplication.annotation.BindViewMe;
import com.seeker.tony.myapplication.annotation.MeOnClick;
import com.seeker.tony.myapplication.process.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindViewMe(R.id.btn_hello)
    Button btnHello;

    @BindViewMe(R.id.btn_world)
    Button btnWorld;

    String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewInject.inject(this);

//        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String string = intent.getStringExtra("string");
        int intentIntExtra = intent.getIntExtra("int", 0);
        boolean aBoolean = intent.getBooleanExtra("boolean", true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        btnHello.setOnClickListener(this);
//        btnWorld.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Test("test")
    public void test() {

    }

    @MeOnClick({R.id.btn_hello, R.id.btn_world})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_hello:
                Toast.makeText(this, "Btn Hello1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_world:
                Toast.makeText(this, "Btn World1", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
