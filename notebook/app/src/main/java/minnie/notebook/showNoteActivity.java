package minnie.notebook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class showNoteActivity extends AppCompatActivity {

    Button editBtn,deleteBtn;
    TextView showNote;
    databaseActivity mydb;
    ListView list;
    int listMember;
    int arr[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mydb=new databaseActivity(this);
        editBtn=(Button)findViewById(R.id.editNoteButton);
        deleteBtn=(Button)findViewById(R.id.DeleteButton);
        showNote=(TextView)findViewById(R.id.showNoteTextView);
        arr=getIntent().getIntArrayExtra("Position");

        setNote();
        editButtonClick();
        deleteButtonClick();

    }

    private void setNote() {
        Cursor cursor=mydb.getNotes();
        String note=null;
        if(cursor.getCount()>0) {

            cursor.moveToPosition(arr[0]);
            //  cursor.moveToPosition(getIntent().getIntExtra("Position",0));
            note=cursor.getString(0);
        }

        showNote.setText(note);

    }

    public void editButtonClick()
    {
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(showNoteActivity.this,editActivity.class);
                startActivity(intent);
                intent.putExtra("Edit",String.valueOf(arr[0] + 1));
                //intent.putExtra("Edit",StgetIntent().getIntExtra("Position",0)+1ring.valueOf());


            }
        });
    }

    public void deleteButtonClick()
    {

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deleteRows=mydb.deleteNote(String.valueOf(arr[0]+1),arr[1]);
                //   Toast.makeText(showNoteActivity.this,String.valueOf(getIntent().getIntExtra("Position",0)),Toast.LENGTH_SHORT);

                if (deleteRows>0) {
                    Intent intent=new Intent(showNoteActivity.this,MainActivity.class);
                    startActivity(intent);
                    //Toast.makeText(showNoteActivity.this, String.valueOf(getIntent().getIntExtra("Position", 0)), Toast.LENGTH_SHORT);
                    Toast.makeText(showNoteActivity.this, "Note Deleted"+(arr[0]+1), Toast.LENGTH_LONG).show();
                } else {
                    Intent intent=new Intent(showNoteActivity.this,MainActivity.class);
                    startActivity(intent);
                    // Toast.makeText(showNoteActivity.this,String.valueOf(getIntent().getIntExtra("Position",0)),Toast.LENGTH_SHORT);
                    Toast.makeText(showNoteActivity.this, "Note can Not be Deleted"+(arr[0]+1), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
