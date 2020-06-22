package com.example.blitz_t.Models;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.AccountHelper;
import com.example.blitz_t.Api.AgencyHelper;
import com.example.blitz_t.Api.CityHelper;
import com.example.blitz_t.Api.CountryHelper;
import com.example.blitz_t.Api.CustomerHelper;
import com.example.blitz_t.Api.MicrofinanceHelper;
import com.example.blitz_t.Api.TransactionHelper;
import com.example.blitz_t.Controllers.AccountPagerAdapter;
import com.example.blitz_t.Controllers.AccountRecyclerAdapter;
import com.example.blitz_t.Controllers.AgencyMicrofinanceRecyclerAdapter;
import com.example.blitz_t.Controllers.MicrofinanceRecyclerAdapter;
import com.example.blitz_t.Controllers.TransactionRecyclerAdapter;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Agency.Agency;
import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Transaction.Transaction;
import com.example.blitz_t.R;
import com.google.firebase.database.DataSnapshot;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Model {

    private static MicrofinanceHelper sMicrofinanceHelper = new MicrofinanceHelper(new Microfinance());

    private static CustomerHelper sCustomerHelper = new CustomerHelper(new Customer());

    private static CountryHelper sCountryHelper = new CountryHelper(new Country());

    private static CityHelper sCityHelper = new CityHelper(new City());

    private static AgencyHelper sAgencyHelper = new AgencyHelper(new Agency());

    private static AccountHelper sAccountHelper = new AccountHelper(new Account());

    private static TransactionHelper sTransactionHelper = new TransactionHelper(new Transaction());

    private static int numberNotification;

    public static String ConvertToString(String dateInString) throws ParseException {
        String format = "YYYY-MM-dd";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat( format );
        Date date = formatter.parse(dateInString);
        return formatter.format(date);
    }

    public static Date ConvertToDate(String dateInString) throws ParseException {
        String format = "YYYY-MM-dd";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat( format );
        return formatter.parse(getDateUtil(dateInString));
    }

    public static String removeTDate(String dateInString){
        return dateInString.replace("T", " ");
    }

    public static String getDateUtil(String dateInString){
        return removeTDate(dateInString).split(" ")[0];
    }

    public static String getBirthDateSelect(int dayOfMonth, int mounth, int year) {
        String DAY = String.valueOf(dayOfMonth).length() == 1 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
        String MOUNT = String.valueOf(mounth).length() == 1 ? "0" + mounth : String.valueOf(mounth);
        return year + "-" + MOUNT + "-" + DAY;
    }

    public static double getAmountAndInterest(double amount, double interest){
        return amount + (interest * (amount / 100) );
    }

    public static String getNewId(){
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static void saveFromPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        String contentPreference = new Gson().toJson(object);
        SharedPreferences.Editor editor = contextWrapper.getSharedPreferences(preferenceFileKey , Context.MODE_PRIVATE).edit();
        editor.putString(filePreference , contentPreference);
        editor.apply();
    }

    private static void addFromPreferenceNotification ( Object key, Object value,  ContextWrapper contextWrapper ) {
        Dictionary dictionaryNotification = contentPreferenceNotification(contextWrapper);
        dictionaryNotification.put(key, value);
        String contentPreference = new Gson().toJson(dictionaryNotification);
        SharedPreferences.Editor editor = contextWrapper.getSharedPreferences(contextWrapper.getString(R.string.PREFERENCE_FILE_KEY) , Context.MODE_PRIVATE).edit();
        editor.putString(contextWrapper.getString(R.string.PREFERENCE_FILE_NOTIFICATION) , contentPreference);
        editor.apply();
    }

    public static void clearFromPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        String contentPreference = new Gson().toJson(object);
        SharedPreferences.Editor editor = contextWrapper.getSharedPreferences(preferenceFileKey , Context.MODE_PRIVATE).edit();
        editor.putString(filePreference , contentPreference);
        editor.apply();
        editor.clear();
    }

    public static Object contentPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        SharedPreferences sharedPref = contextWrapper.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        String member_string = sharedPref.getString(filePreference, null);
        if(member_string != null) {
            return new Gson().fromJson(member_string, object.getClass());
        }
        return null;
    }

    private static Dictionary<Object, Object> contentPreferenceNotification ( ContextWrapper contextWrapper ) {
        SharedPreferences sharedPref = contextWrapper.getSharedPreferences(contextWrapper.getString(R.string.PREFERENCE_FILE_KEY), Context.MODE_PRIVATE);
        String member_string = sharedPref.getString(contextWrapper.getString(R.string.PREFERENCE_FILE_NOTIFICATION), null);
        Dictionary<Object, Object> dictionary = new Hashtable<>();
        if(member_string != null) {
            return new Gson().fromJson(member_string, dictionary.getClass());
        }
        return new Hashtable();
    }

