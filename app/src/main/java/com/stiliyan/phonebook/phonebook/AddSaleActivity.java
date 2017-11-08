package com.stiliyan.phonebook.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stiliyan.phonebook.phonebook.data.CarVO;
import com.stiliyan.phonebook.phonebook.data.ClientVO;
import com.stiliyan.phonebook.phonebook.data.CreditCardVO;
import com.stiliyan.phonebook.phonebook.data.CustomerVO;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.data.InsuranceVO;
import com.stiliyan.phonebook.phonebook.data.SaleVO;
import com.stiliyan.phonebook.phonebook.utils.DatePickerFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddSaleActivity extends AppCompatActivity {

    private Spinner clients;
    private Spinner customers;
    private Spinner cars;
    private Spinner creditCards;
    private Spinner insurances;
    private Button confirm;
    private Button saleDateBtn;
    private TextView saleTW;

    private Date saleDate;

    private List<ClientVO> clientsList;
    private List<CustomerVO> customersList;
    private List<CarVO> carsList;
    private List<CreditCardVO> creditcardsList;
    private List<InsuranceVO> insurancesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);

        clients = (Spinner) findViewById(R.id.clients);
        customers = (Spinner) findViewById(R.id.customers);
        cars = (Spinner) findViewById(R.id.cars);
        creditCards = (Spinner) findViewById(R.id.creditCard);
        insurances = (Spinner) findViewById(R.id.insurance);
        confirm = (Button) findViewById(R.id.confirm);
        saleDateBtn = (Button) findViewById(R.id.sale_date_calendar);
        saleTW = (TextView) findViewById(R.id.sale_date_tw);

        clientsList = DataController.getInstance().getClients();
        customersList = DataController.getInstance().getCustomers();
        carsList = DataController.getInstance().getCars();
        creditcardsList = DataController.getInstance().getCreditCards();
        insurancesList = DataController.getInstance().getInsurances();

        List<String> clientNames = new ArrayList<>();
        List<String> customerNames = new ArrayList<>();
        List<String>carNames = new ArrayList<>();
        List<String>creditcardNames = new ArrayList<>();
        List<String>insuranceNames = new ArrayList<>();

        for ( int i = 0; i<clientsList.size();i++)
        {
            clientNames.add(clientsList.get(i).name);
        }
        for ( int i = 0; i<customersList.size();i++)
        {
            customerNames.add(customersList.get(i).name);
        }
        for ( int i = 0; i<carsList.size();i++)
        {
            carNames.add(carsList.get(i).brand);
        }
        for ( int i = 0; i<creditcardsList.size();i++)
        {
            creditcardNames.add(String.valueOf( creditcardsList.get(i).number ));
        }
        for ( int i = 0; i<insurancesList.size();i++)
        {
            insuranceNames.add( insurancesList.get(i).insurer );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, clientNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        clients.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, customerNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        customers.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, carNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        cars.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, creditcardNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        creditCards.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, insuranceNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        insurances.setAdapter( adapter );

        confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContact();
            }
        });

        saleDateBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new SalesForPeriodActivity.TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        saleTW.setText( date.toString());
                        saleDate = date;
                    }
                });
            }
        });
    }

    private void saveContact(){
        if ( clients.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid client", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( customers.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid customer", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( cars.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid car", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( creditCards.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid credit card", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( insurances.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid insurance type", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( saleDate == null )
        {
            saleTW.setError( "please select sale date");
            return;
        }
        else
            saleTW.setError( null );

        SaleVO sale = new SaleVO();
        sale.client = clientsList.get(clients.getSelectedItemPosition());
        sale.customer = customersList.get(customers.getSelectedItemPosition());
        sale.car = carsList.get(cars.getSelectedItemPosition());
        sale.creditCard = creditcardsList.get( creditCards.getSelectedItemPosition() );
        sale.insuranceType = insurancesList.get( insurances.getSelectedItemPosition() );

        sale.saledate = saleDate;

        DataController.getInstance().addSale(sale);
        finish();
    }
}
