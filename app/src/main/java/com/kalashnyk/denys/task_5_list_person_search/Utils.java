package com.kalashnyk.denys.task_5_list_person_search;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by lekar on 12.03.17.
 */

public class Utils {
    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, String url ) {
        if(view.getId()==R.id.avatar)
        {
            Picasso.with(view.getContext())
                    .load(url)
                    .into(view);
        }
        else
            Picasso.with(view.getContext())
                    .load(url)
                    .into(view);

    }
}