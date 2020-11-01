package com.mariotm.ddamq.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mariotm.ddamq.clases.ConexionSQLite;
import com.mariotm.ddamq.R;
import com.mariotm.ddamq.interfaces.ComunicaFragments;
import com.mariotm.ddamq.clases.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarCategoriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarCategoriaFragment extends Fragment {

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
    EditText categoria, descripcion;
    FloatingActionButton FabGuardarCategoria;

    public AgregarCategoriaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarCategoriaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarCategoriaFragment newInstance(String param1, String param2) {
        AgregarCategoriaFragment fragment = new AgregarCategoriaFragment();
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
        view = inflater.inflate(R.layout.fragment_agregar_categoria, container, false);

        categoria = (EditText) view.findViewById(R.id.edtCategoria);
        descripcion = (EditText) view.findViewById(R.id.edtDescripcion);
        FabGuardarCategoria = (FloatingActionButton) view.findViewById(R.id.FabGuardarCategoria);

        FabGuardarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCategoria();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
            comunicaFragments = (ComunicaFragments) activity;
        }
        /*
        if (context instanceof DashboardFragment.OnFragmentInteractionListener) {
            mListener = (DashboardFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        } */
    }

    public void agregarCategoria() {

        if (categoria.getText().toString() != null && !categoria.getText().toString().trim().equals("") &&
            descripcion.getText().toString() != null && !descripcion.getText().toString().trim().equals("") ) {
            Toast.makeText(activity, "Registo exitoso", Toast.LENGTH_SHORT).show();
            String nCategoria = categoria.getText().toString();
            String nDescripcion = descripcion.getText().toString();
            ConexionSQLite conectar = new ConexionSQLite(activity, Utilidades.BASE_DATOS,
                    null, 1);
            SQLiteDatabase db = conectar.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_CATEGORIA, nCategoria);
            values.put(Utilidades.CAMPO_DESCRIPCION, nDescripcion);
            Long idResult = db.insert(Utilidades.TABLA_CATEGORIAS, Utilidades.CAMPO_ID_CATEGORIA,
                    values);

            if (idResult != -1) {
                Toast.makeText(activity, "¡Registro exitoso!", Toast.LENGTH_SHORT).show();
                categoria.setText("");
                descripcion.setText("");

                

            } else {
                Toast.makeText(activity, "No se pudo realizar el registro", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(activity, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}