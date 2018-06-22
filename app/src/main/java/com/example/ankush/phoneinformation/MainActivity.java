package com.example.ankush.phoneinformation;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnBuild,btnPhone;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!= TextToSpeech.ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        btnBuild = (Button)findViewById(R.id.btnBuild);
        btnPhone = (Button)findViewById(R.id.btnPhone);

    btnBuild.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String model = Build.MODEL;
            String name = Build.MANUFACTURER;
            String release = Build.VERSION.RELEASE;
            int sdkVersion = Build.VERSION.SDK_INT;
            String msg = model + "\n" + name + "\n" +release+"\n"+sdkVersion;
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
        }
    });

    btnPhone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TelephonyManager tm = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

            String imei = tm.getDeviceId();
            String strphoneType = "";

            int phoneType = tm.getPhoneType();
            if(phoneType == TelephonyManager.PHONE_TYPE_CDMA)
                strphoneType = "CDMA";
            if(phoneType == TelephonyManager.PHONE_TYPE_GSM)
                strphoneType = "GSM";
            if(phoneType == TelephonyManager.PHONE_TYPE_NONE)
                strphoneType = "NONE";

            String carrier = tm.getNetworkOperatorName();

            String msg = imei +"\n"+strphoneType+"\n"+carrier;
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);


        }
    });



    }
}
