package com.a256.fortune256.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a256.fortune256.R;
import com.a256.fortune256.data.ListData;

import java.util.List;

/**
 * Created by de165 on 2018/2/5.
 */

public class MyListViewAdapter extends BaseAdapter{
    private Context mContext;
    private List mList;
    private LayoutInflater inflater;
    public interface OnItemClickListener{
        void onClicked(int position);
    };
    private OnItemClickListener mListener;
    public MyListViewAdapter(Context context,@Nullable List<ListData> list){
        this.mContext = context;
        this.mList = list;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return (mList == null)?0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.common_list_view,null);
        ListData data = (ListData) getItem(position);
        ImageView icon= view.findViewById(R.id.common_icon);
        TextView text= view.findViewById(R.id.common_text);
        icon.setImageResource(data.getImage_index());
        text.setText(data.getText());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onClicked(position);
                }
            }
        });
        return view;
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

}
