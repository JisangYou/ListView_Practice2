# AndroidMemo2
## 리스트뷰와 DBHelper를 활용(Android_Memo_WithDB 보충설명)
### 액티비티 및 클래스 구성

1. ListActivity

2. DBHelperActivity

3. Memo클래스

4. MemoDAO클래스 - C,R,U,D 형태로 데이터 베이스 설계


CRUD는 대부분의 컴퓨터 소프트웨어가 가지는 기본적인 데이터 처리 기능인 Create(생성), Read(읽기), Update(갱신), Delete(삭제)를 묶어서 일컫는 말이다. 사용자 인터페이스가 갖추어야 할 기능(정보의 참조/검색/갱신)을 가리키는 용어로서도 사용된다.


```Java
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


```
