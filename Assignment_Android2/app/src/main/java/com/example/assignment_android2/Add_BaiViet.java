package com.example.assignment_android2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.assignment_android2.Adapter.BaiViet_Adapter;
import com.example.assignment_android2.DAO.BaiViet_DAO;
import com.example.assignment_android2.Model.BaiViet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_BaiViet extends AppCompatActivity {
    Toolbar toolbar;
    EditText edt_Tilte, edt_content;
    Button btn_Luu;
    BaiViet_DAO baiVietDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_bai_viet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        clickLuu();
    }
    public void anhXa(){
        toolbar = findViewById(R.id.tolb_add);
        btn_Luu = findViewById(R.id.btn_Lưu);
        edt_Tilte = findViewById(R.id.edt_Tilte);
        edt_content = findViewById(R.id.edt_Content);

        baiVietDao = new BaiViet_DAO(this);
    }

    public void clickLuu(){
        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checknull();
            }
        });
    }
    public void checknull(){
        String Tilte = edt_Tilte.getText().toString().trim();
        String Content = edt_content.getText().toString().trim();
        if (Tilte.isEmpty() || Content.isEmpty()){
            if (Tilte.isEmpty()){
                edt_Tilte.setError("Ban chưa nhập tiêu đề");
            }else {
                edt_content.setError("Bạn chưa nhâp nội dung");
            }
        }else {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dinhDang = new SimpleDateFormat("dd/MM/yyyy");
            Date dateNow = cal.getTime();
            String time = dinhDang.format(dateNow);

            BaiViet baiViet = new BaiViet(null, Tilte, Content, time);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("object", baiViet);

            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}