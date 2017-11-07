package com.stiliyan.phonebook.phonebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stiliyan.phonebook.phonebook.R;
import com.stiliyan.phonebook.phonebook.data.ClientVO;
import com.stiliyan.phonebook.phonebook.data.CustomerVO;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.utils.Validation;

public class AddCustomerActivity extends AppCompatActivity {

    private TextView nameTV;
    private TextView phoneTV;
    private TextView positionTV;
    private Spinner countrySpinner;
    private Spinner codeSpinner;
    private Spinner genderSpinner;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        nameTV = (TextView) findViewById(R.id.name);
        phoneTV = (TextView) findViewById(R.id.phone);
        positionTV = (TextView) findViewById(R.id.position);
        confirmBtn = (Button) findViewById(R.id.confirm);

        confirmBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContact();
            }
        });
    }

    private void saveContact()
    {
        if( TextUtils.isEmpty( nameTV.getText()) ) {
            nameTV.setError("name required ");
            return;
        }

        if ( TextUtils.isEmpty( positionTV.getText() ) )
        {
            positionTV.setError( "invalid position" );
            return;
        }

        if ( !Validation.isValidPhone( phoneTV.getText() ) )
        {
            phoneTV.setError( "invalid phone" );
            return;
        }

        CustomerVO client = new CustomerVO();
        client.name = nameTV.getText().toString();
        client.position = positionTV.getText().toString();
        client.phone = phoneTV.getText().toString();

        DataController.getInstance().addCustomer( client );

//        Intent returnIntent = new Intent();
//        setResult( Activity.RESULT_OK,returnIntent );

        finish();
    }

}
