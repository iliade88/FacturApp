package com.gcs.facturapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gcs.facturapp.R;
import com.gcs.facturapp.models.Concepto;
import com.gcs.facturapp.models.Factura;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ConceptoAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private boolean factura_validada;
    private ArrayList<Concepto> mDataSource;

    public ConceptoAdapter(Context context, boolean factura_validada, ArrayList<Concepto> conceptos)
    {
        mContext = context;
        this.factura_validada = factura_validada;
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

    public void deleteItem(int position) {
        mDataSource.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView;

        if (convertView == null)
            rowView = mInflater.inflate(R.layout.list_item_concepto, parent, false);
        else
            rowView = convertView;


        TextView descripcion_concepto = (TextView) rowView.findViewById(R.id.descripcion_concepto);
        TextView precio_concepto = (TextView) rowView.findViewById(R.id.precio_concepto);
        TextView cantidad_concepto = (TextView) rowView.findViewById(R.id.cantidad_concepto);
        TextView precio_total_concepto = (TextView) rowView.findViewById(R.id.precio_total_concepto);
        final Button btn_delete_concepto = (Button) rowView.findViewById(R.id.btn_delete_concepto);

        final Concepto concepto = (Concepto) getItem(position);

        descripcion_concepto.setText(concepto.descripcion);
        precio_concepto.setText(Float.toString(concepto.precio) + "€");
        cantidad_concepto.setText(Integer.toString(concepto.cantidad));
        precio_total_concepto.setText(String.format("%.2f", concepto.precio * concepto.cantidad) + "€");

        if (factura_validada)
        {
            btn_delete_concepto.setVisibility(View.GONE);
        }

        btn_delete_concepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId())
                {
                    case R.id.btn_delete_concepto:
                        confirmarDelete(concepto, position);

                        break;
                }
            }
        });

        return rowView;
    }

    private void confirmarDelete(Concepto concepto, final int position)
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setMessage("¿Eliminar concepto '" +concepto.descripcion+ "'?");
        mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem(position);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });


        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
}
