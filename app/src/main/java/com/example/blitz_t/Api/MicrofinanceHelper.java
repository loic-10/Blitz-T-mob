package com.example.blitz_t.Api;

import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;

public class MicrofinanceHelper extends DB<Microfinance> {

    private Microfinance mMicrofinance;

    public MicrofinanceHelper ( Microfinance data ) {
        super(data);
        mMicrofinance = data;
    }

    public void setMicrofinance(Microfinance microfinance){
        setObject(microfinance, microfinance.get_id());
    }

    // --- GET ---

    public DatabaseReference getMicrofinances(){
        return getReference();
    }

    public void getMicrofinance( String _id, ArrayList<Microfinance> microfinances){
        getObjects(microfinances, _id);
    }

    public void completedMicrofinances(ArrayList<Microfinance> microfinances){
        getObjects(microfinances);
    }

    // --- DELETE ---

    public void deleteMicrofinance( String _id ){
        removeObject(_id);
    }

}
