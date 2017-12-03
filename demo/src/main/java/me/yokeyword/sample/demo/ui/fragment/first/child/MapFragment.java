package me.yokeyword.sample.demo.ui.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.base.BaseBackFragment;
import me.yokeyword.sample.demo.base.NetWorkUtils;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;


/**
 * Created by SyaoKyo on 2017/12/3.
 */

public class MapFragment extends BaseBackFragment {
    private TextureMapView mMapView;
    private BaiduMap baiduMap;
    private MapStatusUpdate msu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //？？？
        try {
            SDKInitializer.initialize(getActivity().getApplicationContext());
        }
        catch (NullPointerException e)
        {
            System.out.println("null ");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        getActivity().setContentView(R.layout.fragment_map);
        mMapView = (TextureMapView) _mActivity.findViewById(R.id.bmapView);//R.id.bmapView
        baiduMap = mMapView.getMap();
        //msu = MapStatusUpdateFactory.newLatLng(new LatLng(29.495801,106.576364));
        msu = MapStatusUpdateFactory.newLatLngZoom(new LatLng(29.495801, 106.576364), 19);
        // baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(18).build()));//设置缩放级别

        baiduMap.setMapStatus(msu);
        //logNetWorkInfo();
        return view;
    }

    private void logNetWorkInfo() {
        switch (NetWorkUtils.getConnectedType(getActivity().getApplicationContext())) {
            case 0: {
                Toast.makeText(getActivity().getApplicationContext(), "没有链接到网络，地图可能无法展示", Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                Toast.makeText(getActivity().getApplicationContext(), "当前使用WIFI网络", Toast.LENGTH_SHORT).show();
                break;
            }
            case 2: {
                Toast.makeText(getActivity().getApplicationContext(), "当前使用2G网络", Toast.LENGTH_SHORT).show();
                break;
            }
            case 3: {
                Toast.makeText(getActivity().getApplicationContext(), "当前使用3G网络", Toast.LENGTH_SHORT).show();
                break;
            }
            case 4: {
                Toast.makeText(getActivity().getApplicationContext(), "当前使用4G网络", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        //unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    public static MapFragment newInstance() {

        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
