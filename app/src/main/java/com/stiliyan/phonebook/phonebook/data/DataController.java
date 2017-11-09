package com.stiliyan.phonebook.phonebook.data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataController {

    private DataBase db;
    private static DataController instance;

    public static DataController getInstance() {
        if ( instance == null )
            instance = new DataController();

        return instance;
    }

    private DataController() {

    }

    public void setContext( Activity context ) {
        db = new DataBase( context );
    }

    public List<ClientVO> getClients() {
        Cursor c = db.getAllClients();
        List<ClientVO> clients = new ArrayList<ClientVO>();
        if ( c.moveToFirst() )
            do {
                ClientVO client = new ClientVO();
                client.id = c.getInt(c.getColumnIndex(DataBase.client_id));
                client.name = c.getString(c.getColumnIndex(DataBase.key_client_name));
//                client.address = c.getString(c.getColumnIndex(DataBase.key_address));
//                client.phone = c.getString(c.getColumnIndex(DataBase.key_client_phone));
                clients.add(client);
            }
            while (c.moveToNext() );
        db.close();

        return clients;
    }
    public List<CustomerVO> getCustomers() {
        Cursor c = db.getAllCustomers();
        List<CustomerVO> clients = new ArrayList<CustomerVO>();
        if ( c.moveToFirst() )
            do {
                CustomerVO client = new CustomerVO();
                client.id = c.getInt(c.getColumnIndex(DataBase.customer_id));
                client.name = c.getString(c.getColumnIndex(DataBase.key_customer_name));
                clients.add(client);
            }
            while (c.moveToNext() );
        db.close();

        return clients;
    }
    public List<CarVO> getCars() {
        Cursor c = db.getAllCars();
        List<CarVO> clients = new ArrayList<CarVO>();
        if ( c.moveToFirst() )
            do {
                CarVO client = new CarVO();
                client.id = c.getInt(c.getColumnIndex(DataBase.car_id));
                client.brand = c.getString(c.getColumnIndex(DataBase.key_brand));
                clients.add(client);
            }
            while (c.moveToNext() );
        db.close();

        return clients;
    }

    public List<CreditCardVO> getCreditCards() {
        Cursor c = db.getAllCrediCards();
        List<CreditCardVO> clients = new ArrayList<CreditCardVO>();
        if ( c.moveToFirst() )
            do {
                CreditCardVO client = new CreditCardVO();
                client.id = c.getInt(c.getColumnIndex(DataBase.credit_card_id));
                client.number = c.getLong(c.getColumnIndex(DataBase.key_number));
                clients.add(client);
            }
            while (c.moveToNext() );
        db.close();

        return clients;
    }

    public List<InsuranceVO> getInsurances() {
        Cursor c = db.getAllInsurences();
        List<InsuranceVO> clients = new ArrayList<InsuranceVO>();
        if ( c.moveToFirst() )
            do {
                InsuranceVO client = new InsuranceVO();
                client.id = c.getInt(c.getColumnIndex(DataBase.insurance_id));
                client.insurer = c.getString(c.getColumnIndex(DataBase.key_insurer_name));
                clients.add(client);
            }
            while (c.moveToNext() );
        db.close();

        return clients;
    }

    public void updateSale( SaleVO sale ) {

        ContentValues values = new ContentValues();
//        values.put( DataBase.sale_id, sale.id );
        values.put ( DataBase.key_saledate, sale.saledate.getTime() );
        values.put( DataBase.client_id, sale.client.id );
        values.put( DataBase.customer_id, sale.customer.id );
        values.put( DataBase.car_id, sale.car.id );
        values.put( DataBase.credit_card_id, sale.creditCard.id );
        values.put( DataBase.insurance_id, sale.insuranceType.id );

        db.updateSale( sale.id, values );
    }

    public List<SaleVO> getAllSales() {
        List<SaleVO> allContacts = new ArrayList<>();

        Cursor c = db.getAllSales();
        if ( c.moveToFirst() )
            do {

                SaleVO model = new SaleVO();
                model.id =  ( c.getInt(c.getColumnIndex( DataBase.sale_id) ) );
                model.saledate = new Date( ( c.getLong(c.getColumnIndex( DataBase.key_saledate) ) ) );

                model.client = new ClientVO();
                model.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                model.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                model.client.address = ( c.getString(c.getColumnIndex( DataBase.key_address ) ) );
                model.client.phone = ( c.getString(c.getColumnIndex( DataBase.key_client_phone ) ) );

                model.customer = new CustomerVO();
                model.customer.id = ( c.getInt(c.getColumnIndex( DataBase.customer_id ) ) );
                model.customer.name = ( c.getString(c.getColumnIndex( DataBase.key_customer_name ) ) );
                model.customer.phone = ( c.getString(c.getColumnIndex( DataBase.key_customer_phone ) ) );
                model.customer.position = ( c.getString(c.getColumnIndex( DataBase.key_position ) ) );

                model.car = new CarVO();
                model.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                model.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_brand ) ) );
                model.car.model = ( c.getString(c.getColumnIndex( DataBase.key_model ) ) );
                model.car.year = ( c.getInt(c.getColumnIndex( DataBase.key_year ) ) );
                model.car.color = ( c.getString(c.getColumnIndex( DataBase.key_color ) ) );
                model.car.kilometers = ( c.getInt(c.getColumnIndex( DataBase.key_kilometers ) ) );
                model.car.price = ( c.getInt(c.getColumnIndex( DataBase.key_price ) ) );

               model.creditCard = new CreditCardVO();
               model.creditCard.id = ( c.getInt(c.getColumnIndex( DataBase.credit_card_id ) ) );
               model.creditCard.number = ( c.getLong(c.getColumnIndex( DataBase.key_number ) ) );
               model.creditCard.serviceCompany = ( c.getString(c.getColumnIndex( DataBase.key_services_company ) ) );
               model.creditCard.expirationDate = new Date ( c.getLong(c.getColumnIndex( DataBase.key_expiration ) ) );

               model.insuranceType = new InsuranceVO();
               model.insuranceType.id = ( c.getInt(c.getColumnIndex( DataBase.insurance_id ) ) );
               model.insuranceType.insurer = ( c.getString(c.getColumnIndex( DataBase.key_insurer_name ) ) );
               model.insuranceType.value = ( c.getInt(c.getColumnIndex( DataBase.key_insurance_value ) ) );

                allContacts.add( model);
            }
            while ( c.moveToNext() );

        db.close();

        return allContacts;
    }


    public  void deleteSale( int id )
    {
        db.deleteSale( id );
    }

    public void addCleint(ClientVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.key_client_name, vo.name );
        values.put( DataBase.key_address, vo.address);
        values.put( DataBase.key_client_phone, vo.phone);
        db.addClient( values );
    }

    public void addCustomer(CustomerVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.key_customer_name, vo.name );
        values.put( DataBase.key_position, vo.position);
        values.put( DataBase.key_customer_phone, vo.phone);
        db.addCustomer( values );
    }

    public void addCar(CarVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.key_brand, vo.brand );
        values.put( DataBase.key_model, vo.model );
        values.put( DataBase.key_year, vo.year);
        values.put( DataBase.key_color, vo.color);
        values.put( DataBase.key_kilometers, vo.kilometers);
        values.put( DataBase.key_price, vo.price);
        db.addCar( values );
    }

    public void addCreditCard(CreditCardVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.key_services_company, vo.serviceCompany );
        values.put( DataBase.key_number, vo.number );
        values.put( DataBase.key_expiration, vo.expirationDate.getTime());
        db.addCreditCard( values );
    }

    public void addInsurance(InsuranceVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.key_insurer_name, vo.insurer );
        values.put( DataBase.key_insurance_value, vo.value );
        db.addInsurance( values );
    }

    public void addSale(SaleVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.client_id, vo.client.id );
        values.put( DataBase.customer_id, vo.customer.id );
        values.put( DataBase.car_id, vo.car.id);
        values.put( DataBase.key_saledate, vo.saledate.getTime());
        values.put( DataBase.credit_card_id, vo.creditCard.id );
        values.put( DataBase.insurance_id, vo.insuranceType.id );
        db.addSale( values );
    }

    public SaleVO getSaleById(int saleID) {
        SaleVO sale = new SaleVO();

        Cursor c = db.getSaleById( saleID );
        if ( c.moveToFirst() )
            do {
                sale.id =  ( c.getInt(c.getColumnIndex( DataBase.sale_id) ) );
                long date = ( c.getLong(c.getColumnIndex( DataBase.key_saledate) ) );
                sale.saledate = new Date( date );

                sale.client = new ClientVO();
                sale.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                sale.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                sale.client.address = ( c.getString(c.getColumnIndex( DataBase.key_address ) ) );
                sale.client.phone = ( c.getString(c.getColumnIndex( DataBase.key_client_phone ) ) );

                sale.customer = new CustomerVO();
                sale.customer.id = ( c.getInt(c.getColumnIndex( DataBase.customer_id ) ) );
                sale.customer.name = ( c.getString(c.getColumnIndex( DataBase.key_customer_name ) ) );
                sale.customer.phone = ( c.getString(c.getColumnIndex( DataBase.key_customer_phone ) ) );
                sale.customer.position = ( c.getString(c.getColumnIndex( DataBase.key_position ) ) );

                sale.car = new CarVO();
                sale.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                sale.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_brand ) ) );
                sale.car.model = ( c.getString(c.getColumnIndex( DataBase.key_model ) ) );
                sale.car.year = ( c.getInt(c.getColumnIndex( DataBase.key_year ) ) );
                sale.car.color = ( c.getString(c.getColumnIndex( DataBase.key_color ) ) );
                sale.car.kilometers = ( c.getInt(c.getColumnIndex( DataBase.key_kilometers ) ) );
                sale.car.price = ( c.getInt(c.getColumnIndex( DataBase.key_price ) ) );

                sale.creditCard = new CreditCardVO();
                sale.creditCard.id = ( c.getInt(c.getColumnIndex( DataBase.credit_card_id ) ) );
                sale.creditCard.number = ( c.getLong(c.getColumnIndex( DataBase.key_number ) ) );
                sale.creditCard.serviceCompany = ( c.getString(c.getColumnIndex( DataBase.key_services_company ) ) );
                sale.creditCard.expirationDate = new Date ( c.getLong(c.getColumnIndex( DataBase.key_expiration ) ) );

                sale.insuranceType = new InsuranceVO();
                sale.insuranceType.id = ( c.getInt(c.getColumnIndex( DataBase.insurance_id ) ) );
                sale.insuranceType.insurer = ( c.getString(c.getColumnIndex( DataBase.key_insurer_name ) ) );
                sale.insuranceType.value = ( c.getInt(c.getColumnIndex( DataBase.key_insurance_value ) ) );
            }
            while ( c.moveToNext() );

        db.close();

        return sale;
    }

    public List<CarVO> saledCarsFromCustomerOrdered( int customerId ){
        List<CarVO> models = new ArrayList<>();

        Cursor c = db.saledCarsFromCustomerOrdered(customerId );
        if ( c.moveToFirst() )
            do {
                CarVO model = new CarVO();
                model.id = c.getInt(c.getColumnIndex(DataBase.car_id));
                model.brand = c.getString(c.getColumnIndex(DataBase.key_brand));
                model.model = c.getString(c.getColumnIndex(DataBase.key_model));
                model.year = c.getInt(c.getColumnIndex(DataBase.key_year));
                model.color = c.getString(c.getColumnIndex(DataBase.key_color));
                model.kilometers = c.getInt(c.getColumnIndex(DataBase.key_kilometers));
                model.price = c.getInt(c.getColumnIndex(DataBase.key_price));

                models.add(model);
            }
            while (c.moveToNext() );
        db.close();

        return models;
    }

    public List<CarVO> boughtCarsByClient( int clientId ){
        List<CarVO> models = new ArrayList<>();

        Cursor c = db.boughtCarsByClient(clientId );
        if ( c.moveToFirst() )
            do {
                CarVO model = new CarVO();
                model.id = c.getInt(c.getColumnIndex(DataBase.car_id));
                model.brand = c.getString(c.getColumnIndex(DataBase.key_brand));
                model.model = c.getString(c.getColumnIndex(DataBase.key_model));
                model.year = c.getInt(c.getColumnIndex(DataBase.key_year));
                model.color = c.getString(c.getColumnIndex(DataBase.key_color));
                model.kilometers = c.getInt(c.getColumnIndex(DataBase.key_kilometers));
                model.price = c.getInt(c.getColumnIndex(DataBase.key_price));

                models.add(model);
            }
            while (c.moveToNext() );
        db.close();

        return models;
    }

    public List<CarVO> carsInsuratedWithType( int insuranceId ){
        List<CarVO> models = new ArrayList<>();

        Cursor c = db.carsInsuratedWithType( insuranceId );
        if ( c.moveToFirst() )
            do {
                CarVO model = new CarVO();
                model.id = c.getInt(c.getColumnIndex(DataBase.car_id));
                model.brand = c.getString(c.getColumnIndex(DataBase.key_brand));
                model.model = c.getString(c.getColumnIndex(DataBase.key_model));
                model.year = c.getInt(c.getColumnIndex(DataBase.key_year));
                model.color = c.getString(c.getColumnIndex(DataBase.key_color));
                model.kilometers = c.getInt(c.getColumnIndex(DataBase.key_kilometers));
                model.price = c.getInt(c.getColumnIndex(DataBase.key_price));

                models.add(model);
            }
            while (c.moveToNext() );
        db.close();

        return models;
    }

    public List<SaleVO> lastFiveSalesOrderedByPrice(){
        List<SaleVO> models = new ArrayList<>();
        Cursor c = db.lastFiveSalesOrderedByPrice();

        if ( c.moveToFirst() )
            do {

                SaleVO model = new SaleVO();
                model.id =  ( c.getInt(c.getColumnIndex( DataBase.sale_id) ) );
                model.saledate = new Date( ( c.getLong(c.getColumnIndex( DataBase.key_saledate) ) ) );

                model.client = new ClientVO();
                model.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                model.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                model.client.address = ( c.getString(c.getColumnIndex( DataBase.key_address ) ) );
                model.client.phone = ( c.getString(c.getColumnIndex( DataBase.key_client_phone ) ) );

                model.customer = new CustomerVO();
                model.customer.id = ( c.getInt(c.getColumnIndex( DataBase.customer_id ) ) );
                model.customer.name = ( c.getString(c.getColumnIndex( DataBase.key_customer_name ) ) );
                model.customer.phone = ( c.getString(c.getColumnIndex( DataBase.key_customer_phone ) ) );
                model.customer.position = ( c.getString(c.getColumnIndex( DataBase.key_position ) ) );

                model.car = new CarVO();
                model.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                model.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_brand ) ) );
                model.car.model = ( c.getString(c.getColumnIndex( DataBase.key_model ) ) );
                model.car.year = ( c.getInt(c.getColumnIndex( DataBase.key_year ) ) );
                model.car.color = ( c.getString(c.getColumnIndex( DataBase.key_color ) ) );
                model.car.kilometers = ( c.getInt(c.getColumnIndex( DataBase.key_kilometers ) ) );
                model.car.price = ( c.getInt(c.getColumnIndex( DataBase.key_price ) ) );

                model.creditCard = new CreditCardVO();
                model.creditCard.id = ( c.getInt(c.getColumnIndex( DataBase.credit_card_id ) ) );
                model.creditCard.number = ( c.getInt(c.getColumnIndex( DataBase.key_number ) ) );
                model.creditCard.serviceCompany = ( c.getString(c.getColumnIndex( DataBase.key_services_company ) ) );
                model.creditCard.expirationDate = new Date ( c.getLong(c.getColumnIndex( DataBase.key_expiration ) ) );

                model.insuranceType = new InsuranceVO();
                model.insuranceType.id = ( c.getInt(c.getColumnIndex( DataBase.insurance_id ) ) );
                model.insuranceType.insurer = ( c.getString(c.getColumnIndex( DataBase.key_insurer_name ) ) );
                model.insuranceType.value = ( c.getInt(c.getColumnIndex( DataBase.key_insurance_value ) ) );

                models.add( model);
            }
            while ( c.moveToNext() );

        db.close();

        return models;
    }

    public List<SaleVO> salesForPeriod( long from, long to ){
        List<SaleVO> models = new ArrayList<>();
        Cursor c = db.salesForPeriod( from, to );

        if ( c.moveToFirst() )
            do {

                SaleVO model = new SaleVO();
                model.id =  ( c.getInt(c.getColumnIndex( DataBase.sale_id) ) );
                model.saledate = new Date( ( c.getLong(c.getColumnIndex( DataBase.key_saledate) ) ) );

                model.client = new ClientVO();
                model.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                model.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                model.client.address = ( c.getString(c.getColumnIndex( DataBase.key_address ) ) );
                model.client.phone = ( c.getString(c.getColumnIndex( DataBase.key_client_phone ) ) );

                model.customer = new CustomerVO();
                model.customer.id = ( c.getInt(c.getColumnIndex( DataBase.customer_id ) ) );
                model.customer.name = ( c.getString(c.getColumnIndex( DataBase.key_customer_name ) ) );
                model.customer.phone = ( c.getString(c.getColumnIndex( DataBase.key_customer_phone ) ) );
                model.customer.position = ( c.getString(c.getColumnIndex( DataBase.key_position ) ) );

                model.car = new CarVO();
                model.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                model.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_brand ) ) );
                model.car.model = ( c.getString(c.getColumnIndex( DataBase.key_model ) ) );
                model.car.year = ( c.getInt(c.getColumnIndex( DataBase.key_year ) ) );
                model.car.color = ( c.getString(c.getColumnIndex( DataBase.key_color ) ) );
                model.car.kilometers = ( c.getInt(c.getColumnIndex( DataBase.key_kilometers ) ) );
                model.car.price = ( c.getInt(c.getColumnIndex( DataBase.key_price ) ) );

                model.creditCard = new CreditCardVO();
                model.creditCard.id = ( c.getInt(c.getColumnIndex( DataBase.credit_card_id ) ) );
                model.creditCard.number = ( c.getLong(c.getColumnIndex( DataBase.key_number ) ) );
                model.creditCard.serviceCompany = ( c.getString(c.getColumnIndex( DataBase.key_services_company ) ) );
                model.creditCard.expirationDate = new Date ( c.getLong(c.getColumnIndex( DataBase.key_expiration ) ) );

                model.insuranceType = new InsuranceVO();
                model.insuranceType.id = ( c.getInt(c.getColumnIndex( DataBase.insurance_id ) ) );
                model.insuranceType.insurer = ( c.getString(c.getColumnIndex( DataBase.key_insurer_name ) ) );
                model.insuranceType.value = ( c.getInt(c.getColumnIndex( DataBase.key_insurance_value ) ) );

                models.add( model);
            }
            while ( c.moveToNext() );

        db.close();

        return models;
    }
}
