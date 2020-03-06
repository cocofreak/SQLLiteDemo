package com.example.sqlitetutorial;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAdd extends Activity {
    EditText etName, etCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add);
        etName = (EditText) findViewById(R.id.etName);
        etCourse = (EditText) findViewById(R.id.etCourse);
    }

    public void doAdd(View v){
        String name = etName.getText().toString();
        String course = etCourse.getText().toString();
        DBase db = new DBase(this);
        db.open();
        db.add(name, course);
        db.close();

        Dialog d = new Dialog(this);
        d.setTitle("Message");
        TextView tv = new TextView(this);
        tv.setText(name+" has been added");
        d.setContentView(tv);
        d.show();
    }

    public void doBack(View v){
        finish();
    }
}
