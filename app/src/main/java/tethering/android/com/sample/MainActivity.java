package tethering.android.com.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import tethering.android.com.tetheringlibrary.Tether;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = new Gson().toJson(Tether.getTetheringClientsList(this));
        Log.d("isTetheringEnabled",String.valueOf(Tether.isTetheringEnabled(this)));
        Log.d("tetheringDeviceListJson",json);
        Tether.getTetheringClientsList(this).clear();
        for (int i = 0; i < Tether.getTetheringClientsList(this).size(); i++) {
            Log.d("tetheringDeviceList", "ip : " + Tether.getTetheringClientsList(this).get(i).ipAddr + " " + "mac : " + Tether.getTetheringClientsList(this).get(i).hwAddr);
        }
    }
}
