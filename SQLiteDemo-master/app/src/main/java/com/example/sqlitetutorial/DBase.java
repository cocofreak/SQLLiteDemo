package com.example.sqlitetutorial;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBase{
    public static final String K_RID = "_id";
    public static final String K_NAME = "name";
    public static final String K_COURSE = "course";
    private static final String DB_NAME = "DBStudRec";
    private static final String DB_TABLE = "tblStudent";
    private static final int DB_VERSION = 1;

    private DBHelper dbHelper;
    private final Context context;
    private SQLiteDatabase dBase;

    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(
                    "CREATE TABLE " + DB_TABLE + " (" +
                            K_RID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            K_NAME + " TEXT NOT NULL, " +
                            K_COURSE + " TEXT NOT NULL);"
            );
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

    //Constructor
    public DBase(Context c){
        context = c;
    }

    //Open database
    public DBase open() throws SQLException{
        dbHelper = new DBHelper(context);
        dBase = dbHelper.getWritableDatabase();
        return this;
    }

    //Close database
    public void close(){
        dbHelper.close();
    }

    //Add student record
    public long add(String _name, String _course) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(K_NAME, _name);
        cv.put(K_COURSE, _course);
        return dBase.insert(DB_TABLE, null, cv);
    }

    //Return all records in String value
    public String getAllRecords() {
        // TODO Auto-generated method stub
        String[] cols = new String[]{K_RID, K_NAME, K_COURSE};
        Cursor c = dBase.query(DB_TABLE, cols, null, null, null, null, null);
        String result = "";
        int indexRow = c.getColumnIndex(K_RID);
        int indexName = c.getColumnIndex(K_NAME);
        int indexCourse = c.getColumnIndex(K_COURSE);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(indexRow) + " "
                    + c.getString(indexName) + " "
                    + c.getString(indexCourse)
                    + "\n";

        }
        return result;
    }

    //return one record(whose values are in array form) based on its id value
    public String[] getRecord(int rid) throws SQLException{
        String selectQuery = "SELECT * FROM "+DB_TABLE+" WHERE "+K_RID+"="+rid;
        Cursor c = null;
        c = dBase.rawQuery(selectQuery, null);
        String[] data = new String[2];
        if(c.moveToFirst()){
            int indexName = c.getColumnIndex(K_NAME);
            int indexCourse = c.getColumnIndex(K_COURSE);
            data[0] = c.getString(indexName);
            data[1] = c.getString(indexCourse);
        }
        return data;
    }

    //update current record
    public void update(long ledit, String name, String course) throws SQLException{
        // TODO Auto-generated method stub
        ContentValues cv= new ContentValues();
        cv.put(K_NAME, name);
        cv.put(K_COURSE, course);
        dBase.update(DB_TABLE, cv, K_RID +"=" + ledit , null);
    }

    //delete record
    public void delete(long id) throws SQLException{
        dBase.delete(DB_TABLE, K_RID+"="+id, null);
    }

}

