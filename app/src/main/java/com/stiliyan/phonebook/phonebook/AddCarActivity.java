package com.stiliyan.phonebook.phonebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stiliyan.phonebook.phonebook.data.CarVO;
import com.stiliyan.phonebook.phonebook.data.CustomerVO;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.utils.Validation;

public class AddCarActivity extends AppCompatActivity {
    private EditText brand;
    private EditText model;
    private EditText year;
    private EditText color;
    private EditText kilometers;
    private EditText price;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        brand = ( EditText ) findViewById(R.id.brand);
        model = ( EditText ) findViewById(R.id.model);
        year = ( EditText ) findViewById(R.id.year);
        color = ( EditText ) findViewById(R.id.color);
        kilometers = ( EditText ) findViewById(R.id.kilometers);
        price = ( EditText ) findViewById(R.id.price);
        confirm = (Button ) findViewById( R.id.confirm );

        confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContact();
            }
        });
    }

    private void saveContact()
    {
        if( TextUtils.isEmpty( brand.getText()) ) {
            brand.setError("brand required ");
            return;
        }

        if ( TextUtils.isEmpty( model.getText() ) )
        {
            model.setError( "invalid model" );
            return;
        }

        if ( TextUtils.isEmpty(( year.getText() ) ) || !TextUtils.isDigitsOnly( year.getText() ) )
        {
            year.setError( "invalid year" );
            return;
        }

        if ( TextUtils.isEmpty( color.getText() ) )
        {
            color.setError( "invalid color" );
            return;
        }

        if ( TextUtils.isEmpty(( kilometers.getText() ) ) || !TextUtils.isDigitsOnly( kilometers.getText() ) )
        {
            kilometers.setError( "invalid kiloometers" );
            return;
        }
        if ( TextUtils.isEmpty(( price.getText() ) ) || !TextUtils.isDigitsOnly( price.getText() ) )
        {
            price.setError( "invalid kiloometers" );
            return;
        }


        CarVO client = new CarVO();
        client.brand = brand.getText().toString();
        client.model = model.getText().toString();
        client.year = Integer.parseInt( year.getText().toString() );
        client.color = color.getText().toString();
        client.kilometers = Integer.parseInt(kilometers.getText().toString());
        client.price = Integer.parseInt(price.getText().toString());

        DataController.getInstance().addCar( client );

//        Intent returnIntent = new Intent();
//        setResult( Activity.RESULT_OK,returnIntent );

        finish();
    }

}
