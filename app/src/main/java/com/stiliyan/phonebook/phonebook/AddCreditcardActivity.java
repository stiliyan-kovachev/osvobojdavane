package com.stiliyan.phonebook.phonebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stiliyan.phonebook.phonebook.data.CreditCardVO;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.utils.DatePickerFragment;

import java.util.Date;

public class AddCreditcardActivity extends AppCompatActivity {

    private EditText company;
    private EditText number;
    private Button expirationBtn;
    private TextView expirationTW;
    private Button confirm;

    private Date expDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_creditcard);

        company = ( EditText ) findViewById(R.id.company);
        number =( EditText ) findViewById(R.id.number);
        expirationBtn =( Button ) findViewById(R.id.exp_date);
        expirationTW =( TextView ) findViewById(R.id.exp_date_tw);
        confirm =( Button ) findViewById(R.id.confirm);

        expirationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new SalesForPeriodActivity.TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        expirationTW.setText( date.toString());
                        expDate = date;

                    }
                });
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    private void save() {
        if (TextUtils.isEmpty(company.getText()))
        {
            company.setError( "company required" );
            return;
        }
        if (TextUtils.isEmpty(number.getText()) || !TextUtils.isDigitsOnly( number.getText()) || number.getText().length() > 16 )
        {
            number.setError( "invalid number" );
            return;
        }
        if (expDate == null)
        {
            expirationTW.setError( "invalid exp date" );
            return;
        }
        else
            expirationTW.setError( null );

        CreditCardVO model = new CreditCardVO();
        model.serviceCompany = company.getText().toString();
        model.number = Long.valueOf( number.getText().toString());
        model.expirationDate = expDate;

        DataController.getInstance().addCreditCard( model );

        finish();
    }

}
