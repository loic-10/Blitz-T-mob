package com.example.blitz_t.Models.MemberMicrofinance;

import java.util.ArrayList;

public class ModelMemberMicrofinance {

    public static ArrayList<MemberMicrofinance> allMemberMicrofinances(){
        ArrayList<MemberMicrofinance> memberMicrofinances = new ArrayList<>();

        memberMicrofinances.add(new MemberMicrofinance(1, 1, 1, 3));
        memberMicrofinances.add(new MemberMicrofinance(2, 1, 2, 3));
        memberMicrofinances.add(new MemberMicrofinance(3, 2, 1, 3));
        memberMicrofinances.add(new MemberMicrofinance(4, 1, 3, 3));
        return memberMicrofinances;
    }

    public static int memberNumberMicrofinance(int id_microfinance){
        int member = 0;
        for(MemberMicrofinance memberMicrofinance : allMemberMicrofinances()){
            if(memberMicrofinance.getId_microfinance() == id_microfinance){
                member++;
            }
        }
        return member;
    }

    public static float ratingMicrofinance(int id_microfinance){
        float rating = 0;
        for(MemberMicrofinance memberMicrofinance : allMemberMicrofinances()){
            if(memberMicrofinance.getId_microfinance() == id_microfinance){
                rating+=memberMicrofinance.getRating();
            }
        }
        return (rating/memberNumberMicrofinance(id_microfinance));
    }

}
