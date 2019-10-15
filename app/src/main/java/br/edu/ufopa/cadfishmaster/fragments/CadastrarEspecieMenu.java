package br.edu.ufopa.cadfishmaster.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.activity.cadastro_especies.CadastroDeEspeciesPasso1;

/**
 * A simple {@link Fragment} subclass.
 */


public class CadastrarEspecieMenu extends Fragment {


    private Button button;

    public CadastrarEspecieMenu() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cadastrar_especie_menu, container, false);
        button = view.findViewById(R.id.buttonNewEspecie);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastroDeEspeciesPasso1.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
