package com.nguyendinhdoan.recyclerviewgoogleplay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyendinhdoan.recyclerviewgoogleplay.R;
import com.nguyendinhdoan.recyclerviewgoogleplay.model.ListItem;
import com.nguyendinhdoan.recyclerviewgoogleplay.model.MyData;

import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyDataViewHolder> {

    private Context context;
    private List<MyData> myDataList;

    public MyDataAdapter(Context context, List<MyData> myDataList) {
        this.context = context;
        this.myDataList = myDataList;
    }

    @NonNull
    @Override
    public MyDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_data_recycler_view, viewGroup, false);
        return new MyDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyDataViewHolder myDataViewHolder, int position) {
        MyData myData = myDataList.get(position);

        // get data of my data
        final String headerTitle = myData.getHeaderTitle();
        List<ListItem> itemList = myData.getListItem();

        // set data for view
        myDataViewHolder.headerTitleTextView.setText(headerTitle);
        // item list recycler view
        myDataViewHolder.itemListRecyclerView.setHasFixedSize(true);
        myDataViewHolder.itemListRecyclerView.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        ItemListAdapter itemListAdapter = new ItemListAdapter(context, itemList);
        myDataViewHolder.itemListRecyclerView.setAdapter(itemListAdapter);
        // hide nested scroll for item list recycler view
        myDataViewHolder.itemListRecyclerView.setNestedScrollingEnabled(false);
        
        // action listener
        myDataViewHolder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Title: " + headerTitle, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (myDataList != null) ? myDataList.size() : 0;
    }

    public class MyDataViewHolder extends RecyclerView.ViewHolder {

        private TextView headerTitleTextView;
        private Button moreButton;
        private RecyclerView itemListRecyclerView;

        public MyDataViewHolder(@NonNull View itemView) {
            super(itemView);

            headerTitleTextView = itemView.findViewById(R.id.header_title_text_view);
            moreButton = itemView.findViewById(R.id.more_button);
            itemListRecyclerView = itemView.findViewById(R.id.item_list_recycler_view);
        }
    }
}
