package com.kalashnyk.denys.task_5_list_person_search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.kalashnyk.denys.task_5_list_person_search.databinding.FragmentDetailBinding;
import com.kalashnyk.denys.task_5_list_person_search.model.Person;
import com.squareup.picasso.Picasso;

/**
 * Created by lekar on 12.03.17.
 */

public class DetailFragment extends Fragment{


    Person mPerson;
    FragmentDetailBinding mBinding;

    //CRUDSharedPref mCRUDSharedPref = new CRUDSharedPref();

    public void setPerson(Person mPerson) {
        this.mPerson = mPerson;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false);
        //String key=  getArguments().getString("surname");

        mBinding.setPerson(mPerson);
        //mBinding.setPerson( mCRUDSharedPref.getPerson(getContext(),key));

//        ImageView imageView =(ImageView)view.findViewById(R.id.image);
//        Picasso.with(getContext())
//                .load( "http://i.imgur.com/DvpvklR.png")
//                .into(imageView);
        return mBinding.getRoot();
    }

}
