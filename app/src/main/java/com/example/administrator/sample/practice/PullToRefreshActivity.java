package com.example.administrator.sample.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.sample.R;
import com.example.administrator.sample.bean.VideoBean;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shizi on 2016/11/9 0009.
 */

public class PullToRefreshActivity extends AppCompatActivity {
    PullToRefreshGridView gridView;
    private List<VideoBean> datas = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_pulltorefresh);

        gridView = (PullToRefreshGridView) findViewById(R.id.gridview);
        initData();
//        initViewStyle();
        gridView.setAdapter(new GridViewAdapter());

        /**
         * click listener
         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PullToRefreshActivity.this,"点击了"+ (position+1) + "个",Toast.LENGTH_SHORT).show();;
            }
        });
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                //TODO refresh
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                //TODO loading more
            }
        });
    }

    /**
     * 设置刷新/加载样式
     */
    private void initViewStyle() {
        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout il = gridView.getLoadingLayoutProxy();
        il.setPullLabel("下拉刷新");
        il.setReleaseLabel("释放立即刷新");
        il.setRefreshingLabel("正在刷新");
        gridView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        gridView.getLoadingLayoutProxy(false, true).setReleaseLabel("释放立即加载");
        gridView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载");
    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            VideoBean mVideoBean = new VideoBean();
            mVideoBean.setTitle("视频" + i);
            mVideoBean.setUserName("萌萌"+(i+1)+"号");
            mVideoBean.setViewCount((i+1)*1000);
            datas.add(mVideoBean);
        }
    }

    class GridViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size() == 0 ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHolder myHolder;
            if (convertView==null){
                convertView = LayoutInflater.from(PullToRefreshActivity.this).inflate(R.layout.item_pulltorefresh_list,null);
                myHolder = new MyHolder();
                myHolder.title = (TextView) convertView.findViewById(R.id.title_video_item_list);
                myHolder.userName = (TextView) convertView.findViewById(R.id.username_video_item_list);
                myHolder.count = (TextView) convertView.findViewById(R.id.count_video_item_list);
                convertView.setTag(myHolder);
            }else {
                myHolder = (MyHolder) convertView.getTag();
            }
            myHolder.title.setText(datas.get(position).getTitle());
            myHolder.userName.setText(datas.get(position).getUserName());
            myHolder.count.setText(datas.get(position).getViewCount()+"");

            return convertView;
        }
    }
    class MyHolder{
        TextView title;
        TextView userName;
        TextView count;
    }
}
