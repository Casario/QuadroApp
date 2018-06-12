package com.quadro.quadroapp;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import static android.content.ContentValues.TAG;

public class quadro_main extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadro_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bluetoothConnection.bluetoothSetup(this, REQUEST_ENABLE_BT);
        bluetoothConnection.connectToRaspberryPi();

        Button send = (Button) findViewById(R.id.button_send);
        Button connect = (Button) findViewById(R.id.button_connect);

        Switch powerSwitch = (Switch) findViewById(R.id.switch_on);
        powerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bluetoothConnection.sendMessageToPi("ON");
                    Log.d(TAG,"msg: POWER ON" );
                } else {
                    bluetoothConnection.sendMessageToPi("OFF");
                    Log.d(TAG,"msg: POWER OFF" );
                    // The toggle is disabled
                }
            }
        });

        Switch startSwitch = (Switch) findViewById(R.id.switch_enablestartseq);
        startSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bluetoothConnection.sendMessageToPi("START ON");
                    Log.d(TAG,"msg: START ON" );
                } else {
                    bluetoothConnection.sendMessageToPi(" START OFF");
                    Log.d(TAG,"msg: START OFF" );
                    // The toggle is disabled
                }
            }
        });

        Switch rotorSwitch = (Switch) findViewById(R.id.switch_enablerotors);
        rotorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bluetoothConnection.sendMessageToPi("ROTORS ON");
                    Log.d(TAG,"msg: ROTORS ON" );
                } else {
                    bluetoothConnection.sendMessageToPi("ROTORS OFF");
                    Log.d(TAG,"msg: ROTORS OFF" );
                    // The toggle is disabled
                }
            }
        });






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Daniel Rapp\t\t\tt15035@hb.dhbw-stuttgart.de\nKevin Sieg\t\t\t\tt15043@hb.dhbw-stuttgart.de", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quadro_main, menu);
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

    public void onConnect(View view){
        bluetoothConnection.connectToRaspberryPi();
    }

    public void onSend(View view){
        bluetoothConnection.test();
    }

}
