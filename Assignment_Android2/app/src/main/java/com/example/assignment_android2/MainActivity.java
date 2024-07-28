package com.example.assignment_android2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.assignment_android2.DAO.User_DAO;
import com.example.assignment_android2.Dbase.DbUser;
import com.example.assignment_android2.Model.User;

public class MainActivity extends AppCompatActivity {
    DbUser dbUser;
    User_DAO userDao;
    User user;
    EditText edtUser, edtPass;
    Button btn_DangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Ánh xạ
        anhXa();

        clickLogin();
    }

    public void anhXa(){
        btn_DangNhap = findViewById(R.id.btn_DangNhap);
        edtUser = findViewById(R.id.edt_User);
        edtPass = findViewById(R.id.edt_Password);

        userDao = new User_DAO(this);
        dbUser = new DbUser(this);
    }

    public void checkNull(){
        String txtUser = edtUser.getText().toString();
        String txtPass = edtPass.getText().toString();
        if(txtUser.isEmpty() || txtPass.isEmpty()){
            if (txtUser.isEmpty()){
                edtUser.setError("Chưa nhập tên đăng nhập");
            }else {
                edtPass.setError("Chưa nhập mật khẩu");
            }
        }else{
            user = (User) userDao.get_oneRow(txtUser);
            if (txtUser.equals(user.getUserName()) && txtPass.equals(user.getPassWord())){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));
            }else {
                Toast.makeText(this, "Mật khẩu hoặc tài khoản không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clickLogin(){
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNull();
            }
        });
    }
}