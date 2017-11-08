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

import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.data.InsuranceVO;

public class AddInsuranceActivity extends AppCompatActivity {

    private EditText name;
    private EditText value;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_insurance);

        name = (EditText) findViewById(R.id.insurance_name);
        value = (EditText) findViewById(R.id.value);
        confirm = (Button) findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty( name.getText())) {
                    name.setError("name required");
                    return;
                }
                if (TextUtils.isEmpty( value.getText()) || !TextUtils.isDigitsOnly(value.getText())) {
                    value.setError("invalid value");
                    return;
                }

                InsuranceVO model = new InsuranceVO();
                model.insurer = name.getText().toString();
                model.value = Integer.valueOf( value.getText().toString() );

                DataController.getInstance().addInsurance( model );
                finish();

            }
        });
    }

}