//    public static String currentDateString(){
//        String format = "YYYY-MM-dd H:mm:ss";
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat( format );
//        Date date = new Date();
//        return formatter.format(date);
//    }

    public static String currentDateString(){
        Date date = new Date();
        String format = "YYYY-MM-dd H:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    public static Date currentDate() throws ParseException {
        String format = "YYYY-MM-dd H:mm:ss";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat( format );
        Date date = new Date();
        return formatter.parse(formatter.format(date));
    }

    public static void reverseList(ArrayList objects){
        ArrayList results = new ArrayList<>();
        for (int i = objects.size() - 1; i >= 0; i--){
            results.add(objects.get(i));
        }
        objects.clear();
        objects.addAll(results);
    }

    public static void reverseList(ArrayList objects, int limit){
        ArrayList results = new ArrayList<>();
        ArrayList results_ = new ArrayList<>();
        for (int i = objects.size() - 1; i >= 0; i--){
            results.add(objects.get(i));
        }

        results_.addAll(results);

        if(limit > 0 && results_.size() > 0) {
            results_.clear();
            for (int i = 0; i < limit; i++) {
                results_.add(results.get(i));
                if ( (i + 1) >= results.size() )
                    break;
            }
        }

        objects.clear();
        objects.addAll(results_);
    }

