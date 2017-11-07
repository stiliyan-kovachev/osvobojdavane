package com.stiliyan.phonebook.phonebook.data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String database_name = "carsaleDB";
    private static final String client_table_name = "client";
    private static final String customer_table_name = "customer";
    private static final String car_table_name = "car";
    private static final String sale_table_name = "sale";

    private static final int table_version    = 1;

    public  static  final String client_id = "contact_id";
    public  static  final String key_client_name    = "cl_name";
    public  static  final String key_customer_name    = "cs_name";
    public  static  final String key_address = "address";
    public  static  final String key_client_phone   = "cl_phone";
    public  static  final String key_customer_phone   = "cs_phone";
    public  static  final String customer_id = "customer_id";
    public  static  final String key_position = "position";
    public  static  final String car_id       = "car_id";
    public  static  final String key_brand     = "brand";
    public  static  final String key_model     = "model";
    public  static  final String key_year     = "year";
    public  static  final String key_color     = "color";
    public  static  final String key_kilometers     = "kilometers";
    public  static  final String key_price     = "price";
    public  static  final String sale_id     = "sale_id";
    public  static  final String key_saledate   = "saldate";

    private SQLiteDatabase db;


    public DataBase( Activity context ) {
        super( context, database_name, null, table_version );
    }


    public void addClient(ContentValues client) {
        open();
        db.insert(client_table_name, null, client );
        close();
    }

    public void addCustomer(ContentValues client) {
        open();
        db.insert(customer_table_name, null, client );
        close();
    }
    public void addCar(ContentValues client) {
        open();
        db.insert(car_table_name, null, client );
        close();
    }

    public void addSale(ContentValues client) {
        open();
        db.insert(sale_table_name, null, client );
        close();
    }

    public void updateSale(int id, ContentValues contact ) {
        open();

        db.update(sale_table_name, contact, sale_id +  " = '" + id + "'", null );

        close();
    }

    public Cursor getAllClients()
    {
        open();

        Cursor cursor =  db.query(client_table_name, new String[]{client_id, key_client_name},null, null, null, null, null );

//        close();

        return  cursor;

    }

    public Cursor getAllCustomers()
    {
        open();

        Cursor cursor =  db.query(customer_table_name, new String[]{customer_id, key_customer_name},null, null, null, null, null );

//        close();

        return  cursor;

    }

    public Cursor getAllCars()
    {
        open();

        Cursor cursor =  db.query(car_table_name, new String[]{car_id, key_brand},null, null, null, null, null );

//        close();

        return  cursor;

    }

    public Cursor getAllSales()
    {
        open();

        Cursor cursor = db.rawQuery( "select " + "*" + " from " +sale_table_name +
                " inner join " +client_table_name +" on " + sale_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +customer_table_name +" on " + sale_table_name +"."  + customer_id + " = "+customer_table_name +"." + customer_id +
                " inner join " +car_table_name +" on " + sale_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ";", null );

//        close();

        return  cursor;

    }

    public void deleteSale( int id )
    {
        open();

//        db.rawQuery("delete from " + client_table_name + " " + "where " + key_id + " = '" + id + "'", null );
        db.delete(sale_table_name, sale_id + "=" + id, null );
        close();
    }

    public Cursor getSaleById( int id )
    {
        open();

        Cursor cursor = db.rawQuery( "select " + "*" + " from " +sale_table_name + " " + "inner join " +customer_table_name +" on " + sale_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +";", null );
        return  cursor;

    }

    public boolean hasoCountries()
    {
        open();

        int count = 0;
        Cursor c = db.rawQuery( "select count(*) from " + customer_table_name, null );
        if( c.moveToFirst() ) {
            count = c.getInt( 0 );
        }
        close();

        return count > 0;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ client_table_name + " (" +
                client_id + " integer primary key autoincrement," +
                key_client_name + " text not null, " +
                key_address + " text not null, " +
                key_client_phone + " text not null " + ");");

        db.execSQL("create table "+ customer_table_name + " (" +
                customer_id + " integer primary key autoincrement," +
                key_customer_name + " text not null, " +
                key_position + " text not null, " +
                key_customer_phone + " text not null " + ");");

        db.execSQL("create table "+ car_table_name + " (" +
                car_id + " integer primary key autoincrement," +
                key_brand + " text not null, " +
                key_model + " text not null, " +
                key_year + " integer, " +
                key_kilometers + " integer, " +
                key_price + " integer, " +
                key_color + " text not null " + ");");

        db.execSQL("create table "+ sale_table_name + " (" +
                sale_id + " integer primary key autoincrement," +
                client_id + " integer, " +
                customer_id + " integer, " +
                key_saledate + " integer, " +
                car_id + " integer, " +
                "FOREIGN KEY("+client_id+") REFERENCES "+client_table_name+"("+client_id+")" +
                "FOREIGN KEY("+customer_id+") REFERENCES "+customer_table_name+"("+customer_id+")" +
                "FOREIGN KEY("+car_id+") REFERENCES "+car_table_name+"("+car_id+")" +
                ");");
    }

    @Override
    public void onUpgrade( SQLiteDatabase sqLiteDatabase, int i, int i1 ) {

    }

    public void open() throws SQLException {
        db = getWritableDatabase();
    }

    public void close() throws SQLException
    {
        db.close();
    }


}
