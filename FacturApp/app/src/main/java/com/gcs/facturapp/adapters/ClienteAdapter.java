package com.gcs.facturapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.gcs.facturapp.R;
import com.gcs.facturapp.models.Cliente;

import org.w3c.dom.Text;

/**
 * Created by Cris on 13/03/2017.
 */

public class ClienteAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Cliente> mDataSource;

    public ClienteAdapter(Context context, ArrayList<Cliente> clientes)
    {
        mContext = context;
        mDataSource = clientes;
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

        View rowView = mInflater.inflate(R.layout.list_item_cliente, parent, false);

        TextView nombre = (TextView) rowView.findViewById(R.id.nombre_cliente);
        TextView telefono = (TextView) rowView.findViewById(R.id.telefono_cliente);
        TextView email = (TextView) rowView.findViewById(R.id.email_cliente);

        Cliente cliente = (Cliente) getItem(position);

        nombre.setText(cliente.nombre);
        telefono.setText(cliente.telefono);
        email.setText(cliente.email);

        return rowView;
    }
}
