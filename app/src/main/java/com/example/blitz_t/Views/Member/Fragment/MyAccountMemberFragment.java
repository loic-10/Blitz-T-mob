package com.example.blitz_t.Views.Member.Fragment;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blitz_t.Views.Member.Activity.EditProfileMemberActivity;
import com.example.blitz_t.Views.Member.Activity.HomeMemberActivity;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.Member.Activity.LoginMemberActivity;
import com.example.blitz_t.Views.Member.Activity.RegisterMemberPart1Activity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyAccountMemberFragment extends Fragment {

    private View view, button_edit_profile, button_notification, button_settings, button_disconnect, account_true, account_false;
    private CollapsingToolbarLayout collapsing;
    private View button_create_account, button_login;
    private Member mMember;
    private ContextWrapper mContextWrapper;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_account_member , container, false);

        mContextWrapper = (ContextWrapper) view.getContext();

        collapsing = view.findViewById(R.id.collapsing);
        button_edit_profile = view.findViewById(R.id.button_edit_profile);
        button_notification = view.findViewById(R.id.button_notification);
        button_settings = view.findViewById(R.id.button_settings);
        button_disconnect = view.findViewById(R.id.button_disconnect);
        button_create_account = view.findViewById(R.id.button_create_account);
        button_login = view.findViewById(R.id.button_login);

        account_true = view.findViewById(R.id.account_true);
        account_false = view.findViewById(R.id.account_false);

        button_edit_profile.setOnClickListener(listener);
        button_notification.setOnClickListener(listener);
        button_settings.setOnClickListener(listener);
        button_disconnect.setOnClickListener(listener);
        button_create_account.setOnClickListener(listener);
        button_login.setOnClickListener(listener);

        collapsing.setTitle(getString(R.string.text_my_account));

        mMember = (Member) Model.contentPreference(
                new Member(),
                getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                mContextWrapper);

        account_false.setVisibility(mMember == null ? View.VISIBLE : View.INVISIBLE);
        account_true.setVisibility(mMember != null ? View.VISIBLE : View.INVISIBLE);

        return view;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {

            Intent intent = null;

            switch (v.getId()){
                case R.id.button_edit_profile :
                    intent = new Intent(v.getContext(), EditProfileMemberActivity.class);
                    break;
                case R.id.button_notification :
                    intent = new Intent(v.getContext(), EditProfileMemberActivity.class);
                    break;
                case R.id.button_settings :
                    intent = new Intent(v.getContext(), EditProfileMemberActivity.class);
                    break;
                case R.id.button_disconnect :
                    Model.saveFromPreference(null, getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                            getString(R.string.PREFERENCE_FILE_KEY), mContextWrapper );
                    intent = new Intent(v.getContext(), HomeMemberActivity.class);
                    break;
                case R.id.button_create_account :
                    intent = new Intent(v.getContext(), RegisterMemberPart1Activity.class);
                    break;
                case R.id.button_login :
                    intent = new Intent(v.getContext(), LoginMemberActivity.class);
                    break;
            }
            if(intent != null) {
                startActivity(intent);
            }

        }
    };
}
