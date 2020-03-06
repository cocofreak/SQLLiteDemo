package com.example.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
public class MainActivity extends Activity {

    EditText et_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_id = (EditText) findViewById(R.id.et_id);
    }

    public void doList(View v){
        startActivity(new Intent(this, ActivityList.class));
    }
    public void doAdd(View v){
        startActivity(new Intent(this, ActivityAdd.class));
    }
    public void doView(View v){
        String id = et_id.getText().toString();
        if(!id.isEmpty()){
            Intent i = new Intent(this.getApplicationContext(), ActivityView.class);
            i.putExtra("id", id);
            startActivity(i);
        } else {
            Dialog d = new Dialog(this);
            d.setTitle("Message");
            TextView tv = new TextView(this);
            tv.setText("ID must be provided");
            d.setContentView(tv);
            d.show();
        }
    }

    public void doEdit(View v){
        String id = et_id.getText().toString();
        if(!id.isEmpty()){
            Intent i = new Intent(this.getApplicationContext(), ActivityUpdate.class);
            i.putExtra("id", id);
            startActivity(i);
        } else {
            Dialog d = new Dialog(this);
            d.setTitle("Message");
            TextView tv = new TextView(this);
            tv.setText("ID must be provided");
            d.setContentView(tv);
            d.show();
        }
    }
    public void doDelete(View v){
        String id = et_id.getText().toString();
        if(!id.isEmpty()){
            Intent i = new Intent(this.getApplicationContext(), ActivityDelete.class);
            i.putExtra("id", id);
            startActivity(i);
        } else {
            Dialog d = new Dialog(this);
            d.setTitle("Message");
            TextView tv = new TextView(this);
            tv.setText("ID must be provided");
            d.setContentView(tv);
            d.show();
        }
    }
}
