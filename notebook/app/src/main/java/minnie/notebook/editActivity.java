package minnie.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editActivity extends AppCompatActivity {

    databaseActivity mydb;
    Button cancelBtn,doneBtn;
    public EditText editNoteTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mydb=new databaseActivity(this);

        cancelBtn=(Button) findViewById(R.id.cancel2);
        doneBtn=(Button)findViewById(R.id.done2);
        editNoteTextview=(EditText)findViewById(R.id.editTextView);
        cancelBtnClick();
        doneBtnClick();


    }

    public void cancelBtnClick()
    {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void doneBtnClick()
    {
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(getIntent().getStringExtra("Edit"));
                mydb.getNotes().moveToPosition(id);
                editNoteTextview.setText(mydb.getNotes().getString(0));
            }
        });
    }




}
