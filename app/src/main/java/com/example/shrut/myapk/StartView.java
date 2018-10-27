package com.example.shrut.myapk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class StartView extends AppCompatActivity {

    private ProgressDialog progressDialog;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);


        progressDialog=new ProgressDialog(StartView.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setMessage("Please wait while loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new BackGroundJob().execute();

    }

    private class BackGroundJob extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(9000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.cancel();
            Intent intent=new Intent(StartView.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