//    public static boolean isOnline(Context context) {
//        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        assert cm != null;
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkAgenciesMicrofinance( final RecyclerView recyclerView, final Context context, final String name, final Microfinance microfinance, final Activity activity){

        RxFirebaseDatabase.observeValueEvent(sAgencyHelper.getAgencies())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    dataSnapshot -> displayAgenciesMicrofinance(dataSnapshot , recyclerView , context , name , microfinance , activity),
                    throwable -> Toast.makeText(context, activity.getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
                );

    }


    private static void displayAgenciesMicrofinance ( DataSnapshot dataSnapshot, final RecyclerView recyclerView, final Context context, final String name, final Microfinance microfinance, final Activity activity ) {

        final ArrayList<Agency> agencies = new ArrayList<>();
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                data -> {
                    Agency agency = data.getValue(Agency.class);
                    if(agency != null &&
                            (agency.getQuartier().toLowerCase().contains(name.toLowerCase()) ||
                                    agency.getCity().getName().toLowerCase().contains(name.toLowerCase())
                            ) &&
                            agency.getMicrofinance().get_id().equals(microfinance.get_id())){
                        agencies.add(agency);
                    }
                }
            );
            AgencyMicrofinanceRecyclerAdapter adapter = new AgencyMicrofinanceRecyclerAdapter(agencies , context, activity);
            recyclerView.setAdapter(adapter);
        }
    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkAccountCustomer( final ViewPager viewPager, final Context context, final Activity activity, final Customer customer, final Microfinance microfinance, final Account account_, final ArrayList<Account> accounts_){

        RxFirebaseDatabase.observeValueEvent(sAccountHelper.getAccounts())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dataSnapshot -> displayAccountCustomer(dataSnapshot , viewPager, context, activity, customer, microfinance, account_, accounts_),
                        throwable -> Toast.makeText(context, activity.getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
                );
    }


    private static void displayAccountCustomer( DataSnapshot dataSnapshot, final ViewPager viewPager, final Context context, final Activity activity, final Customer customer, final Microfinance microfinance, final Account account_, final ArrayList<Account> accounts_){
        final ArrayList<Account> accounts = new ArrayList<>();
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                data -> {
                    Account account = data.getValue(Account.class);
                    if((account_ == null &&
                            account != null &&
                            account.getCustomer().get_id().equals(customer.get_id()) &&
                            account.getCustomer().getMicrofinance().get_id().equals(microfinance.get_id())
                    ) ||
                            (account_ != null &&
                                    account_.get_id().equals(account.get_id()))
                    ){
                        accounts.add(account);
                        if(account_ != null) {
                            AccountPagerAdapter mAdapter = new AccountPagerAdapter(accounts, context, activity);
                            viewPager.setAdapter(mAdapter);
                            viewPager.setPadding(50, 0, 50, 0);
                            accounts_.clear();
                            accounts_.addAll(accounts);
                            return;
                        }
                    }
                }
            );
        }
        AccountPagerAdapter mAdapter = new AccountPagerAdapter(accounts, context, activity);
        viewPager.setAdapter(mAdapter);
        viewPager.setPadding(50, 0, 50, 0);
        accounts_.clear();
        accounts_.addAll(accounts);
    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkMicrofinances( final RecyclerView recyclerView, final Context context, final String name, final Member member, final Activity activity){
        if(member != null){
            if(member.get_id() != null)
                checkMicrofinancesMember(recyclerView, context, name, member, activity);
            return;
        }

        RxFirebaseDatabase.observeValueEvent(sMicrofinanceHelper.getMicrofinances())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dataSnapshot -> displayMicrofinances(dataSnapshot , recyclerView, context, name, member, activity),
                        throwable -> Toast.makeText(context, activity.getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
                );
    }


    private static void displayMicrofinances( DataSnapshot dataSnapshot, final RecyclerView recyclerView, final Context context, final String name, final Member member, final Activity activity){

        final ArrayList<Microfinance> microfinances = new ArrayList<>();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                    data -> {
                        Microfinance microfinance = data.getValue(Microfinance.class);
                        if ( microfinance != null && microfinance.getNom().toLowerCase().contains(name.toLowerCase()) ) {
                            microfinances.add(microfinance);
                        }
                    }
            );
            MicrofinanceRecyclerAdapter adapter = new MicrofinanceRecyclerAdapter(microfinances , context, activity);
            recyclerView.setAdapter(adapter);
        }
    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    private static void checkMicrofinancesMember( final RecyclerView recyclerView, final Context context, final String name, final Member member, final Activity activity){

        RxFirebaseDatabase.observeValueEvent(sCustomerHelper.getCustomers())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    dataSnapshot -> displayMicrofinancesMember(dataSnapshot , recyclerView, context, name, member, activity),
                    throwable -> Toast.makeText(context, activity.getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
            );

    }


    private static void displayMicrofinancesMember( DataSnapshot dataSnapshot, final RecyclerView recyclerView, final Context context, final String name, final Member member, final Activity activity){

        final ArrayList<Microfinance> microfinances = new ArrayList<>();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                data -> {
                    Customer customer = data.getValue(Customer.class);
                    if(customer != null &&
                        customer.getMicrofinance().getNom().toLowerCase().contains(name.toLowerCase()) &&
                        customer.getMember().get_id().equals(member.get_id())){
                        microfinances.add(customer.getMicrofinance());
                    }
                }
            );
            MicrofinanceRecyclerAdapter adapter = new MicrofinanceRecyclerAdapter(microfinances , context, activity);
            recyclerView.setAdapter(adapter);
        }
    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkTransactionCustomer ( final RecyclerView recyclerView , final Context context , final Customer customer , final Microfinance microfinance , final Activity activity , final String value , final int limit , final Account account , final SwipeRefreshLayout swipe_refresh_recycler_transaction ){

        if(swipe_refresh_recycler_transaction != null)
        {
            swipe_refresh_recycler_transaction.setColorSchemeResources(
                    R.color.colorPrimaryDark,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        RxFirebaseDatabase.observeValueEvent(sTransactionHelper.getTransactions().orderByChild("transaction_date"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    dataSnapshot ->
                            displayTransactionCustomer(dataSnapshot , recyclerView , context , customer , microfinance , activity , value , limit , account , swipe_refresh_recycler_transaction),
                    throwable -> {
                        Toast.makeText(context, activity.getString(R.string.text_operation_failed).concat(" : ").concat(Objects.requireNonNull(throwable.getMessage())), Toast.LENGTH_LONG).show();
//                        checkTransactionCustomer(recyclerView, context , customer , microfinance , activity , value , limit , account ,  swipe_refresh_recycler_transaction);
                    }
            );

    }


    private static void displayTransactionCustomer ( DataSnapshot dataSnapshot, final RecyclerView recyclerView , final Context context , final Customer customer , final Microfinance microfinance , final Activity activity , final String value , final int limit , final Account account , final SwipeRefreshLayout swipe_refresh_recycler_transaction ){
        final ArrayList<Transaction> transactions = new ArrayList<>();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                data -> {
                    Transaction transaction = data.getValue(Transaction.class);
                    if(transaction != null &&
                            ((transaction.getRecipient_account() != null &&
                                    transaction.getRecipient_account().getCustomer().get_id().equals(customer.get_id())
                            ) ||
                                    transaction.getSending_account().getCustomer().get_id().equals(customer.get_id())
                            ) &&
                            transaction.getSending_account().getCustomer().getMicrofinance().get_id().equals(microfinance.get_id()) &&
                            ((String.valueOf(transaction.getAmount()).toLowerCase().contains(value.toLowerCase())) ||
                                    transaction.getTransaction_type().toString().toLowerCase().contains(value.toLowerCase()) ||
                                    transaction.getTransaction_date().toLowerCase().contains(value.toLowerCase()) ||
                                    transaction.getTransaction_status().toString().toLowerCase().contains(value.toLowerCase()) ||
                                    transaction.get_id().toLowerCase().contains(value.toLowerCase())
                            ) &&
                            (account == null ||
                                    (transaction.getSending_account().get_id().equals(account.get_id()) ||
                                            (transaction.getRecipient_account() != null &&
                                                    transaction.getRecipient_account().get_id().equals(account.get_id())
                                            )
                                    )
                            )
                    ){
                        transactions.add(transaction);
                        if(transaction.get_id() != null) {
                            showNotification(
                                    activity ,
                                    microfinance.getNom() +
                                            " : " +
                                            activity.getString(R.string.text_menu_transaction) ,
                                    transaction.getTransaction_type().toString().toUpperCase() +
                                            " (" +
                                            new StringBuilder(transaction.get_id()).substring(0 , 10).concat("...") +
                                            ") : " +
                                            transaction.getAmount() +
                                            " " + microfinance.getCurrency().getCurrency_symbol() +
                                            ". " +
                                            transaction.getTransaction_status() +
                                            ". \n" +
                                            activity.getString(R.string.text_view_more) +
                                            "..." ,
                                    transaction.get_id() ,
                                    transaction.getTransaction_status().toString()
                            );
                        }
                    }
                }
            );

            reverseList(transactions, limit);

            TransactionRecyclerAdapter mAdapter = new TransactionRecyclerAdapter(transactions, context, activity);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(mAdapter);

            if(swipe_refresh_recycler_transaction != null){
                swipe_refresh_recycler_transaction.setRefreshing(false);
            }
        }

    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkAccountCustomer( final RecyclerView recyclerView, final Context context, final Customer customer, final Microfinance microfinance, final Activity activity){

        RxFirebaseDatabase.observeValueEvent(sAccountHelper.getAccounts())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    dataSnapshot -> displayAccountCustomer(dataSnapshot , recyclerView, context, customer, microfinance, activity),
                    throwable -> Toast.makeText(context, activity.getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
            );

    }


    private static void displayAccountCustomer( DataSnapshot dataSnapshot, final RecyclerView recyclerView, final Context context, final Customer customer, final Microfinance microfinance, final Activity activity){
        final ArrayList<Account> accounts = new ArrayList<>();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                    data -> {
                        Account account = data.getValue(Account.class);
                        if(account != null &&
                                account.getCustomer().get_id().equals(customer.get_id()) &&
                                account.getCustomer().getMicrofinance().get_id().equals(microfinance.get_id())){
                            accounts.add(account);
                        }
                    }
            );
            reverseList(accounts);
            AccountRecyclerAdapter mAdapter = new AccountRecyclerAdapter(accounts, context, activity);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(mAdapter);
        }

    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkCitiesForCountries( final SmartMaterialSpinner smartMaterialSpinner, final Country country, final Member member) {

        RxFirebaseDatabase.observeValueEvent(sCityHelper.getCities())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dataSnapshot -> displayCitiesForCountries(dataSnapshot , smartMaterialSpinner, country, member),
                        throwable -> Toast.makeText(smartMaterialSpinner.getContext(), smartMaterialSpinner.getContext().getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
                );

    }


    private static void displayCitiesForCountries( DataSnapshot dataSnapshot, final SmartMaterialSpinner smartMaterialSpinner, final Country country, final Member member) {
        final ArrayList<City> cities = new ArrayList<>();
        final City[] citySearch = {new City()};

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                data -> {
                    City city = data.getValue(City.class);
                    if(city != null){
                        if ( city.getCountry().get_id().equals(country.get_id()) ) {
                            cities.add(city);
                            if(member.getCity() != null) {
                                if ( city.getName().equals(member.getCity().getName()) ) {
                                    citySearch[0] = city;
                                }
                            }
                        }
                    }
                }
            );

            smartMaterialSpinner.setItem(cities);
            smartMaterialSpinner.setSelection(cities.indexOf(citySearch[0]));
        }

    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkCountries( final SmartMaterialSpinner smartMaterialSpinnerCountry, final SmartMaterialSpinner smartMaterialSpinnerCity, final Member member){

        RxFirebaseDatabase.observeValueEvent(sCountryHelper.getCountries())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dataSnapshot -> displayCountries(dataSnapshot , smartMaterialSpinnerCountry, smartMaterialSpinnerCity, member),
                        throwable -> Toast.makeText(smartMaterialSpinnerCountry.getContext(), smartMaterialSpinnerCountry.getContext().getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
                );

    }


    private static void displayCountries( DataSnapshot dataSnapshot, final SmartMaterialSpinner smartMaterialSpinnerCountry, final SmartMaterialSpinner smartMaterialSpinnerCity, final Member member){
        final ArrayList<Country> countries = new ArrayList<>();
        final Country[] countrySearch = {new Country()};

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                data -> {
                    Country country = data.getValue(Country.class);
                    if(country != null){
                        countries.add(country);
                    }
                    if(member.getCountry() != null){
                        if (country.getName().equals(member.getCountry().getName())) {
                            countrySearch[0] = country;
                        }
                    }
                }
            );

            smartMaterialSpinnerCountry.setItem(countries);
            if( countries.size() > 0 ) {
                smartMaterialSpinnerCountry.setSelection(countries.indexOf(countrySearch[0]));
                if(smartMaterialSpinnerCity != null){
                    checkCitiesForCountries(smartMaterialSpinnerCity, countrySearch[0], member);
                }

            }
        }

    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkListNumberCode( final SmartMaterialSpinner smartMaterialSpinner, final Member member){

        RxFirebaseDatabase.observeValueEvent(sCountryHelper.getCountries())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    dataSnapshot -> displayListNumberCode(dataSnapshot , smartMaterialSpinner, member),
                    throwable -> Toast.makeText(smartMaterialSpinner.getContext(), smartMaterialSpinner.getContext().getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
            );

    }


    private static void displayListNumberCode( DataSnapshot dataSnapshot, final SmartMaterialSpinner smartMaterialSpinner, final Member member){

        final ArrayList<String> listNumberCode = new ArrayList<>();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                data -> {
                    Country country = data.getValue(Country.class);
                    if(country != null){
                        listNumberCode.add(country.getCode_phone());
                    }
                }
            );

            smartMaterialSpinner.setItem(listNumberCode);
            if(member.getPhone_number() != null) {
                smartMaterialSpinner.setSelection(listNumberCode.indexOf(member.getPhone_number().split(" ")[0]));
            }
        }

    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkCitiesForAgencyMicrofinance( final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance) {

        RxFirebaseDatabase.observeValueEvent(sAgencyHelper.getAgencies())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    dataSnapshot -> displayCitiesForAgencyMicrofinance(dataSnapshot , smartMaterialSpinner, microfinance),
                    throwable -> Toast.makeText(smartMaterialSpinner.getContext(), smartMaterialSpinner.getContext().getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
            );

    }


    private static void displayCitiesForAgencyMicrofinance( DataSnapshot dataSnapshot, final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance) {
        final ArrayList<City> cities = new ArrayList<>();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                    data -> {
                        Agency agency = data.getValue(Agency.class);
                        if(agency != null){
                            if ( !cities.contains(agency.getCity()) && agency.getMicrofinance().get_id().equals(microfinance.get_id())) {
                                cities.add(agency.getCity());
                            }
                        }
                    }
            );

            smartMaterialSpinner.setItem(cities);
        }

    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkAgencyCity ( final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance, final City city ) {

        RxFirebaseDatabase.observeValueEvent(sAgencyHelper.getAgencies())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dataSnapshot -> displayAgencyCity(dataSnapshot , smartMaterialSpinner, microfinance, city),
                        throwable -> Toast.makeText(smartMaterialSpinner.getContext(), smartMaterialSpinner.getContext().getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
                );

    }


    private static void displayAgencyCity ( DataSnapshot dataSnapshot, final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance, final City city ) {
        final ArrayList<Agency> agencies = new ArrayList<>();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                    data -> {
                        Agency agency = data.getValue(Agency.class);
                        if(agency != null){
                            if ( agency.getCity().get_id().equals(city.get_id()) &&
                                    agency.getMicrofinance().get_id().equals(microfinance.get_id())) {
                                agencies.add(agency);
                            }
                        }
                    }
            );

            smartMaterialSpinner.setItem(agencies);
        }

    }


    @SuppressLint({"CheckResult" , "ShowToast"})
    public static void checkAccountMicrofinance ( final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance, final Account account ) {

        RxFirebaseDatabase.observeValueEvent(sAccountHelper.getAccounts())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dataSnapshot -> displayAccountMicrofinance(dataSnapshot , smartMaterialSpinner, microfinance, account),
                        throwable -> Toast.makeText(smartMaterialSpinner.getContext(), smartMaterialSpinner.getContext().getString(R.string.text_operation_failed), Toast.LENGTH_LONG).show()
                );

    }


    private static void displayAccountMicrofinance ( DataSnapshot dataSnapshot, final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance, final Account account ) {
        final ArrayList<Account> accounts = new ArrayList<>();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            dataSnapshot.getChildren().forEach(
                    data -> {
                        Account account1 = data.getValue(Account.class);
                        if(account1 != null){
                            if ( !account1.get_id().equals(account.get_id()) &&
                                    account.getCustomer().getMicrofinance().get_id().equals(microfinance.get_id())) {
                                accounts.add(account1);
                            }
                        }
                    }
            );

            smartMaterialSpinner.setItem(accounts);
        }
    }

