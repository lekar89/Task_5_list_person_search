package com.kalashnyk.denys.task_5_list_person_search.ui;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.kalashnyk.denys.task_5_list_person_search.CRUDSharedPref;
import com.kalashnyk.denys.task_5_list_person_search.DetailFragment;
import com.kalashnyk.denys.task_5_list_person_search.R;
import com.kalashnyk.denys.task_5_list_person_search.databinding.ActivityMainBinding;
import com.kalashnyk.denys.task_5_list_person_search.model.Person;
import com.kalashnyk.denys.task_5_list_person_search.ui.adapters.MyAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final String[] NAME =  new String[] {"Denys", "Stanislav", "Vadim", "Eugeniy", "Alexander", "Vladislav", "Sergey", "Aleksey"};
    private static final String[] SURNAME = new String[] {"Kalashnyk", "Puzin", "Hotiun", "Kovalov", "Merezhko", "Pomoynyts'kyy", "Rudenko", "Greenyuk"};
    private static final String[] PHONE_NUMBER = new String [] {"0993414821", "0667077979", "0974995005", "0933988237", "0932007592", "0631365815", "0938375507", "0679999977"};
    private static final String[] MAIL = new String [] {"kalashnyk.denys@gmail.com", "stanislavshido@gmail.com", "v.a.d.i.k@mail.ru", "eugene.kovalev@me.com",
            "merezhkosasha@gmail.com", "ekar89@mail.ru", "thesergeyrudenko@gmail.com", "alexey.grinyuk@gmail.com"};
    private static final String[] SKYPE = new String [] {"denis_ka27", "shido_s", "hotun.vadim", "kovalev_eugene", "sasha_merezhko", "vladislavpom", "sergey_rudenko_84", "greenya1"};

    public static final String [] FOTO = {"https://avatars2.githubusercontent.com/u/18060033?v=3&s=460",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT-ZoKiYmasqfHAxdpo4SY7jBtqubJWsBpwEdYLZjXOzEd9hu7R9g",
            "https://avatar.skype.com/v1/avatars/panzhiev_jr?auth_key=-1153689606&returnDefaultImage=false&cacheHeaders=true",
            "https://avatar.skype.com/v1/avatars/kovalev_eugene?auth_key=-18264124&returnDefaultImage=false&cacheHeaders=true",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRH6V3UggwkMCNdhItafOmOVwxeYYX_QoWFXfbG4q3aoFLxqYx2",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcT9amZGk2iReYOzY-vLSftw0tV3thO-91wqia55sPPzXIE-NxPI",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRN3C7PqPkAlU-PyDOw1rMJviHUKQCBUscskOqZlPgBMCv_FdNlRw",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK_fofEumwI2bI2FPgnhlyJkJqc9eXLkQAw_pJq6nUYygxxCUqXA"};


    private static final Comparator<Person> ALPHABETICAL_COMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person a, Person b) {
            return a.getName().compareTo(b.getName());
        }
    };

    CRUDSharedPref mCRUDSharedPref;
    private MyAdapter mAdapter;
    private List<Person> mModels;
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mAdapter = new MyAdapter(this, ALPHABETICAL_COMPARATOR, new MyAdapter.Listener() {
            @Override
            public void onPersonModelClicked(Person person) {
                DetailFragment fragment = new DetailFragment( );
//                Bundle bundle =new Bundle();
//                bundle.putString("surname",person.getSurname());
//                fragment.setArguments(bundle);

                fragment.setPerson(person);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack("A");
                ft.commit();
                mBinding.recyclerView.setVisibility(View.GONE);
            }
        });

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);

        mCRUDSharedPref = new CRUDSharedPref();

        mModels = new ArrayList<>();
        for (int i = 0, count = NAME.length; i < count; i++) {

            mModels.add(new Person(i, NAME[i], SURNAME[i], PHONE_NUMBER[i], MAIL[i],
                    SKYPE[i],FOTO[i]));
        }

        mCRUDSharedPref.savePersons(this,mModels);

        mAdapter.edit()
                .replaceAll(mModels)
                .commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<Person> filteredModelList = filter(mModels, query);
        mAdapter.edit()
                .replaceAll(filteredModelList)
                .commit();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private static List<Person> filter(List<Person> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Person> filteredModelList = new ArrayList<>();
        for (Person model : models) {
            final String name = model.getName().toLowerCase();
            if (name.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }



    @Override
    public void onBackPressed() {

        if( mBinding.recyclerView.getVisibility()!=View.VISIBLE) super.onBackPressed();
        else mBinding.recyclerView.setVisibility(View.VISIBLE);
        getSupportFragmentManager().getFragments().clear();

    }
}
