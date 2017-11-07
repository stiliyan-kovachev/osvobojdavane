package com.stiliyan.phonebook.phonebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.stiliyan.phonebook.phonebook.adapter.ContactListAdapter;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.data.SaleVO;

import java.util.List;

public class LastFiveActivity extends AppCompatActivity {

    private ContactListAdapter contactListAdapter;
    private ListView salesList;
    private List<SaleVO> salesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_five);

        salesList = (ListView) findViewById( R.id.salesList );

        salesData = DataController.getInstance().lastFiveSalesOrderedByPrice();

        contactListAdapter = new ContactListAdapter( this, R.layout.contact_list_item_renderer, salesData );
        salesList.setAdapter( contactListAdapter );
    }

}
