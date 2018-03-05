package com.neman.morph.morphwallet.Adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neman.morph.morphwallet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rharo on 2/22/18.
 */

public class StringRecyclerAdapter extends RecyclerView.Adapter<StringRecyclerAdapter.ViewHolder> {
    private final Context mContext;
    private final List<String> list;
    private final LayoutInflater LInflater;

    public StringRecyclerAdapter(Context c, ArrayList<String> _list) {
        mContext = c;
        list = _list;
        LInflater = LayoutInflater.from(c);
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View itemView = LInflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = list.get(position);
        holder.textInfo.setText(data);
        holder.mCurrentPos = position;
    }

    public void updateData(ArrayList<String> newList) {
        list.clear();
        list.addAll(newList);
        //notifyDataSetChanged();
    }


//    public void onBindViewHolder(ViewHolder holder, int pos) {
//        T data = list.get(pos);
//        holder.textInfo.setText( "Data passed in converted to string: " + (String) data);
//    }

    @Override
    public int getItemCount() {

        if(list !=null){
            return list.size();
        }else{

            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textInfo;
        public int mCurrentPos;

        public ViewHolder(View itemView) {
            super(itemView);
            textInfo = (TextView) itemView.findViewById(R.id.text_info);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, mCurrentPos+"", Snackbar.LENGTH_LONG).show();
                }
            });

        }

    }
}
