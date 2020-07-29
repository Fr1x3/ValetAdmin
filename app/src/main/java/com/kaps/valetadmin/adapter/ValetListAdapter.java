package com.kaps.valetadmin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kaps.valetadmin.R;
import com.kaps.valetadmin.models.Valet;

import java.util.List;

public class ValetListAdapter extends RecyclerView.Adapter<ValetListAdapter.ViewHolder> {

    List<Valet> mValets;
    Context context;

    public ValetListAdapter(Context context, List<Valet> valets){
        this.mValets = valets;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(context).inflate(R.layout.valet_list_item, parent, false);
        return  new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Valet valet = mValets.get(position);
        holder.mName.setText(valet.getFistName() + " " + valet.getLastName());
        holder.mEmail.setText(valet.getEmail());

    }

    @Override
    public int getItemCount() {
        if(  mValets != null)
            return mValets.size();
        return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mIcon;
        public final TextView mName;
        public final TextView mEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mIcon = itemView.findViewById(R.id.img_valet_icon);
            mName = itemView.findViewById(R.id.tv_full_name);
            mEmail = itemView.findViewById(R.id.tv_email_list);
        }
    }
}
