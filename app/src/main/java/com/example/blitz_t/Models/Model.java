package com.example.blitz_t.Models;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.AccountHelper;
import com.example.blitz_t.Api.AgencyHelper;
import com.example.blitz_t.Api.CityHelper;
import com.example.blitz_t.Api.CountryHelper;
import com.example.blitz_t.Controllers.AccountPagerAdapter;
import com.example.blitz_t.Controllers.AccountRecyclerAdapter;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import static com.example.blitz_t.Api.AccountHelper.getAccounts;
import static com.example.blitz_t.Api.CustomerHelper.getCustomers;
import static com.example.blitz_t.Api.MicrofinanceHelper.getMicrofinances;
import static com.example.blitz_t.Api.TransactionHelper.getTransactions;

public class Model {

    public static String ConvertToString(String dateInString) throws ParseException {
        String format = "YYYY-MM-dd";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat( format );
        Date date = formatter.parse(dateInString);
        return formatter.format(date);
    }

    public static String getBirthDateSelect(int dayOfMonth, int mounth, int year) {
        String DAY = String.valueOf(dayOfMonth).length() == 1 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
        String MOUNT = String.valueOf(mounth).length() == 1 ? "0" + mounth : String.valueOf(mounth);
        return year + "-" + MOUNT + "-" + DAY;
    }


    public static void saveFormPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        String contentPreference = new Gson().toJson(object);
        SharedPreferences.Editor editor = contextWrapper.getSharedPreferences(preferenceFileKey , Context.MODE_PRIVATE).edit();
        editor.putString(filePreference , contentPreference);
        editor.apply();
    }

    public static void clearFormPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
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

    public static String currentDate(){
        String format = "YYYY-MM-dd H:mm:ss";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat( format );
        Date date = new Date();
        return formatter.format(date);
    }

    public static void reverseList(ArrayList objects){
        ArrayList results = new ArrayList<>();
        for (int i = objects.size() - 1; i >= 0; i--){
            results.add(objects.get(i));
        }
        objects.clear();
        objects.addAll(results);
    }

