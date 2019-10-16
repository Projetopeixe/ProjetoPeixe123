package br.edu.ufopa.cadfishmaster.fragments;

import android.Manifest;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.config.Permissoes;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHomeMenu extends Fragment {


    public FragmentHomeMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home_menu, container, false);
    }


}
