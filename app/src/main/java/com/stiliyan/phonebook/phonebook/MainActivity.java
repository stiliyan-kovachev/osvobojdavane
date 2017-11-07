package com.stiliyan.phonebook.phonebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.stiliyan.phonebook.phonebook.adapter.ContactListAdapter;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.data.SaleVO;
import com.stiliyan.phonebook.phonebook.utils.RequestCodes;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;
    private Button viewSalesBtn;


    private List<SaleVO> contactData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = (Button) findViewById(R.id.add);
        viewSalesBtn = (Button) findViewById(R.id.viewSales);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        viewSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewSalesActivity.class);
                startActivity(intent);
            }
        });

    }
}
