package orgs.androidtown.androidmemopractice02.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import orgs.androidtown.androidmemopractice02.DBHelper;

/**
 * Created by Jisang on 2017-09-24.
 */

public class MemoDAO { //C,R,U,D 방식으로 데이터를 설계
    DBHelper helper;

    public MemoDAO(Context context) {
        helper = new DBHelper(context);
    }

    public void create(Memo memo){
        SQLiteDatabase con = helper.getWritableDatabase();

        String query ="insert into memo(title, content, n_date)" + " values('"+memo.title+"','"+memo.content+"',datetime('now','localtime'))"; // 여기에 formmatter를 사용하면 더 간결하게 표현할 수 있음.
        con.execSQL(query);
        con.close();
    }

    public ArrayList<Memo> read(){
        String query = "select id, title, content, n_date from memo";

        ArrayList<Memo> data = new ArrayList<>();
        SQLiteDatabase con = helper.getReadableDatabase();
        Cursor cursor = con.rawQuery(query, null);

        while(cursor.moveToNext()){
            Memo memo = new Memo();

            memo.id = cursor.getInt(0);
            memo.title = cursor.getString(1);
            memo.content = cursor.getString(2);
            memo.n_date = cursor.getString(3);
            data.add(memo);

        }
        con.close();
        return data;
    }

    public void update(String query){
        SQLiteDatabase con = helper.getWritableDatabase();
        con.execSQL(query);
        con.close();
    }
    public void delete(String query){
        SQLiteDatabase con = helper.getWritableDatabase();
        con.execSQL(query);
        con.close();
    }

    public void close(){
        helper.close();
    }

}
