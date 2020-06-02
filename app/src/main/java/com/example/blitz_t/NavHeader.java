package com.example.blitz_t;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Views.DesignApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NavHeader extends Fragment {

    private Member mMember;
    private ImageView image_cni_customer;
    private ImageView image_profile_customer;
    private TextView text_customer_name;
    private TextView text_customer_phone_number;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.nav_header , container , false);

        initView(view);

        mMember = (Member) Model.contentPreference(
                new Customer(),
                getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                getActivity());

        fillForm ( mMember != null ? mMember : new Member(), view );

        return view;
    }

    private void initView ( View view ) {
        image_cni_customer = view.findViewById(R.id.image_cni_customer);
        image_profile_customer = view.findViewById(R.id.image_profile_customer);
        text_customer_name = view.findViewById(R.id.text_customer_name);
        text_customer_phone_number = view.findViewById(R.id.text_customer_phone_number);
    }

    private void fillForm ( Member member,  View view ){
        text_customer_name.setText(member.getFull_name());
        text_customer_phone_number.setText(member.getPhone_number());
        DesignApp.updateImage(view.getContext(), image_cni_customer , member.getCni_copy() , null);
        DesignApp.updateImage(view.getContext() , image_profile_customer , member.getProfile_picture() , null);

    }
}
