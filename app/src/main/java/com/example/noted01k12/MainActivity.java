package com.example.noted01k12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvNotes;

    private List<Note> dataSource;

    private MyAdapter myAdapter ;

    private CreateNoteDialog createNoteDialog = null;

    private NoteDAO noteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvNotes = findViewById(R.id.lvNotes);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();

        noteDAO = db.getNoteDAO();

        dataSource = noteDAO.getAll();

        myAdapter = new MyAdapter(this, dataSource);

        lvNotes.setAdapter(myAdapter);

        lvNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = dataSource.get(i);
                dataSource.remove(note);
                noteDAO.delete(note);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void showDialog(View view) {
        // Show dialog nhap du lieu
        if(createNoteDialog == null){
            createNoteDialog = new CreateNoteDialog(this) {
                @Override
                protected void sendToMain(Note note) {
                   // Buoc 1-> insert vao Db-> lay id
                    long lastId = noteDAO.insert(note);
                    // set id do cho note
                    note.setId(lastId);
                    // Them vao datasource(ram)
                    dataSource.add(note);

                    myAdapter.notifyDataSetChanged();
                }
            };
        }
        createNoteDialog.show();
    }
}