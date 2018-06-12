package com.example.akarshsingh.touritinerary;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akarshsingh.touritinerary.DatabaseHelper;

import org.w3c.dom.Text;

import java.util.List;

public class addTI_recyclerAdapter extends RecyclerView.Adapter<addTI_recyclerAdapter.ViewHolder> {

    List<travelInfoModelClass> travelInfoModelClassList;
    Context c;

    public addTI_recyclerAdapter(List<travelInfoModelClass> travelInfoModelClassList)
    {
        this.travelInfoModelClassList = travelInfoModelClassList;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.tinum.setText(travelInfoModelClassList.get(position).getTi_number());
        holder.fromplace.setText(travelInfoModelClassList.get(position).getFrom());
        holder.toplace.setText(travelInfoModelClassList.get(position).getTo());
        holder.travelmode.setImageResource(travelInfoModelClassList.get(position).getTravelmode());

        holder.date_dept.setText(travelInfoModelClassList.get(position).getDate());
        holder.time_dept.setText(travelInfoModelClassList.get(position).getTime());
        holder.purposeTV.setText(travelInfoModelClassList.get(position).getPurpose());


        holder.collapsable_layout.setVisibility(View.GONE);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTravel(v,position);
            }
        });

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
        return travelInfoModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tinum;
        TextView fromplace;
        TextView toplace;
        TextView date_dept;
        TextView time_dept;
        TextView purposeTV;
        ImageView travelmode;
        RelativeLayout header_infolayout;
        RelativeLayout collapsable_layout;
        ImageButton delete;

        public ViewHolder(View view)
        {
            super(view);
            tinum = (TextView)view.findViewById(R.id.ti_num);
            fromplace = (TextView)view.findViewById(R.id.from_source);
            date_dept = (TextView)view.findViewById(R.id.dateSelected);
            time_dept = (TextView)view.findViewById(R.id.TimeSelected);
            toplace = (TextView)view.findViewById(R.id.to_destination);
            travelmode = (ImageView)view.findViewById(R.id.train_icon);
            purposeTV = (TextView)view.findViewById(R.id.purpose_text);
            header_infolayout = (RelativeLayout)view.findViewById(R.id.header_info);
            collapsable_layout =(RelativeLayout)view.findViewById(R.id.collapsable_info);
            delete = (ImageButton)view.findViewById(R.id.delete);
        }


    }

    public void deleteTravel(View view,int pos)
    {
        //GET ID
        travelInfoModelClass p=travelInfoModelClassList.get(pos);
        int id=p.getId();

        c = view.getContext();
        DatabaseHelper helper = new DatabaseHelper(c);
        helper.getWritableDatabase();
        if (helper.deletetravel(id))
        {
            this.notifyItemRangeChanged(pos,travelInfoModelClassList.size());
            travelInfoModelClassList.remove(pos);
        }
        else
        {
            Toast.makeText(c,"Unable to delete",Toast.LENGTH_SHORT).show();
        }
        helper.close();

        this.notifyItemRemoved(pos);

    }
}
