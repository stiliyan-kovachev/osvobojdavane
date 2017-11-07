package com.stiliyan.phonebook.phonebook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.stiliyan.phonebook.phonebook.adapter.ContactListAdapter;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.data.SaleVO;
import com.stiliyan.phonebook.phonebook.utils.Consts;
import com.stiliyan.phonebook.phonebook.utils.RequestCodes;

import java.util.List;

public class ViewSalesActivity extends AppCompatActivity {

    private ContactListAdapter contactListAdapter;
    private ListView salesList;
    private List<SaleVO> salesData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sales);

        salesList = (ListView) findViewById( R.id.salesList );

        DataController.getInstance().setContext( this );
        salesData = DataController.getInstance().getAllSales();

        contactListAdapter = new ContactListAdapter( this, R.layout.contact_list_item_renderer, salesData );
        salesList.setAdapter( contactListAdapter );

        salesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick( AdapterView<?> arg0, View v,
                                            final int i, long l ) {

                final CharSequence[] items = { getResources().getString( R.string.edit ), getResources().getString( R.string.delete ) };

                AlertDialog.Builder builder = new AlertDialog.Builder( ViewSalesActivity.this );

                builder.setTitle("Action:");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        SaleVO model = ( SaleVO ) salesList.getItemAtPosition( i );
                        if( item == 0 )
                        {
                            Intent intent = new Intent( ViewSalesActivity.this, EditSaleActivity.class );
                            intent.putExtra( Consts.ID, model.id );
                            startActivityForResult( intent, RequestCodes.CREATE_EDIT_SALE_REQUEST_CODE);
                        }
                        else
                        if ( item == 1 ) {
                            DataController.getInstance().deleteSale( model.id );
                            updateList();
                        }
                    }

                });

                AlertDialog alert = builder.create();

                alert.show();

                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode ==  RequestCodes.CREATE_EDIT_SALE_REQUEST_CODE){
                updateList();
            }
        }
    }

    private void updateList()
    {
        salesData.clear();
        salesData.addAll(  DataController.getInstance().getAllSales() );
        contactListAdapter.notifyDataSetChanged();
    }

}
