package com.example.recyclerviewsyora;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.activity_main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NameListAdapter adapter = new NameListAdapter();
        recyclerView.setAdapter(adapter);

        for(int i=0; i<40; i++){
            adapter.addName("Name_" + Integer.toString(i));
        }

    }

    private class NameListAdapter extends RecyclerView.Adapter<NameListViewHolder> {

        private final ArrayList<String> names;

        public NameListAdapter(){
            names = new ArrayList<>();
        }

        public void addName(String name){
            names.add(name);
            notifyItemInserted(names.size() - 1);
        }

        public void removeName(String name){
            int position = names.indexOf(name);
            names.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public int getItemCount() {
            return names.size();
        }

        //下两个函数可以理解成 ListView 当中的 getView
        @Override
        public NameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 为给定的 View 实例化一个 ViewHolder,创建View
            View view = getLayoutInflater().inflate(R.layout.list_item_name,parent,false);
            return new NameListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NameListViewHolder holder, int position) {
            //为给定position 上的 Item 绑定ViewHolder上的数据，
            // populate the view with data at 相应的 position
            String name = names.get(position);
            holder.NameTextView.setText(name);

            if(position %2 == 0 ){
                holder.NameTextView.setBackgroundColor(Color.parseColor("#22000000"));
            }else{
                holder.NameTextView.setBackground(null);
            }
        }
    }

    private class NameListViewHolder extends RecyclerView.ViewHolder {
        public TextView NameTextView;

        public NameListViewHolder(View itemView) {
            super(itemView);
            NameTextView = (TextView) itemView.findViewById(R.id.list_item_name_name);
        }
    }
}
