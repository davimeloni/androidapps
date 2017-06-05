package com.example.davisantosmeloni.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //instancia de novo arraylist
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static Set<String> set;
    //static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //seta o ListView do layout para podermos mexer nele no código java
        ListView listView = (ListView) findViewById(R.id.listView);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.davisantosmeloni.notes",
                Context.MODE_PRIVATE);

        set = sharedPreferences.getStringSet("notes", null);

        notes.clear();

        if (set != null) {
            notes.addAll(set);
        } else {
            notes.add("Example Note");
            set = new HashSet<String>();
            set.addAll(notes);
            sharedPreferences.edit().putStringSet("notes", set).apply();
        }

        //cria o arrayAdapter que vai receber o contexto da app, criar o layout e ser populado com
        //um arraylist, que no caso é o notes
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

        //usa o arrayAdapter para passar as infos do listView, pro ArrayAdapter que vai passar
        //para o notes, que é a nossa lista real
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //on click, vai pegar o contexto da aplicação e ir para o EditYourText class
                Intent i = new Intent(getApplicationContext(), EditYourText.class);

                //vai inserir na lista um note, pelo noteId e a posição do noteId
                i.putExtra("noteId", position);
                //e roda a atividade
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.add) {

            notes.add("");

            SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.davisantosmeloni.notes",
                    Context.MODE_PRIVATE);

            if (set == null) {

                set = new HashSet<String>();

            } else {

                set.clear();

            }

            set.addAll(notes);
            sharedPreferences.edit().putStringSet("notes", set).apply();
            Intent i = new Intent(getApplicationContext(), EditYourText.class);
            i.putExtra("noteId", notes.size() -1);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
