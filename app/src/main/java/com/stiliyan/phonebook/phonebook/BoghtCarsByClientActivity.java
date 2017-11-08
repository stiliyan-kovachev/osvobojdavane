package com.stiliyan.phonebook.phonebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.stiliyan.phonebook.phonebook.adapter.CarsListAdapter;
import com.stiliyan.phonebook.phonebook.data.CarVO;
import com.stiliyan.phonebook.phonebook.data.ClientVO;
import com.stiliyan.phonebook.phonebook.data.CustomerVO;
import com.stiliyan.phonebook.phonebook.data.DataController;

import java.util.ArrayList;
import java.util.List;

public class BoghtCarsByClientActivity extends AppCompatActivity {

    private Button confirmBtn;
    private Spinner clients;
    private ListView carsList;

    private CarsListAdapter contactListAdapter;

    private List<ClientVO> clientList;
    private List<CarVO> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boght_cars_by_client);

        clients = ( Spinner ) findViewById( R.id.customers );
        carsList = (ListView ) findViewById( R.id.carsList );
        confirmBtn = ( Button ) findViewById( R.id.confirm );

        contactListAdapter = new CarsListAdapter( this, R.layout.sale_list_item_renderer, cars );
        carsList.setAdapter( contactListAdapter );

        clientList = DataController.getInstance().getClients();
        List<String> clientNames = new ArrayList<>();

        for ( int i = 0; i<clientList.size();i++)
        {
            clientNames.add(clientList.get(i).name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, clientNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        clients.setAdapter( adapter );

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmTriggered();
            }
        });
    }

    private void confirmTriggered(){
        if ( clients.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid client", Toast.LENGTH_SHORT).show();
            return;
        }

        int customerId = clientList.get( clients.getSelectedItemPosition() ).id;

        cars.clear();
        cars.addAll( DataController.getInstance().boughtCarsByClient( customerId ));
        contactListAdapter.notifyDataSetChanged();
    }

}
