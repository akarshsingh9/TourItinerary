package com.example.akarshsingh.touritinerary;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class addTI_recyclerAdapter extends RecyclerView.Adapter<addTI_recyclerAdapter.ViewHolder> {

    List<addTI_recyclermodel> addTIRecyclermodelList;

    public addTI_recyclerAdapter(List<addTI_recyclermodel> addTIRecyclermodelList)
    {
        this.addTIRecyclermodelList = addTIRecyclermodelList;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticreated_recycler_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.tinum.setText(addTIRecyclermodelList.get(position).getTi_number());
        holder.fromplace.setText(addTIRecyclermodelList.get(position).getFrom_place());
        holder.toplace.setText(addTIRecyclermodelList.get(position).getTo_place());
        holder.travelmode.setImageResource(addTIRecyclermodelList.get(position).getTravelmode_img());

        holder.collapsable_layout.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //boolean shouldExpand = holder.collapsable_layout.getVisibility() == View.GONE;

                if(holder.collapsable_layout.getVisibility() == View.GONE)
                {
                    holder.collapsable_layout.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.collapsable_layout.setVisibility(View.GONE);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return addTIRecyclermodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tinum;
        TextView fromplace;
        TextView toplace;
        TextView date_dept;
        TextView time_dept;
        ImageView travelmode;
        RelativeLayout header_infolayout;
        RelativeLayout collapsable_layout;

        public ViewHolder(View view)
        {
            super(view);
            tinum = (TextView)view.findViewById(R.id.ti_num);
            fromplace = (TextView)view.findViewById(R.id.from_source);
            //date_dept = (TextView)view.findViewById(R.id.dateTime);
            //time_dept = (TextView)view.findViewById(R.id.dateTime);
            toplace = (TextView)view.findViewById(R.id.to_destination);
            travelmode = (ImageView)view.findViewById(R.id.train_icon);
            header_infolayout = (RelativeLayout)view.findViewById(R.id.header_info);
            collapsable_layout =(RelativeLayout)view.findViewById(R.id.collapsable_info);

        }


    }
}
