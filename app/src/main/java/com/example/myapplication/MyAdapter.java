package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    //Declare our requirements that are needed for the Adapter to work

    Context c;
    HashMap<String, String> users;

    //Constructor for passing data to the adapter
    public MyAdapter(Context c, HashMap<String, String> users) {
        this.c = c;
        this.users = users;
    }

    //Attaching the item layout to the adapter
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item,parent,false);
        return new MyHolder(v);
    }

    //Binding data to the widgets in our MyHolder class
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    //This tells the recycler view how many items are needed
    @Override
    public int getItemCount() {
        return users.size();
    }
}
