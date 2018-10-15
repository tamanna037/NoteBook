package minnie.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newNoteActivity extends AppCompatActivity {

    boolean isNew;
    databaseActivity mydb;
    Button cancelBtn,doneBtn;
    public   EditText newNoteTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isNew=true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mydb=new databaseActivity(this);

      //  cancelBtn=(Button) findViewById(R.id.cancelButton);
        cancelBtn=(Button)findViewById(R.id.cancelButton);
        doneBtn=(Button)findViewById(R.id.doneButton);
        newNoteTextview=(EditText)findViewById(R.id.writeNoteTextView);

       /* String extra=getIntent().getStringExtra("EditShow");
        if(!extra.equals("-1")) {

            isNew = false;
            newNoteTextview.setText(extra);
        }
        else
        {
            newNoteTextview.setText("main:");
        }*/
        cancelBtnClick();
        doneBtnClick();


    }

    public void cancelBtnClick()
    {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void doneBtnClick()
    {
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted,isUpdated;
                String toInsert=newNoteTextview.getText().toString();
                if(isNew)
                {
                    isInserted=mydb.insertNote(toInsert);
                    if(isInserted){

                        Intent intent=new Intent(newNoteActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(newNoteActivity.this,"Note Inserted",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(newNoteActivity.this,"Note note Inserted",Toast.LENGTH_LONG).show();
                    }


                }
              /*  else {
                   isUpdated=mydb.updateNote(newNoteTextview.getText().toString(),getIntent().getStringExtra("Edit"));
                   if(isUpdated){

                       Intent intent=new Intent(newNoteActivity.this,MainActivity.class);
                       startActivity(intent);
                       Toast.makeText(newNoteActivity.this,"Note Inserted",Toast.LENGTH_LONG).show();
                   }
               }*/
            }
        });
    }


}
