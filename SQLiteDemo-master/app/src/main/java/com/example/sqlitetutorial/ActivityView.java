package com.example.sqlitetutorial;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_view);
        TextView tv_id = (TextView) findViewById(R.id.tvId);
        TextView tv_name = (TextView) findViewById(R.id.tvName);
        TextView tv_course = (TextView) findViewById(R.id.tvCourse);
        Bundle extra = getIntent().getExtras();
        String id = extra.getString("id");
        tv_id.setText(id);
        DBase db = new DBase(this);
        db.open();
        String[] rec = db.getRecord(Integer.parseInt(id));
        db.close();
        if(rec[0]!=null){
            tv_name.setText(rec[0]);
            tv_course.setText(rec[1]);
        } else {
            Dialog d = new Dialog(this);
            d.setTitle("Message");
            TextView tv = new TextView(this);
            tv.setText("There is no record");
            d.setContentView(tv);
            d.show();
        }
    }

    public void doBack(View v){
        finish();
    }
}
