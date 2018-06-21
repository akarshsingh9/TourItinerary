package com.example.akarshsingh.touritinerary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    List<Search> searchList;

    SearchAdapter(List<Search> searchList)
    {
        this.searchList = searchList;

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler,parent,false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {

        holder.roid.setText(searchList.get(position).getRoid());
        holder.rograde.setText(searchList.get(position).getRoGrade());
        holder.roname.setText(searchList.get(position).getRoname());
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView roname;
        TextView rograde;
        TextView roid;

        public SearchViewHolder(View itemView) {
            super(itemView);
            roname = (TextView)itemView.findViewById(R.id.roname);
            roid = (TextView)itemView.findViewById(R.id.roid);
            rograde = (TextView)itemView.findViewById(R.id.rograde);
        }
    }
}
