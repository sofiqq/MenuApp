package com.example.menuapp.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuapp.R;
import com.example.menuapp.model.MenuItem;

import java.util.ArrayList;

import static com.example.menuapp.Helper.getTypeById;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    ArrayList<MenuItem> data;
    Context context;
    int[] orders;

    public MenuAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(ArrayList<MenuItem> data) {
        this.data = data;
        orders = new int[data.size()];
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvName;
        TextView tvCost;
        CardView flAdd;
        CardView flRemove;
        TextView tvCount;
        FrameLayout flType;
        TextView tvType;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCost = itemView.findViewById(R.id.tv_cost);
            flAdd = itemView.findViewById(R.id.fl_add);
            flRemove = itemView.findViewById(R.id.fl_remove);
            tvCount = itemView.findViewById(R.id.tv_count);
            flType = itemView.findViewById(R.id.fl_type);
            tvType = itemView.findViewById(R.id.tv_type);
//            itemView.setOnClickListener(this);
        }

        public void bind(MenuItem item) {
            int id = context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());
            ivImage.setImageResource(id);
            tvName.setText(item.getName());
            if (getAdapterPosition() == 0 || item.getType() != data.get(getAdapterPosition() - 1).getType()) {
                tvType.setText(getTypeById(item.getType()));
                flType.setVisibility(View.VISIBLE);
            }
            tvCost.setText("KZT " + item.getCost());
            tvCount.setText(String.valueOf(orders[getAdapterPosition()]));
//            flAdd.setOnClickListener(this);
//            flRemove.setOnClickListener(this);
            flAdd.setOnClickListener(v -> {
                orders[getAdapterPosition()]++;
                flRemove.setVisibility(View.VISIBLE);
                notifyDataSetChanged();
                ((MainActivity) context).countOrder(orders);
            });
            flRemove.setOnClickListener(v -> {
                orders[getAdapterPosition()]--;
                if (orders[getAdapterPosition()] == 0)
                    flRemove.setVisibility(View.INVISIBLE);
                notifyDataSetChanged();
                ((MainActivity) context).countOrder(orders);
            });
        }

//        @Override
//        public void onClick(View v) {
//            Log.e("ASD", "click");
//            switch (v.getId()) {
//                case R.id.fl_add:
//                    orders[getAdapterPosition()]++;
//                    flRemove.setVisibility(View.VISIBLE);
//                    break;
//                case R.id.fl_remove:
//                    if (orders[getAdapterPosition()] > 0) {
//                        flRemove.setVisibility(View.INVISIBLE);
//                        orders[getAdapterPosition()]--;
//                    }
//            }
//        }
    }
}
