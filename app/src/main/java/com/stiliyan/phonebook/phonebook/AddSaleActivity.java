package com.stiliyan.phonebook.phonebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.stiliyan.phonebook.phonebook.data.CarVO;
import com.stiliyan.phonebook.phonebook.data.ClientVO;
import com.stiliyan.phonebook.phonebook.data.CustomerVO;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.data.SaleVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddSaleActivity extends AppCompatActivity {

    private Spinner clients;
    private Spinner customers;
    private Spinner cars;
    private Button confirm;

    private List<ClientVO> clientsList;
    private List<CustomerVO> customersList;
    private List<CarVO> carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);

        clients = (Spinner) findViewById(R.id.clients);
        customers = (Spinner) findViewById(R.id.customers);
        cars = (Spinner) findViewById(R.id.cars);
        confirm = (Button) findViewById(R.id.confirm);

        clientsList = DataController.getInstance().getClients();
        customersList = DataController.getInstance().getCustomers();
        carsList = DataController.getInstance().getCars();

        List<String> clientNames = new ArrayList<>();
        List<String> customerNames = new ArrayList<>();
        List<String>carNames = new ArrayList<>();

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, clientNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        clients.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, customerNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        customers.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, carNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        cars.setAdapter( adapter );

        confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContact();
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

        SaleVO sale = new SaleVO();
        sale.client = clientsList.get(clients.getSelectedItemPosition());
        sale.customer = customersList.get(customers.getSelectedItemPosition());
        sale.car = carsList.get(cars.getSelectedItemPosition());

        sale.saledate = new Date();

        DataController.getInstance().addSale(sale);
        finish();
    }
}
