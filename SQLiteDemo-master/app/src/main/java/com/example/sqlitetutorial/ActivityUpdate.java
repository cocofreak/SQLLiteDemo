package com.example.sqlitetutorial;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityUpdate extends Activity {
    TextView tvId;
    EditText etName, etCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit);
        tvId = (TextView) findViewById(R.id.tvId);
        etName = (EditText) findViewById(R.id.etName);
        etCourse = (EditText) findViewById(R.id.etCourse);

        Bundle extra = getIntent().getExtras();
        String id = extra.getString("id");
        tvId.setText(id);
        DBase db = new DBase(this);
        db.open();
        String[] rec = db.getRecord(Integer.parseInt(id));
        db.close();
        if(rec[0]!=null){
            etName.setText(rec[0]);
            etCourse.setText(rec[1]);
        } else {
            Dialog d = new Dialog(this);
            d.setTitle("Message");
            TextView tv = new TextView(this);
            tv.setText("There is no record");
            d.setContentView(tv);
            d.show();
        }
    }

    public void doUpdate(View v){
        String name = etName.getText().toString();
        String course = etCourse.getText().toString();
        long id = Integer.parseInt(tvId.getText().toString());
        DBase db = new DBase(this);
        db.open();
        db.update(id, name, course);
        db.close();

        Dialog d = new Dialog(this);
        d.setTitle("Message");
        TextView tv = new TextView(this);
        tv.setText("Update Successful");
        d.setContentView(tv);
        d.show();
    }

    public void doBack(View v){
        finish();
    }
}
