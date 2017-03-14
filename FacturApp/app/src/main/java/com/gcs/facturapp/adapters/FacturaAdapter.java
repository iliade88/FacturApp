package com.gcs.facturapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.gcs.facturapp.R;
import com.gcs.facturapp.models.Cliente;
import com.gcs.facturapp.models.Factura;

/**
 * Created by Cris on 14/03/2017.
 */

public class FacturaAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Factura> mDataSource;

    public FacturaAdapter(Context context, ArrayList<Factura> facturas)
    {
        mContext = context;
        mDataSource = facturas;
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
            rowView = mInflater.inflate(R.layout.list_item_factura, parent, false);
        else
            rowView = convertView;


        TextView id_factura = (TextView) rowView.findViewById(R.id.id_factura);
        TextView fecha_factura = (TextView) rowView.findViewById(R.id.fecha_factura);
        TextView nombre_cliente = (TextView) rowView.findViewById(R.id.nombre_cliente);
        TextView precio_total = (TextView) rowView.findViewById(R.id.precio_total);

        Factura factura = (Factura) getItem(position);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        id_factura.setText(Long.toString(factura.id));
        String fecha = df.format(factura.fecha);
        /* Comentado porque se carga el layout, hasta que se encuentre solucion
        fecha_factura.setText(fecha);*/
        nombre_cliente.setText(factura.cliente.nombre+" "+factura.cliente.apellidos);
        precio_total.setText(String.format("%.2f", factura.precio_total) + "€");

        return rowView;
    }
}
