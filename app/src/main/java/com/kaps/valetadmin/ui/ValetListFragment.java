package com.kaps.valetadmin.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kaps.valetadmin.R;
import com.kaps.valetadmin.adapter.ValetListAdapter;
import com.kaps.valetadmin.models.Valet;
import com.kaps.valetadmin.viewmodel.ValetListViewmodel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValetListFragment extends Fragment {


    private RecyclerView.Adapter mAdapter;

    public ValetListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_valet_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ValetListViewmodel viewmodel = ViewModelProviders.of(this).get(ValetListViewmodel.class);
        FloatingActionButton fab = view.findViewById(R.id.fab_nav);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_valetListFragment_to_registerFragment);
            }
        });
        final RecyclerView recyclerView = view.findViewById(R.id.rv_valet_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewmodel.getValet().observe(getViewLifecycleOwner(), new Observer<List<Valet>>() {
            @Override
            public void onChanged(List<Valet> valets) {


                mAdapter = new ValetListAdapter(getContext(), valets );
                recyclerView.setAdapter(mAdapter);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if( mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }
}
