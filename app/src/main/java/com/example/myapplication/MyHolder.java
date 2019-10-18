package com.example.myapplication;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    //Responsible for attaching our widget to our adapter
    //It is also responsible for holding the state of a view
    //Declare our widgets
    TextView tv_id,tv_name,tv_email,tv_phone,tv_gender;

    //Constructor for passing our view from the adapter
    public MyHolder(View itemView) {
        super(itemView);

        //Initialize our widgets using the passed view
        tv_id = itemView.findViewById(R.id.tv_id);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_email = itemView.findViewById(R.id.tv_email);
        tv_phone = itemView.findViewById(R.id.tv_phone);
        tv_gender = itemView.findViewById(R.id.tv_gender);
    }
}
