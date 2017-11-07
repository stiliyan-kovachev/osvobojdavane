package com.stiliyan.phonebook.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AddActivity extends AppCompatActivity {

    private Button addClientBtn;
    private Button addCustomerBtn;
    private Button addCarBtn;
    private Button addSaleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addClientBtn = ( Button ) findViewById( R.id.addClient );
        addCustomerBtn = ( Button ) findViewById( R.id.addCustomer );
        addCarBtn = ( Button ) findViewById( R.id.addCar );
        addSaleBtn = ( Button ) findViewById( R.id.addSale );

        addClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddClientActivity.class );
                startActivity( intent );
            }
        });

        addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddCustomerActivity.class );
                startActivity( intent );
            }
        });

        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddCarActivity.class );
                startActivity( intent );
            }
        });

        addSaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddSaleActivity.class );
                startActivity( intent );
            }
        });
    }

}
