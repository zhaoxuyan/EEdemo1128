package me.yokeyword.sample.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.helper.NetWorkUtils;

/**
 * Created by wang on 2017/10/23.
 */


public class MapActivity extends AppCompatActivity {
    private TextureMapView mMapView;
    private BaiduMap baiduMap;
    private MapStatusUpdate msu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现


        //？？？
        try {
            SDKInitializer.initialize(getApplicationContext());
        } catch (NullPointerException e) {
            System.out.println("null ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_map);
        mMapView = (TextureMapView) findViewById(R.id.bmapView);
        baiduMap = mMapView.getMap();
        //msu = MapStatusUpdateFactory.newLatLng(new LatLng(29.495801,106.576364));
        msu = MapStatusUpdateFactory.newLatLngZoom(new LatLng(29.495801, 106.576364), 19);
        // baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(18).build()));//设置缩放级别

        baiduMap.setMapStatus(msu);
        //logNetWorkInfo();

    }

    private void logNetWorkInfo() {
        switch (NetWorkUtils.getConnectedType(MapActivity.this)) {
            case 0: {
                Toast.makeText(MapActivity.this, "没有链接到网络，地图可能无法展示", Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                Toast.makeText(MapActivity.this, "当前使用WIFI网络", Toast.LENGTH_SHORT).show();
                break;
            }
            case 2: {
                Toast.makeText(MapActivity.this, "当前使用2G网络", Toast.LENGTH_SHORT).show();
                break;
            }
            case 3: {
                Toast.makeText(MapActivity.this, "当前使用3G网络", Toast.LENGTH_SHORT).show();
                break;
            }
            case 4: {
                Toast.makeText(MapActivity.this, "当前使用4G网络", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        //unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

}
