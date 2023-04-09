package com.himasha.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     DatabaseHelper mydbd;

     EditText date,des,removeID;

     Button add,view,remove,UpdateBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydbd = new DatabaseHelper(this);
        date=findViewById(R.id.dd);
        des = findViewById(R.id.td);
        removeID = findViewById(R.id.RM);
        add = findViewById(R.id.addi);
        view = findViewById(R.id.view);
        remove = findViewById(R.id.RB);
        UpdateBtn = findViewById(R.id.UP);

        addData();
        ViewAll();
        deleteData();
        updateData();
    }

    public void addData(){
        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                boolean isInserted = mydbd.insertData(date.getText().toString(),des.getText().toString());
                if(isInserted == true){
                    Toast.makeText(MainActivity.this,"Data Inserted Succsfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data not inserted Succesfully",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ViewAll(){
        view.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor results = mydbd.getAllDat();
                if(results.getCount()==0){
                    showMessage("Error Message :","No data available in Database.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(results.moveToNext()){
                    buffer.append("ID :"+results.getString(0)+"\n");
                    buffer.append("Date :"+results.getString(1)+"\n");
                    buffer.append("To do :"+results.getString(2)+"\n\n");
                 showMessage("To do ",buffer.toString());
                }
            }
        }));
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void deleteData(){
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleredatarowa = mydbd.deleteData(removeID.getText().toString());
                if(deleredatarowa >0){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void updateData(){
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = mydbd.updateData(removeID.getText().toString(),date.getText().toString(),des.getText().toString());
                if(isUpdate == true){
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}