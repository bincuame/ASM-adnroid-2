package com.example.assignment_android2;

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
import com.example.assignment_android2.Model.User;

public class Sign_up extends AppCompatActivity {
    EditText edtUser, edtPass, edtRetypePass;
    Button btn_SignUp;
    User_DAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();

        clickBtn_SignUp();
    }

    public void anhXa(){
        // Ánh xạ các phần tử trong layout
        edtUser = findViewById(R.id.edt_User);
        edtPass = findViewById(R.id.edt_Password);
        edtRetypePass = findViewById(R.id.edt_RetypePassword);
        btn_SignUp = findViewById(R.id.btn_SignUp);

        // Tạo các đối tượng mới
        userDao = new User_DAO(this);
    }

    public void checknull(){
        // Lấy dữ liệu từ EditText
        String user = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        String retypePass = edtRetypePass.getText().toString();

        // check null
        if (user.isEmpty() || pass.isEmpty() || retypePass.isEmpty()){

            // xét error cho EditText
            if (user.isEmpty()){
                edtUser.setError("Bạn chưa nhập tên đăng nhập");
            }else if(pass.isEmpty()){
                edtPass.setError("Bạn chưa nhập mật khẩu");
            }else if (retypePass.isEmpty()){
                edtRetypePass.setError("Bạn chưa nhập lại password");
            }
        }else if(!pass.matches("^.*[A-Z]+.*$")){ // check điều kiện chứa chữ hoa
            edtPass.setError("Mật khẩu ít nhất 1 ký tự in hoa");
        }else if(!pass.matches("^.*[!@#$&*]+.*$")){ // check điều kiện chứa kí tự đặc biệt
            edtPass.setError("Mật khẩu phải chứa ít nhất 1 kí tự đặc biệt");
        }else if (!pass.matches("^.*[0-9]+.*$")){   // check điều kiện chứa chữ số
            edtPass.setError("Mật khẩu phải chứa ít là nhất 1 chữ số");
        }else if (pass.length()<7 ){ // check điều kiện độ dài
            edtPass.setError("Mật khẩu phải dài hơn 7 kí tự");
        }else {
            if (!retypePass.equals(pass)){
                edtRetypePass.setError("Mật khẩu nhập lại không chính xác");
            }else {
                boolean check = userDao.Insert(new User(user, pass));
                if (check){
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void clickBtn_SignUp(){

        // sử lý sự kiện bấm nút đăng ký
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checknull();
            }
        });
    }
}