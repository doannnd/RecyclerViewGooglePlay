package com.nguyendinhdoan.recyclerviewgoogleplay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyendinhdoan.recyclerviewgoogleplay.R;
import com.nguyendinhdoan.recyclerviewgoogleplay.model.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder> {

    private Context context;
    private List<ListItem> itemList;

    public ItemListAdapter(Context context, List<ListItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_recycler_view, viewGroup, false);
        return new ItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder itemListViewHolder, int position) {
        ListItem listItem = itemList.get(position);

        // get data from list item
        String name = listItem.getName();
        String image = listItem.getImage();

        // set data for view
        itemListViewHolder.itemTitleTextView.setText(name);
        Picasso.get().load(image).into(itemListViewHolder.itemImageView);

        // action listener
        itemListViewHolder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClickListener(View view, int position) {
                Toast.makeText(context, "Name: " + itemList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (itemList != null) ? itemList.size() : 0;
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView itemTitleTextView;
        private ImageView itemImageView;
        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public ItemListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitleTextView = itemView.findViewById(R.id.item_title_text_view);
            itemImageView = itemView.findViewById(R.id.item_image_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.itemClickListener(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void itemClickListener(View view, int position);
    }
}
