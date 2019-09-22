package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context context;
    List<ItemModal> itemModalList;

    public float getTotal(){
        float total = 0;
        for(int i=0;  i<itemModalList.size(); i++){
            total += (itemModalList.get(i).getInventory() * itemModalList.get(i).getPrice());
        }
        return total;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name;
        public TextView desc;
        public TextView inventory;
        public ImageView image;
        public TextView price;
        public com.google.android.material.button.MaterialButton add;
        public com.google.android.material.button.MaterialButton sub;
        public CardView card_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.item_name = itemView.findViewById(R.id.item_name);
            this.desc = itemView.findViewById(R.id.description);
            this.inventory = itemView.findViewById(R.id.inventory);
            this.image = itemView.findViewById(R.id.image);
            this.price = itemView.findViewById(R.id.price);
            this.add = itemView.findViewById(R.id.add);
            this.sub = itemView.findViewById(R.id.sub);
            this.card_view = itemView.findViewById(R.id.card_view);
        }
    }

    public ListAdapter(List<ItemModal> itemModalList) {
        this.itemModalList = itemModalList;
    }

    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_layout, null);
        MyViewHolder vh = new MyViewHolder(view);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.card_view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));

        final ItemModal itemModal = itemModalList.get(position);
//        id.setText(String.valueOf(itemModal.getId()));
        holder.item_name.setText(itemModal.getName());
        holder.desc.setText(itemModal.getDescription());
//        category.setText(itemModal.getCategory());
        holder.inventory.setText(String.valueOf(itemModal.getInventory()));
        Picasso.get()
                .load(context.getResources().getIdentifier(itemModal.getImage(), "drawable", context.getPackageName()))
                .resize(400, 200)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.image);
        holder.price.setText("₹"+String.valueOf(itemModal.getPrice())+"/item");

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(holder.inventory.getText().toString());
                if(qty < itemModal.getQuantity()) {
                    qty++;
                    itemModal.setInventory(qty);
                }
                else
                    Snackbar.make(v, "Cannot order more than "+ itemModal.getQuantity() +" " +itemModal.getName(), Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                holder.inventory.setText(String.valueOf(qty));
            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(holder.inventory.getText().toString());
                if(qty != 0) {
                    qty--;
                    itemModal.setInventory(qty);
                }
                else
                    Snackbar.make(v, "You can order only 0 or more "+itemModal.getName(), Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                holder.inventory.setText(String.valueOf(qty));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModalList.size();
    }
}
