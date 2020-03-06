package com.example.sqlitetutorial;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityList extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list);
        TextView tv_list = (TextView) findViewById(R.id.tvStudentList);
        DBase db = new DBase(this);
        db.open();
        String recs = db.getAllRecords();
        db.close();

        if(recs.isEmpty()){
            Dialog d = new Dialog(this);
            d.setTitle("Message");
            TextView tv = new TextView(this);
            tv.setText("There are no records");
            d.setContentView(tv);
            d.show();
        } else {
            tv_list.setText(recs);
        }
    }

    public void doBack(View v){

        finish();
    }
}
