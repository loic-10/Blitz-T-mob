package com.example.blitz_t.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blitz_t.R;
import com.example.blitz_t.Views.ScreenItem;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class IntroPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<ScreenItem> mScreenItems;

    public IntroPagerAdapter ( Context context , List<ScreenItem> screenItems ) {
        mContext = context;
        mScreenItems = screenItems;
    }

    @NonNull
    @Override
    public Object instantiateItem ( @NonNull ViewGroup container , int position ) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        ImageView imgBlide = layoutScreen.findViewById(R.id.img_intro);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        title.setText(mScreenItems.get(position).getTitle());
        description.setText(mScreenItems.get(position).getDescription());
        imgBlide.setBackgroundResource(mScreenItems.get(position).getScreenImg());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount () {
        return mScreenItems.size();
    }

    @Override
    public boolean isViewFromObject ( @NonNull View view , @NonNull Object object ) {
        return view == object;
    }

    @Override
    public void destroyItem ( @NonNull ViewGroup container , int position , @NonNull Object object ) {



    }
}
