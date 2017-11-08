package com.stiliyan.phonebook.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class InquiriesActivity extends AppCompatActivity {

    private Button salesFromCustomerBtrn;
    private Button lastFiveSalesBtrn;
    private Button boughtFromClientBtn;
    private Button salesForPeriodBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiries);

        salesFromCustomerBtrn = (Button) findViewById(R.id.salesFromCustomer);
        lastFiveSalesBtrn = (Button) findViewById(R.id.lastFiveSales);
        boughtFromClientBtn = (Button) findViewById(R.id.boughtFromClient);
        salesForPeriodBtn = (Button) findViewById(R.id.salesForPeriod);

        lastFiveSalesBtrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InquiriesActivity.this, LastFiveActivity.class );
                startActivity( intent );
            }
        });

        salesForPeriodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InquiriesActivity.this, SalesForPeriodActivity.class );
                startActivity( intent );
            }
        });

        salesFromCustomerBtrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InquiriesActivity.this, SaledCarsFromCustomerActivity.class );
                startActivity( intent );
            }
        });
    }

}
