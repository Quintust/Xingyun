package com.a256.fortune256.viewer;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.a256.fortune256.R;
import com.a256.fortune256.debug.T;
import com.a256.fortune256.net.DataRequester;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.DropboxHeader;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Created by de165 on 2018/2/26.
 */

public class TendPage {

    private Context context = null;

    private JsonArray data = null;

    private ListView listView = null;

    private SmartRefreshLayout refreshLayout = null;

    private View view = null;

    private int num = 10;

    private TendAdapter adapter = null;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            data = (JsonArray)msg.obj;
            refreshData();
        }
    };
    public TendPage() {

    }
    private void getData(String count){
        if(count == null || count.equals(""))
            count = "10";
        final String num = count;
        new Thread(){
            @Override
            public void run() {
                Message message = new Message();
                message.obj = DataRequester.getFortune(num);
                mHandler.sendMessage(message);
            }
        }.start();
    }

    public View init(Context context){
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.tend,null);
        initView(context);
        getData(num+"");
        return this.view;
    }

    private void initView(Context context){

        listView = this.view.findViewById(R.id.tend_listview);
        adapter = new TendAdapter(context);
        adapter.setData(data);
        listView.setAdapter(adapter);

        refreshLayout = this.view.findViewById(R.id.tend_refreshlayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refresh) {
                num = num + 10;
                getData(num+"");
                refreshLayout.finishRefresh();
                refresh.finishRefresh(3000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refresh) {
                num = num + 10;
                listView.setSelection(adapter.getCount()-1);
                getData(num+"");
                refreshLayout.finishLoadmore();
                refresh.finishLoadmore(3000);
            }
        });
    }

    private void refreshData(){
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    class TendAdapter extends BaseAdapter{

        private JsonArray list = null;

        private Context context = null;

        TendAdapter(Context context){
            this.context = context;
        }

        public void setData(JsonArray list){
            this.list = list;
        }

        @Override
        public int getCount() {
            return (list == null)?0:list.size();
        }

        @Override
        public Object getItem(int position) {
            return this.list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            LayoutInflater inflater = LayoutInflater.from(this.context);
            JsonObject obj = list.get(position).getAsJsonObject();
            view = inflater.inflate(R.layout.tend_item,null);
            LinearLayout layout = view.findViewById(R.id.tend_layout);
            TextView id = view.findViewById(R.id.tend_id);
            TextView num = view.findViewById(R.id.tend_number);
            TextView[] eachs = new TextView[5];
            eachs[0]  = view.findViewById(R.id.tend_mil);
            eachs[1] = view.findViewById(R.id.tend_th);
            eachs[2] = view.findViewById(R.id.tend_hund);
            eachs[3] = view.findViewById(R.id.tend_tenth);
            eachs[4] = view.findViewById(R.id.tend_single);

            layout.setBackgroundColor(Color.WHITE);
            id.setTextColor(Color.BLACK);
            num.setTextColor(Color.RED);
            eachs[0].setTextColor(Color.BLACK);
            eachs[1].setTextColor(Color.BLACK);
            eachs[2].setTextColor(Color.BLACK);
            eachs[3].setTextColor(Color.BLACK);
            eachs[4].setTextColor(Color.BLACK);

            String expect = obj.get("expect").toString().replace("\"","");
            int length = expect.length();
            id.setText(expect.substring(length-3, length)+"期");

            String openCode = obj.get("openCode").toString().replace("\"","").replace(","," ");
            num.setText(openCode);
            String[] codes = openCode.split(" ");
            for (int i = 0;i<codes.length;i++) {
                int value = Integer.parseInt(codes[i]);
                String maxOrMin = value<5?"小":"大";
                String singleOrDouble = (value%2==0)?"双":"单";
                eachs[i].setText(maxOrMin+singleOrDouble);
            }

            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    150));

            return view;
        }
    }
}
