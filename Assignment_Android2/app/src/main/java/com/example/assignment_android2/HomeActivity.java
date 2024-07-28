package com.example.assignment_android2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_android2.Adapter.BaiViet_Adapter;
import com.example.assignment_android2.DAO.BaiViet_DAO;
import com.example.assignment_android2.Model.BaiViet;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv_home;
    BaiViet_Adapter baiVietAdapter;
    BaiViet_DAO baiVietDao;
    ArrayList<BaiViet> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();

        baiVietDao = new BaiViet_DAO(this);
         list = baiVietDao.getAll();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        rv_home.setLayoutManager(gridLayoutManager);
        baiVietAdapter = new BaiViet_Adapter(HomeActivity.this, list);

        setSupportActionBar(toolbar);
        rv_home.setAdapter(baiVietAdapter);

    }

    public void anhXa(){
        toolbar = findViewById(R.id.toolbar);
        rv_home = findViewById(R.id.rv_Home);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search){

        }
        if (id == R.id.add){
            startActivityForResult(new Intent(HomeActivity.this, Add_BaiViet.class), 1);
        }
        return true;
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            BaiViet baiViet = (BaiViet) data.getExtras().get("object");
            boolean check = baiVietDao.Insert(baiViet);
            if (check){
                Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                list.clear();
                list.addAll(baiVietDao.getAll());
                baiVietAdapter.notifyDataSetChanged();
            }
        }
    }



}