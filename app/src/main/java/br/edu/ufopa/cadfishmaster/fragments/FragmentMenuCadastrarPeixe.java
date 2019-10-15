package br.edu.ufopa.cadfishmaster.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.activity.CadastroDePeixesActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenuCadastrarPeixe extends Fragment {

    private Button button;

    public FragmentMenuCadastrarPeixe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_cadastrar_peixe, container, false);

        button = view.findViewById(R.id.buttonNewCadastroPeixe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastroDePeixesActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