//    public static boolean isOnline(Context context) {
//        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        assert cm != null;
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }


    public static void checkAccountCustomer( final ViewPager viewPager, final Context context, final Activity activity, final Customer customer, final Microfinance microfinance, final Account account){
        final ArrayList<Account> accounts = new ArrayList<>();
        getAccounts().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                if ( account ==  null ){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Account account = data.getValue(Account.class);
                        if(account != null &&
                                account.getCustomer().get_id().equals(customer.get_id()) &&
                                account.getCustomer().getMicrofinance().get_id().equals(microfinance.get_id())){
                            accounts.add(account);
                        }
                    }
                }
                else{
                    accounts.add(account);
                }

                AccountPagerAdapter mAdapter = new AccountPagerAdapter(accounts, context, activity);
                viewPager.setAdapter(mAdapter);
                viewPager.setPadding(50, 0, 50, 0);

            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Snackbar.make(viewPager, R.string.text_operation_failed, Snackbar.LENGTH_LONG);
            }
        });
    }

    public static void checkMicrofinances( final RecyclerView recyclerView, final Context context, final String name, final Member member, final Activity activity){
        if(member != null){
            if(member.get_id() != null)
                checkMicrofinancesMember(recyclerView, context, name, member, activity);
            return;
        }
        final ArrayList<Microfinance> microfinances = new ArrayList<>();
        getMicrofinances().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Microfinance microfinance = data.getValue(Microfinance.class);
                    if(microfinance != null && microfinance.getNom().toLowerCase().contains(name.toLowerCase())){
                        microfinances.add(microfinance);
                    }
                }
                MicrofinanceRecyclerAdapter adapter = new MicrofinanceRecyclerAdapter(microfinances , context, activity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Snackbar.make(recyclerView, R.string.text_operation_failed, Snackbar.LENGTH_LONG);
            }
        });
    }

    public static void checkMicrofinancesMember( final RecyclerView recyclerView, final Context context, final String name, final Member member, final Activity activity){
        final ArrayList<Microfinance> microfinances = new ArrayList<>();
        getCustomers().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.w("Info", data.toString());
                    Customer customer = data.getValue(Customer.class);
                    if(customer != null && customer.getMicrofinance().getNom().toLowerCase().contains(name.toLowerCase()) && customer.getMember().get_id().equals(member.get_id())){
                        microfinances.add(customer.getMicrofinance());
                    }
                }
                MicrofinanceRecyclerAdapter adapter = new MicrofinanceRecyclerAdapter(microfinances , context, activity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Snackbar.make(recyclerView, R.string.text_operation_failed, Snackbar.LENGTH_LONG);
            }
        });
    }

    public static void checkTransactionCustomer( final RecyclerView recyclerView, final Context context, final Customer customer, final Microfinance microfinance, final Activity activity, final String value, final int limit, final Account account ){
        final ArrayList<Transaction> transactions = new ArrayList<>();
        getTransactions().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Transaction transaction = data.getValue(Transaction.class);
                    if(transaction != null &&
                        (transaction.getRecipient_account().getCustomer().get_id().equals(customer.get_id()) ||
                            transaction.getSending_account().getCustomer().get_id().equals(customer.get_id())
                        ) &&
                        transaction.getSending_account().getCustomer().getMicrofinance().get_id().equals(microfinance.get_id()) &&
                        ((String.valueOf(transaction.getAmount()).toLowerCase().contains(value.toLowerCase())) ||
                            transaction.getTransaction_type().toString().toLowerCase().contains(value.toLowerCase()) ||
                            transaction.getTransaction_date().toLowerCase().contains(value.toLowerCase()) ||
                            transaction.getTransaction_status().toString().toLowerCase().contains(value.toLowerCase())
                        ) &&
                        (account == null ||
                            (transaction.getSending_account().get_id().equals(account.get_id()) ||
                            transaction.getRecipient_account().get_id().equals(account.get_id()))
                        )
                    ){
                        transactions.add(transaction);
                    }
                }
                reverseList(transactions);

                if(limit > 0){
                    final ArrayList<Transaction> results = new ArrayList<>();
                    for (int i = 0; i < limit; i++){
                        results.add(transactions.get(i));
                    }
                    transactions.clear();
                    transactions.addAll(results);
                }

                TransactionRecyclerAdapter mAdapter = new TransactionRecyclerAdapter(transactions, context, activity);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Snackbar.make(recyclerView, R.string.text_operation_failed, Snackbar.LENGTH_LONG);
            }
        });
    }

    public static void checkAccountCustomer( final RecyclerView recyclerView, final Context context, final Customer customer, final Microfinance microfinance, final Activity activity){
        final ArrayList<Account> accounts = new ArrayList<>();
        getAccounts().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Account account = data.getValue(Account.class);
                    if(account != null &&
                        account.getCustomer().get_id().equals(customer.get_id()) &&
                        account.getCustomer().getMicrofinance().get_id().equals(microfinance.get_id())){
                        accounts.add(account);
                    }
                }
                reverseList(accounts);

                AccountRecyclerAdapter mAdapter = new AccountRecyclerAdapter(accounts, context, activity);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Snackbar.make(recyclerView, R.string.text_operation_failed, Snackbar.LENGTH_LONG);
            }
        });
    }

    public static void checkCitiesForCountries( final SmartMaterialSpinner smartMaterialSpinner, final Country country, final Member member) {
        final ArrayList<City> cities = new ArrayList<>();
        final City[] citySearch = {new City()};

        CityHelper.getCities().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
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
                smartMaterialSpinner.setItem(cities);
                smartMaterialSpinner.setSelection(cities.indexOf(citySearch[0]));

            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public static void checkCountries( final SmartMaterialSpinner smartMaterialSpinnerCountry, final SmartMaterialSpinner smartMaterialSpinnerCity, final Member member){
        final ArrayList<Country> countries = new ArrayList<>();
        final Country[] countrySearch = {new Country()};

        CountryHelper.getCountries().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
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
                smartMaterialSpinnerCountry.setItem(countries);
                if( countries.size() > 0 ) {
                    smartMaterialSpinnerCountry.setSelection(countries.indexOf(countrySearch[0]));
                    if(smartMaterialSpinnerCity != null){
                        checkCitiesForCountries(smartMaterialSpinnerCity, countrySearch[0], member);
                    }

                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public static void checkListNumberCode( final SmartMaterialSpinner smartMaterialSpinner, final Member member){

        final ArrayList<String> listNumberCode = new ArrayList<>();

        CountryHelper.getCountries().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Country country = data.getValue(Country.class);
                    if(country != null){
                        listNumberCode.add(country.getCode_phone());
                    }
                }
                smartMaterialSpinner.setItem(listNumberCode);
                if(member.getPhone_number() != null) {
                    smartMaterialSpinner.setSelection(listNumberCode.indexOf(member.getPhone_number().split(" ")[0]));
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public static void checkCitiesForAgencyMicrofinance( final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance) {
        final ArrayList<City> cities = new ArrayList<>();
        AgencyHelper.getAgencies().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Agency agency = data.getValue(Agency.class);
                    if(agency != null){
                        if ( !cities.contains(agency.getCity()) && agency.getMicrofinance().get_id().equals(microfinance.get_id())) {
                            cities.add(agency.getCity());
                        }
                    }
                }
                smartMaterialSpinner.setItem(cities);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public static void checkAgencyCity ( final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance, final City city ) {
        final ArrayList<Agency> agencies = new ArrayList<>();
        AgencyHelper.getAgencies().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Agency agency = data.getValue(Agency.class);
                    if(agency != null){
                        if ( agency.getCity().get_id().equals(city.get_id()) &&
                                agency.getMicrofinance().get_id().equals(microfinance.get_id())) {
                            agencies.add(agency);
                        }
                    }
                }
                smartMaterialSpinner.setItem(agencies);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public static void checkAccountMicrofinance ( final SmartMaterialSpinner smartMaterialSpinner, final Microfinance microfinance, final Account account ) {
        final ArrayList<Account> accounts = new ArrayList<>();
        AccountHelper.getAccounts().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Account account1 = data.getValue(Account.class);
                    if(account1 != null){
                        if ( !account1.get_id().equals(account.get_id()) &&
                            account.getCustomer().getMicrofinance().get_id().equals(microfinance.get_id())) {
                            accounts.add(account1);
                        }
                    }
                }
                smartMaterialSpinner.setItem(accounts);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

}
