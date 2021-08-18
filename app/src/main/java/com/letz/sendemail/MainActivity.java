package com.letz.sendemail;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    Button send;
    EditText address, message, subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = findViewById(R.id.etEmail);
        message = findViewById(R.id.etMessage);
        subject = findViewById(R.id.etSubject);
        send = findViewById(R.id.btnSend);

        send.setOnClickListener(v -> {
            String uAddress = address.getText().toString();
            String uMessage = address.getText().toString();
            String uSubject = subject.getText().toString();

            sendEmail(uAddress, uSubject, uMessage);
        });
    }

    public void sendEmail(String userAddress, String userSubject, String userMessage){
        String [] emailAddress = {userAddress};
        //send email with Intent method 사용
        // 다른 앱으로 데이터랑 같이 전송할 수 있다. with Intent
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        //write to mail address section
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        // write to subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, userSubject);
        // wirte to message section
        emailIntent.putExtra(Intent.EXTRA_TEXT,userMessage);

        launchEmail.launch(Intent.createChooser(emailIntent,"email send"));
    }

    ActivityResultLauncher< Intent > launchEmail = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();

                    }
                }
            }
    );

}