//    private static RemoteViews getCustomDesign ( Activity activity , String title , String message ){
//        RemoteViews remoteViews = new RemoteViews(activity.getApplicationContext().getPackageName(), R.layout.notification);
//        remoteViews.setTextViewText(R.id.text_title, title);
//        remoteViews.setTextViewText(R.id.text_message, message);
//        remoteViews.setImageViewResource(R.id.icon_notification, R.mipmap.logo);
//        return remoteViews;
//    }

    private static void showNotification ( Activity activity , String title , String message , Object key , Object value ){

        if(key == null || value == null)
            return;
        Dictionary<Object, Object> dictionary = contentPreferenceNotification(activity);

        if(dictionary.get(key) == null){
            addFromPreferenceNotification(key, value, activity);
        }
        else {
            if(dictionary.get(key).equals(value)){
                return;
            }
            else {
                addFromPreferenceNotification(key, value, activity);
            }
        }

        String channel_id = "com.example.blitz_t.test";

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(),
                numberNotification,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity.getApplicationContext(), channel_id)
                .setSmallIcon(R.mipmap.logo_b)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent);

        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, "web_app", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri, null);
            if ( notificationManager != null ) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        if ( notificationManager != null ) {
            notificationManager.notify(numberNotification, builder.build());
        }

        numberNotification++;

    }

}
