package orgs.androidtown.androidmemopractice02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import orgs.androidtown.androidmemopractice02.domain.Memo;
import orgs.androidtown.androidmemopractice02.domain.MemoDAO;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreate, btnRead, btnUpdate, btnDelete;
    EditText editTitle, editContent;
    TextView textResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initViews();
        initListener();
        init();

    }


    private void initViews() {
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editContent = (EditText) findViewById(R.id.editContent);
        textResult = (TextView) findViewById(R.id.textResult);
    }

    private void initListener() {
        btnCreate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    MemoDAO dao = null;

    private void init() {
        dao = new MemoDAO(this); // dao 객체 생성(context를 받고)
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreate:
                createAfterRead();
                break;
            case R.id.btnRead:
                read();
                break;
            case R.id.btnUpdate:

                break;
            case R.id.btnDelete:

                break;
        }
    }

    private void createAfterRead() { //이곳은 완전히 메서드들을 위한 메서드!, 전체적인 그림을 그리고, 세부메서드들은 하나씩 차근차근히 채우기
        Memo memo = getMemoFromScreen();
        create(memo);
        showInfo("입력되었습니다.");
        resetScreen();
        read();
    }

    private Memo getMemoFromScreen() {// 화면에 보여주는 메소드
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();
        return new Memo(title, content);
    }

    private void create(Memo memo) { // memo란 객체가 들어오면, 쿼리를 날린다.
        dao.create(memo);
    }

    private void resetScreen() {
        editTitle.setText("");
        editContent.setText("");
    }

    private void showInfo(String comment) {
        Toast.makeText(this, comment, Toast.LENGTH_LONG).show();
    }

    public void read() { // 이미dao클래스에 data들이 만들어져 보내기에 따로 인자가 필요없음.
        ArrayList<Memo> data = dao.read();
        textResult.setText(" ");

        for (Memo memo : data) {
            textResult.append(memo.toString());
        }
    }


    @Override
    protected void onDestroy() {
        if (dao != null) {
            dao.close();
        }
        super.onDestroy();
    }
}