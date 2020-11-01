package com.mariotm.ddamq.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mariotm.ddamq.R;
import com.mariotm.ddamq.adapters.AdaptadorCategorias;
import com.mariotm.ddamq.clases.Utilidades;
import com.mariotm.ddamq.interfaces.ComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Activity activity;
    View view;
    ComunicaFragments comunicaFragments;
    RecyclerView recyclerCategorias;
    FloatingActionButton FabAgregarCategoria;

    public CategoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriasFragment newInstance(String param1, String param2) {
        CategoriasFragment fragment = new CategoriasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categorias, container, false);

        recyclerCategorias = (RecyclerView) view.findViewById(R.id.recyclerCategorias);
        FabAgregarCategoria = (FloatingActionButton) view.findViewById(R.id.FabAgregarCategoria);

        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this.activity));
        recyclerCategorias.setHasFixedSize(true);

        cargarAdaptadorCategorias();

        FabAgregarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicaFragments.categorias();
            }
        });

        return view;
    }

    private void cargarAdaptadorCategorias() {

        AdaptadorCategorias adaptadorCategorias = new AdaptadorCategorias(Utilidades.listaCategorias);
        recyclerCategorias.setAdapter(adaptadorCategorias);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (Activity) context;
            comunicaFragments = (ComunicaFragments) activity;

        }
    }
}