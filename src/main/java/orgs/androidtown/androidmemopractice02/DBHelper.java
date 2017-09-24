package orgs.androidtown.androidmemopractice02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by Jisang on 2017-09-24.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "sqlite.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE 'memo'(\n" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "'title' TEXT, \n" +
                "'content' TEXT, \n" +
                "'n_date' Text" +
                "'modified' TEXT)";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i<2){

        }
    }
}

class Singleton{
    private static Singleton instance = null;

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    private Singleton(){

    }
}
