package com.delaroystudios.sqlitelogin.fragments;

import com.delaroystudios.sqlitelogin.R;
import com.delaroystudios.sqlitelogin.helper.TableUser;
import com.delaroystudios.sqlitelogin.sql.DatabaseHelper;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.util.ArrayList;

public class UsersFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private TableLayout tabla;
    private String[] header = {"Nombre", "Correo"};
    private ArrayList<String[]> rows = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_users, container, false);
        tabla = (TableLayout)v.findViewById(R.id.user_table);

        databaseHelper = new DatabaseHelper(getActivity());
        TableUser tabla_datos = new TableUser(tabla, getActivity());
        tabla_datos.addHeader(header);
        tabla_datos.addData(getDatos());

        return v;
    }

    private ArrayList<String[]> getDatos()
    {
        String nombre;
        String correo;

        Cursor datos = databaseHelper.getAllUsers();

        if(datos.moveToFirst())
        {
            do {
                nombre = datos.getString(1);
                correo = datos.getString(2);
                rows.add(new String[]{nombre, correo});
            } while(datos.moveToNext());
        }

        return rows;
    }

}
