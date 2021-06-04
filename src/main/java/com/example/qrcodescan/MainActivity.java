package com.example.qrcodescan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button sbutton ;
    private TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbutton = findViewById(R.id.QrScan) ;
        textView = findViewById(R.id.tv);

        sbutton.setOnClickListener(v ->{

            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setPrompt("for flash use the volume key ") ;
            intentIntegrator.setBeepEnabled(true);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setCaptureActivity(Caoture.class);

            intentIntegrator.initiateScan();
        } );
        textView.setLinksClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult( requestCode ,resultCode , data);



        if(intentResult.getContents()!= null){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Result");
            builder.setMessage(intentResult.getContents());
            textView.setText(intentResult.getContents());
            builder.setPositiveButton("ok",(d , i )->{
              d.dismiss();
            });

             builder.show();
        }else  {

            Toast.makeText(getApplicationContext() , "ooops something went wrong ..." ,Toast.LENGTH_SHORT ).show();

        }


    }
}