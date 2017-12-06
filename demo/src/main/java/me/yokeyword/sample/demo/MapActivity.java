package me.yokeyword.sample.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.helper.NetWorkUtils;

/**
 * Created by wang on 2017/10/23.
 */


public class MapActivity extends AppCompatActivity {
    private TextureMapView mMapView;
    private BaiduMap mBaiduMap;
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
        mBaiduMap = mMapView.getMap();

        //构建MarkerOption，用于在地图上添加Marker
        LatLng hospital_point = new LatLng(29.4937262027, 106.5779192736);

        //msu = MapStatusUpdateFactory.newLatLng(new LatLng(29.495801,106.576364));
        msu = MapStatusUpdateFactory.newLatLngZoom(hospital_point, 20);
        // baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(18).build()));//设置缩放级别

        mBaiduMap.setMapStatus(msu);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_map_hospital);

        OverlayOptions option = new MarkerOptions()
                .position(hospital_point)
                .icon(bitmap);

        //在地图上添加Marker，并显示

        mBaiduMap.addOverlay(option);

        //logNetWorkInfo();

//        // 自定义定位模式、定位图标、精度圈颜色
//        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
//        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_map_locate);
//        int accuracyCircleFillColor = 0xAAFFFF88;
//        int accuracyCircleStrokeColor = 0xAA00FF00;
//        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
//                mCurrentMode, true, mCurrentMarker,
//                accuracyCircleFillColor, accuracyCircleStrokeColor));
//
//        // 构造定位数据
//        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(location.getRadius())
//                // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(100).latitude(location.getLatitude())
//                .longitude(location.getLongitude()).build();
//
//        // 设置定位数据
//        mBaiduMap.setMyLocationData(locData);
//
//        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        mCurrentMarker = BitmapDescriptorFactory
//                .fromResource(R.drawable.ic_map_locate);
//        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
//        mBaiduMap.setMyLocationConfiguration();
//
//        // 当不需要定位图层时关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);

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
