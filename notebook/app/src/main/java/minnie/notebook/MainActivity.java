package minnie.notebook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button newNoteBtn;
    ListView noteList;
    databaseActivity mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb=new databaseActivity(this);

        newNoteBtn=(Button)findViewById(R.id.makeAnoteButton);
        newNoteBtnAction();

        noteList=(ListView)findViewById(R.id.listOfNotes);
        setNoteList();
        noteList.getAdapter().getCount();
    }

    public void setNoteList()
    {
        Cursor cursor=mydb.getNotes();
        ArrayList<String> arrayList=new ArrayList<>();

        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                arrayList.add(cursor.getString(0));
            }
        }

        String[] noteArray=new String[arrayList.size()];
        for (int i=0;i<arrayList.size();i++)
        {
            noteArray[i]=arrayList.get(i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_layout, noteArray);
        noteList.setAdapter(arrayAdapter);
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, showNoteActivity.class);
                int arr[] = {position, noteList.getCount()};
                intent.putExtra("Position", arr);
                startActivity(intent);
            }
        });


    }
    public void newNoteBtnAction()
    {
        newNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,newNoteActivity.class);
                startActivity(intent);
                intent.putExtra("EditShow", "-1");
            }
        });
    }

}
