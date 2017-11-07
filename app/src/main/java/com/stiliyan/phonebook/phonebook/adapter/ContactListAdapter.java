package com.stiliyan.phonebook.phonebook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stiliyan.phonebook.phonebook.R;
import com.stiliyan.phonebook.phonebook.data.SaleVO;

import java.util.List;

public class ContactListAdapter extends ArrayAdapter<SaleVO> {

    public ContactListAdapter( Context context, int textViewResourceId ) {
        super( context, textViewResourceId );
    }

    public ContactListAdapter( Context context, int resource, List<SaleVO> items ) {
        super( context, resource, items );
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        View v = convertView;

        if ( v == null ) {
            LayoutInflater vi;
            vi = LayoutInflater.from( getContext() );
            v = vi.inflate( R.layout.contact_list_item_renderer, null );
        }

        SaleVO p = getItem( position );
        if ( p != null ) {
            TextView clientName = ( TextView ) v.findViewById(R.id.client_name);
            TextView saleDate = ( TextView ) v.findViewById(R.id.sale_date);
            TextView clientPhone = ( TextView ) v.findViewById(R.id.client_phone);
            TextView clienAddress = ( TextView ) v.findViewById(R.id.client_address);
            TextView customerName = ( TextView ) v.findViewById(R.id.customer_name);
            TextView customerPhone = ( TextView ) v.findViewById(R.id.customer_phone);
            TextView customerPosition = ( TextView ) v.findViewById(R.id.customer_position);
            TextView carBrand = ( TextView ) v.findViewById(R.id.car_brand);
            TextView carModel = ( TextView ) v.findViewById(R.id.car_model);
            TextView carYear = ( TextView ) v.findViewById(R.id.car_year);
            TextView carColor = ( TextView ) v.findViewById(R.id.car_color);
            TextView carKilometers = ( TextView ) v.findViewById(R.id.car_kilometers);
            TextView carPrice = ( TextView ) v.findViewById(R.id.car_price);

            if (saleDate != null) {
                saleDate.setText( p.saledate.toString() );
            }
            if (clientName != null) {
                clientName.setText( p.client.name );
            }
            if (clientPhone != null) {
                clientPhone.setText( p.client.phone );
            }
            if (clienAddress != null) {
                clienAddress.setText( p.client.address );
            }
            if (customerName != null) {
                customerName.setText( p.customer.name );
            }
            if (customerPhone != null) {
                customerPhone.setText( p.customer.phone );
            }
            if (customerPosition != null) {
                customerPosition.setText( p.customer.position );
            }
            if (carBrand != null) {
                carBrand.setText( p.car.brand );
            }
            if (carModel != null) {
                carModel.setText( p.car.model );
            }
            if (carYear != null) {
                carYear.setText( String.valueOf(p.car.year) );
            }
            if (carColor != null) {
                carColor.setText( p.car.color );
            }
            if (carKilometers != null) {
                carKilometers.setText( String.valueOf(p.car.kilometers) );
            }
            if (carPrice != null) {
                carPrice.setText( String.valueOf(p.car.price) );
            }

        }

        return v;
    }
}
