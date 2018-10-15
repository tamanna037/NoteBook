package minnie.notebook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseActivity extends SQLiteOpenHelper {

    public static final String Database_Name="2.db";
    public static final String Table_Name="NoteList";
    public static final String Column1="ID";
    public static final String Column2="Note";

    public databaseActivity(Context context) {
        super(context, Database_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + "(Note TEXT,ID INTEGER PRIMARY KEY AUTOINCREMENT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + Table_Name);
        onCreate(db);
    }

    public boolean insertNote(String note)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(Column2, note);

        long res=db.insert(Table_Name,null,contentValues);

        if(res==-1) return false;
        else return true;


    }

    public Integer deleteNote(String id,int count)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int intId=Integer.parseInt(id);
        Integer deleteRows=db.delete(Table_Name, "ID=?", new String[]{id});

        if(deleteRows>0)
        {
            count=count-intId;
            int in=intId;
            while (count!=0)
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(Column1, in);
                db.update(Table_Name, contentValues, "ID=?", new String[]{String.valueOf(++intId)});
                count--;in++;
            }

        }
        return deleteRows;
    }


    public Cursor getNotes() {

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + Table_Name, null);
        return res;
    }

    public boolean updateNote(String note,String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column2,note);
        contentValues.put(Column1, id);
        db.update(Table_Name, contentValues, "ID=?", new String[]{id});
        return true;
    }
}
