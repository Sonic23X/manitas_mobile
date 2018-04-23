package com.delaroystudios.sqlitelogin.helper;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.content.Context;
import android.widget.TableRow;
import android.widget.TextView;

import com.delaroystudios.sqlitelogin.R;

import java.util.ArrayList;

public class TableUser {

    private TableLayout tableLayout;
    private Context context;
    private String[] header;
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;


    public TableUser(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader(String[] header)
    {
        this.header = header;
        createHeader();
    }

    public void addData(ArrayList<String[]> data)
    {
        this.data = data;
        ceateDataTable();
    }

    private void newRow()
    {
        tableRow = new TableRow(context);
    }

    private void newCell()
    {
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(25);
    }

    private void createHeader()
    {
        indexC = 0;
        newRow();
        Resources recurso = context.getResources();
        while(indexC < header.length)
        {
            newCell();
            txtCell.setText(header[indexC++]);
            txtCell.setTextColor(recurso.getColor(android.support.design.R.color.primary_dark_material_light));
            tableRow.addView(txtCell, newTableRowParams());
            tableRow.setBackgroundColor(recurso.getColor(android.support.design.R.color.primary_material_dark));
        }
        tableLayout.addView(tableRow);
    }

    private void ceateDataTable()
    {
        String info;
        Resources recurso = context.getResources();
        for (indexR = 0; indexR < data.size(); indexR++)
        {
            newRow();
            for (indexC = 0; indexC < header.length; indexC++)
            {
                newCell();
                String[] colums = data.get(indexR);
                info = (indexC < colums.length) ? colums[indexC]: "";
                txtCell.setText(info);
                tableRow.addView(txtCell, newTableRowParams());
                tableRow.setBackgroundColor(recurso.getColor(R.color.colorPrimaryDark));
            }
            tableLayout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams()
    {
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);

        params. weight = 1;
        return params;
    }

}
