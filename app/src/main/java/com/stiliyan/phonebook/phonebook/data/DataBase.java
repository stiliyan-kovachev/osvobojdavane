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

    public Cursor getSaleById( int id )
    {
        open();

        Cursor cursor = db.rawQuery( "select " + "*" + " from " +sale_table_name +
                " inner join " +client_table_name +" on " + sale_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +customer_table_name +" on " + sale_table_name +"."  + customer_id + " = "+customer_table_name +"." + customer_id +
                " inner join " +car_table_name +" on " + sale_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ";" + " where " + sale_id + " = " + id + ";", null );

//        close();

        return  cursor;

    }

    public  Cursor saledCarsFromCustomerOrdered( int customerId ){
        open();
        Cursor cursor = db.rawQuery( "select " + car_id +"," + key_brand +"," + key_model + "," + key_year + "," + key_color + "," + key_kilometers + "," +key_price +
                " from " +sale_table_name +
                " inner join " +customer_table_name +" on " + sale_table_name +"."  + customer_id + " = "+customer_table_name +"." + customer_id +
                " inner join " +car_table_name +" on " + sale_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ";" + " where " + customer_id + " = " + customerId + ";" + " order by " + key_saledate + " asc;", null );

        return  cursor;
    }

    public  Cursor lastFiveSalesOrderedByPrice(){
        open();
        Cursor cursor = db.rawQuery( "select * from (select " + "*" + " from " +sale_table_name +
                " inner join " +client_table_name +" on " + sale_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +customer_table_name +" on " + sale_table_name +"."  + customer_id + " = "+customer_table_name +"." + customer_id +
                " inner join " +car_table_name +" on " + sale_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
               " order by " + key_price + " asc)" + " limit 5;", null );

        return  cursor;
    }

    public  Cursor boughtCarsByClient( int clientId ){
        open();
        Cursor cursor = db.rawQuery( "select " + car_id +"," + key_brand +"," + key_model + "," + key_year + "," + key_color + "," + key_kilometers + "," +key_price +
                " from " +sale_table_name +
                " inner join " +client_table_name +" on " + sale_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +car_table_name +" on " + sale_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ";" + " where " + client_id + " = " + clientId + ";" , null );

        return  cursor;
    }

    public  Cursor salesForPeriod( long from, long to ){
        open();
        Cursor cursor = db.rawQuery( "select " + "*" + " from " +sale_table_name +
                " inner join " +client_table_name +" on " + sale_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +customer_table_name +" on " + sale_table_name +"."  + customer_id + " = "+customer_table_name +"." + customer_id +
                " inner join " +car_table_name +" on " + sale_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                " order by " + key_saledate + " between " + from + " and " + to + ";", null );

        return  cursor;
    }

    public void deleteSale( int id )
    {
        open();

//        db.rawQuery("delete from " + client_table_name + " " + "where " + key_id + " = '" + id + "'", null );
        db.delete(sale_table_name, sale_id + "=" + id, null );
        close();
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
                key_saledate + " long, " +
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
