package com.stiliyan.phonebook.phonebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stiliyan.phonebook.phonebook.data.ClientVO;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.utils.Validation;

public class AddClientActivity extends AppCompatActivity {

    private TextView nameTV;
    private TextView phoneTV;
    private TextView addressTV;
    private Spinner countrySpinner;
    private Spinner codeSpinner;
    private Spinner genderSpinner;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        nameTV = (TextView) findViewById(R.id.name);
        phoneTV = (TextView) findViewById(R.id.phone);
        addressTV = (TextView) findViewById(R.id.address);
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

       if ( !Validation.isValidAddress( addressTV.getText() ) )
       {
           addressTV.setError( "invalid address" );
           return;
       }

       if ( !Validation.isValidPhone( phoneTV.getText() ) )
       {
           phoneTV.setError( "invalid phone" );
           return;
       }

        ClientVO client = new ClientVO();
        client.name = nameTV.getText().toString();
        client.address = addressTV.getText().toString();
        client.phone = phoneTV.getText().toString();

        DataController.getInstance().addCleint( client );

//        Intent returnIntent = new Intent();
//        setResult( Activity.RESULT_OK,returnIntent );

        finish();
    }

}
