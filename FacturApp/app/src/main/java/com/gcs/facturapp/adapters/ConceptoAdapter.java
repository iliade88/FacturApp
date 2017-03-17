package com.gcs.facturapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gcs.facturapp.R;
import com.gcs.facturapp.models.Concepto;

import java.util.ArrayList;

public class ConceptoAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Concepto> mDataSource;

    public ConceptoAdapter(Context context, ArrayList<Concepto> conceptos)
    {
        mContext = context;
        mDataSource = conceptos;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;

        if (convertView == null)
            rowView = mInflater.inflate(R.layout.list_item_concepto, parent, false);
        else
            rowView = convertView;


        TextView descripcion_concepto = (TextView) rowView.findViewById(R.id.descripcion_concepto);
        TextView precio_concepto = (TextView) rowView.findViewById(R.id.precio_concepto);
        TextView cantidad_concepto = (TextView) rowView.findViewById(R.id.cantidad_concepto);
        TextView precio_total_concepto = (TextView) rowView.findViewById(R.id.precio_total_concepto);

        Concepto concepto = (Concepto) getItem(position);

        descripcion_concepto.setText(concepto.descripcion);
        precio_concepto.setText(Float.toString(concepto.precio));
        cantidad_concepto.setText(Integer.toString(concepto.cantidad));
        precio_total_concepto.setText(Float.toString(concepto.precio * concepto.cantidad) + "€");

        return rowView;
    }
}
