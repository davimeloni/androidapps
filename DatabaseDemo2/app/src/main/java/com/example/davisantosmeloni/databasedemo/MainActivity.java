package com.example.davisantosmeloni.databasedemo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (id INTEGER PRIMARY KEY, name VARCHAR, age INT(3))");

            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Raissa', 17)");

            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Julia', 8)");

            myDatabase.execSQL("DELETE FROM newUsers WHERE id = 1");

            Cursor c = myDatabase.rawQuery("SELECT * FROM newUsers", null);

            int idIndex = c.getColumnIndex("id");
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();

             do {

                 Log.i("id", c.getString(idIndex));
                 Log.i("name", c.getString(nameIndex));
                 Log.i("age", Integer.toString(c.getInt(ageIndex)));

                 c.moveToNext();

            } while(c != null);


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
