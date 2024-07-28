package com.example.assignment_android2.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_android2.DAO.BaiViet_DAO;
import com.example.assignment_android2.Model.BaiViet;
import com.example.assignment_android2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaiViet_Adapter extends RecyclerView.Adapter<BaiViet_Adapter.BaiVietViewHodel>{
     Context context;
     List<BaiViet> list;

    public BaiViet_Adapter(Context context, List<BaiViet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BaiVietViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baiviet, parent, false);
        BaiVietViewHodel BaiVietViewHodel = new BaiVietViewHodel(view);
        return BaiVietViewHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull BaiVietViewHodel holder, int position) {
        BaiViet baiViet = list.get(position);

        holder.tvTilte.setText(baiViet.getTilte());
        holder.tvContent.setText(baiViet.getContent());
        holder.tvDate.setText("Đã chỉnh sửa ngày: "+baiViet.getDate());

        holder.btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpadte(baiViet);
            }
        });
        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogXacNhanXoa(baiViet);
            }
        });
    }



    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class BaiVietViewHodel extends RecyclerView.ViewHolder {
        TextView tvTilte, tvContent, tvDate;
        Button btn_Delete, btn_Update;
        public BaiVietViewHodel(@NonNull View itemView) {
            super(itemView);
            tvTilte = itemView.findViewById(R.id.tv_Tilte);
            tvContent= itemView.findViewById(R.id.tv_Content);
            tvDate = itemView.findViewById(R.id.tv_Date);
            btn_Delete = itemView.findViewById(R.id.btn_Delete);
            btn_Update = itemView.findViewById(R.id.btn_Update);
        }
    }
    public void showDialogUpadte(BaiViet baiViet){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_update, null, false);

        EditText edtTilte = view.findViewById(R.id.edt_Tilte);
        EditText edtContent = view.findViewById(R.id.edt_Content);
        Button btn_Update = view.findViewById(R.id.btn_Update);
        Button btn_Huy = view.findViewById(R.id.btn_Huy);

        edtTilte.setText(baiViet.getTilte());
        edtContent.setText(baiViet.getContent());

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTilte = edtTilte.getText().toString().trim();
                String newContent = edtContent.getText().toString().trim();
                if (newTilte.isEmpty() || newContent.isEmpty()){
                    if (newTilte.isEmpty()){
                        edtTilte.setError("Không được để trống");
                    }
                    else if (newContent.isEmpty()){
                        edtContent.setError("Không được để trống");
                    }
                } else  {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = cal.getTime();
                    String time = simpleDateFormat.format(date);

                    BaiViet_DAO baiVietDao = new BaiViet_DAO(context);
                    boolean check = baiVietDao.Update(new BaiViet(baiViet.getId(),newTilte, newContent, time));
                    if (check){
                        list.clear();
                        list.addAll(baiVietDao.getAll());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Cập nhập thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }

    private void diaLogXacNhanXoa(BaiViet baiViet) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn chắc chắn muốn xóa dữ liệu này ?");
        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaiViet_DAO baiVietDao = new BaiViet_DAO(context);
                boolean check = baiVietDao.delete(baiViet);
                if (check){
                    list.clear();
                    list.addAll(baiVietDao.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }
}
