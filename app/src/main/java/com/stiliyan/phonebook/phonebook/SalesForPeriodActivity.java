package com.stiliyan.phonebook.phonebook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.stiliyan.phonebook.phonebook.adapter.ContactListAdapter;
import com.stiliyan.phonebook.phonebook.data.DataController;
import com.stiliyan.phonebook.phonebook.data.SaleVO;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SalesForPeriodActivity extends AppCompatActivity {

    private Button fromBtn;
    private TextView fromTW;
    private Button toBtn;
    private TextView toTW;
    private Button sortBtn;

    private ContactListAdapter contactListAdapter;
    private ListView salesList;

    private Date from;
    private Date to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_for_period);

        fromBtn = (Button) findViewById(R.id.fromBtn);
        fromTW = (TextView) findViewById(R.id.from);
        toBtn = (Button) findViewById(R.id.toBtn);
        toTW = (TextView) findViewById(R.id.to);
        sortBtn = (Button) findViewById(R.id.sort);

        salesList = (ListView) findViewById( R.id.salesList );

        fromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        fromTW.setText( date.toString());
                        from = date;
                    }
                });
            }
        });

        toBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        toTW.setText( date.toString());
                        to = date;
                    }
                });
            }
        });

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( from == null ){
                    fromTW.setError( "please set from time" );
                    return;
                }
                else
                    fromTW.setError(null);

                if ( to == null ){
                    toTW.setError( "please set to time" );
                    return;
                }
                else
                  toTW.setError(null);

                List<SaleVO> sales = DataController.getInstance().salesForPeriod(from.getTime(), to.getTime() );
                contactListAdapter = new ContactListAdapter( SalesForPeriodActivity.this, R.layout.contact_list_item_renderer, sales );
                salesList.setAdapter( contactListAdapter );
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private TimeSet timeset;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }


        public void setTmeCallback(TimeSet timeset)
        {
            this.timeset = timeset;
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            if ( timeset != null )
            {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);
                timeset.onTimeSet( cal.getTime() );
            }
        }
    }

    public interface TimeSet
    {
        public void onTimeSet( Date date );
    }

}
