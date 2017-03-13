package com.kalashnyk.denys.task_5_list_person_search.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.kalashnyk.denys.task_5_list_person_search.model.Person;
import com.kalashnyk.denys.task_5_list_person_search.databinding.ItemPersonBinding;

import java.util.Comparator;

/**
 * Created by User on 18.12.2016.
 */
public class MyAdapter extends SortedListAdapter<Person> {

    public interface Listener {
        void onPersonModelClicked(Person person);
    }

    private final Listener mListener;

    public MyAdapter(Context context, Comparator<Person> comparator, Listener listener) {
        super(context, Person.class, comparator);
        mListener = listener;
    }

    @Override
    protected ViewHolder<? extends Person> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        ItemPersonBinding binding = ItemPersonBinding.inflate(inflater, parent, false);
        return new PersonViewHolder(binding, mListener);
    }

    @Override
    protected boolean areItemsTheSame(Person item1, Person item2) {
        return item1.getId() == item2.getId();
    }

    @Override
    protected boolean areItemContentsTheSame(Person oldItem, Person newItem) {
        return oldItem.equals(newItem);
    }
}

