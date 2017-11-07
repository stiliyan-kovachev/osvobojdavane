package com.stiliyan.phonebook.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.stiliyan.phonebook.phonebook.utils.Consts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditSaleActivity extends AppCompatActivity {

    private Spinner clients;
    private Spinner customers;
    private Spinner cars;
    private Button confirm;

    private int saleID = -1;

    private List<ClientVO> clientsList;
    private List<CustomerVO> customersList;
    private List<CarVO> carsList;
    private SaleVO crrSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sale);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null)
            saleID = bd.getInt(Consts.ID );

        clients = (Spinner) findViewById(R.id.clients);
        customers = (Spinner) findViewById(R.id.customers);
        cars = (Spinner) findViewById(R.id.cars);
        confirm = (Button) findViewById(R.id.confirm);

        crrSale = DataController.getInstance().getSaleById( saleID );
        clientsList = DataController.getInstance().getClients();
        customersList = DataController.getInstance().getCustomers();
        carsList = DataController.getInstance().getCars();

        List<String> clientNames = new ArrayList<>();
        List<String> customerNames = new ArrayList<>();
        List<String>carNames = new ArrayList<>();

        int crrClientPosition = 0;
        int crrCustomerPosition = 0;
        int crrCarPosition = 0;

        for ( int i = 0; i<clientsList.size();i++)
        {
            if ( crrSale.client.id == clientsList.get(i).id)
                crrClientPosition = i;

            clientNames.add(clientsList.get(i).name);
        }
        for ( int i = 0; i<customersList.size();i++)
        {
            if ( crrSale.customer.id == customersList.get(i).id)
                crrCustomerPosition = i;

            customerNames.add(customersList.get(i).name);
        }
        for ( int i = 0; i<carsList.size();i++)
        {
            if ( crrSale.car.id == carsList.get(i).id)
                crrCarPosition = i;

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

        clients.setSelection(crrClientPosition);
        customers.setSelection(crrCustomerPosition);
        cars.setSelection(crrCarPosition);

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
        sale.id = saleID;
        sale.client = clientsList.get(clients.getSelectedItemPosition());
        sale.customer = customersList.get(customers.getSelectedItemPosition());
        sale.car = carsList.get(cars.getSelectedItemPosition());

        sale.saledate = new Date();

        DataController.getInstance().updateSale(sale);

        Intent returnIntent = new Intent();
        setResult( Activity.RESULT_OK, returnIntent);
        finish();
    }

}
