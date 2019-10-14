package br.edu.ufopa.cadfishmaster.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ufopa.cadfishmaster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenuCadastrarPeixe extends Fragment {

    public FragmentMenuCadastrarPeixe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_menu_cadastrar_peixe, container, false);
    }
}
