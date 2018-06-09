package com.quadro.quadroapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Set;

import static android.content.ContentValues.TAG;

public class bluetoothConnection {

    private static BluetoothAdapter mBluetoothAdapter;
    private static BluetoothDevice mBluetoothDevice;


    public static void bluetoothSetup(AppCompatActivity activity, int request){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent,request);
        }
    }

    public static void connectToRaspberryPi(){

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                Log.d(TAG,"Device: " + device.getName());
                if(device.getName().equals("raspberrypi")){
                    mBluetoothDevice = device;
                }
            }
        }
    }

    public static void test(){
        new MessageThread(mBluetoothDevice, "Test").start();
    }

    public static void sendMessageToPi(String message){
        new MessageThread(mBluetoothDevice, message).start();
    }


}
