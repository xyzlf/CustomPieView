package com.xyzlf.view.pieview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyzlf.view.pieviewlib.CircleView;
import com.xyzlf.view.pieviewlib.CustomPieData;
import com.xyzlf.view.pieviewlib.CustomPieView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 初始化View组件 **/
        //自定义饼状图
        CustomPieView pieView = (CustomPieView) findViewById(R.id.asset_pieview);
        //小圆点容器
        LinearLayout pieContainer = (LinearLayout) findViewById(R.id.asset_pie_container);

        /** 构造饼状图假数据 **/
        List<CustomPieData> datas = new ArrayList<>();
        String[] colors = new String[]{"#ED4E39", "#FF9330", "#FFC72D", "#82DFB2", "#65B4FF", "#7193E9"};
        float[] percents = new float[]{0.2f, 0.1f, 0.1f, 0.2f, 0.2f, 0.2f};
        /** 构建小圆点假数据 **/
        String[] titles = new String[]{"养老基金", "活期理财", "定期理财", "余利宝", "齐鲁资产", "天宏理财"};
        String[] values = new String[]{"26720.82", "13360.41", "13360.41", "26720.82", "26720.82", "26720.82"};

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for(int i=0; i<6; i++) {
            CustomPieData data = new CustomPieData();
            data.setColor(colors[i]);
            data.setAngel(percents[i] * 360);
            datas.add(data);

            /** 添加对应的小圆点 **/
            View view = layoutInflater.inflate(R.layout.circle_item, null);
            CircleView circleView = (CircleView) view.findViewById(R.id.pie_item_circle);
            TextView title = (TextView) view.findViewById(R.id.pie_item_name);
            TextView value = (TextView) view.findViewById(R.id.pie_item_value);
            circleView.setColor(Color.parseColor(colors[i]));
            title.setText(titles[i]);
            value.setText(values[i]);
            //设置小圆点
            pieContainer.addView(view);
        }
        //设置饼状图数据
        pieView.setDatas(datas);
    }
}
