package com.flipingtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.flipingtest.flingswipe.SwipeFlingAdapterView;
import com.flipingtest.model.CardEntity;
import java.util.ArrayList;

public class MainActivity extends Activity implements CardAdapter.OnCardExitListener {
    private SwipeFlingAdapterView flingContainer;
    private ArrayList<CardEntity> data=new ArrayList<CardEntity>();
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flingContainer= (SwipeFlingAdapterView) findViewById(R.id.fling_container);
        data.add(new CardEntity("邀请","张1","产品经理","百度","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张2","android","阿里巴巴","杭州","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张3","ios","高德地图","上海","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张4","java","腾讯科技","深圳","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张5","php","携程旅行网","上海","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张6","python","去哪儿网","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张7","C++","美团","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张8","数据库","58同城","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张9","BD","奇虎360","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张10","UED","搜狐视频","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张11","BI","爱奇艺","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张12","QA","小米科技","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张13","js","搜狗","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张14","html5","滴滴打车","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张15","理财顾问","新浪微博","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张16","猎头","京东","北京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张17","星探","途牛网","南京","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));
        data.add(new CardEntity("邀请","张18","金融","同程网","上海","您是他的通讯录联系人","我是xxx的xxx想加你好友",""));

        adapter=new CardAdapter(this,data);
        adapter.setOnCardExitListener(this);
        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                if(dataObject instanceof CardEntity){
                    CardEntity item = (CardEntity) dataObject;
                    if(data.contains(item)){
                        data.remove(item);
                    }
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if(dataObject instanceof CardEntity){
                    CardEntity item = (CardEntity) dataObject;
                    if(data.contains(item)){
                        data.remove(item);
                        adapter.notifyDataSetChanged();
                        data.add(item);
                    }
                }
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                if(data==null||data.isEmpty()){
                    //TODO

                    Toast.makeText(MainActivity.this,"data is empty",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                try {
                    View selectedView = flingContainer.getSelectedView();
                    selectedView.findViewById(R.id.fl_ignore).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);//左滑忽略
                    selectedView.findViewById(R.id.fl_next).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);//右滑下一条
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }


    @Override
    public void onLeftExit(CardEntity item) {
        flingContainer.getTopCardListener().selectLeft();
    }

    @Override
    public void onRightExit(CardEntity item) {
        flingContainer.getTopCardListener().selectRight();
    }
}
