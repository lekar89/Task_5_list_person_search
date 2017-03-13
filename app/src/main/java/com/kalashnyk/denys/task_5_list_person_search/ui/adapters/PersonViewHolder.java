package com.kalashnyk.denys.task_5_list_person_search.ui.adapters;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.kalashnyk.denys.task_5_list_person_search.model.Person;
import com.kalashnyk.denys.task_5_list_person_search.databinding.ItemPersonBinding;

/**
 * Created by User on 17.12.2016.
 */
public class PersonViewHolder extends SortedListAdapter.ViewHolder<Person> {

    private final ItemPersonBinding mBinding;

    public PersonViewHolder(ItemPersonBinding binding, MyAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);

        mBinding = binding;
    }

    @Override
    protected void performBind(Person item) {
        mBinding.setPerson(item);
    }
}

