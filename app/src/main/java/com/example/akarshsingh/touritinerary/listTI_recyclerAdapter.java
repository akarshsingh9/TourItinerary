package com.example.akarshsingh.touritinerary;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class listTI_recyclerAdapter extends RecyclerView.Adapter<listTI_recyclerAdapter.ViewHolder> {

    //Model Class List declared
    List<listTI_modelClass> listTI_modelClassList;

//===============================================================================================================
    //Adpater class constructor defined
    public listTI_recyclerAdapter(List<listTI_modelClass> modelClassList) {

        this.listTI_modelClassList = modelClassList;
    }
//===============================================================================================================
    //onCreateViewHolder - inflate list item layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
//==================================================================================================================
    //onBindViewHolder - defined setText() and color
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.listTI_name.setText(listTI_modelClassList.get(position).getOfficer_name());
        holder.TI_no.setText(listTI_modelClassList.get(position).getTi_no());
        holder.status_TI.setText(listTI_modelClassList.get(position).getStatus_ti());
        if(listTI_modelClassList.get(position).getStatus_ti() == "Accepted")
        {
            holder.status_TI.setTextColor(Color.parseColor("#4CAF50"));
        }
        if(listTI_modelClassList.get(position).getStatus_ti() == "Pending")
        {
            holder.status_TI.setTextColor(Color.parseColor("#F44336"));
        }

    }
//===================================================================================================================
    //list item count
    @Override
    public int getItemCount() {
        return listTI_modelClassList.size();
    }

//===================================================================================================================
    //default
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
//====================================================================================================================
    //ViewHolder Class defined to initialize all list item layout views
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listTI_name;
        TextView TI_no;
        TextView status_TI;

        public ViewHolder(View v)
        {
            super(v);
            listTI_name = (TextView)v.findViewById(R.id.listTi_name);
            TI_no = (TextView)v.findViewById(R.id.listTI_tino);
            status_TI = (TextView)v.findViewById(R.id.listTI_status);

        }

    } // end of ViewHolder class
//======================================================================================================================
}// end of adapter class
