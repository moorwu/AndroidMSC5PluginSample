package com.dheaven.exmaple.msc5plugin;


import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AbsoluteLayout;
import io.dcloud.DHInterface.AbsMgr;
import io.dcloud.DHInterface.ICore;
import io.dcloud.DHInterface.IFeature;
import io.dcloud.DHInterface.ICore.ICoreStatusListener;
import io.dcloud.DHInterface.IWebview;
import io.dcloud.util.JSUtil;


public class UIPluginSample  implements IFeature{
    @Override
    public void dispose(String arg0) {
        // TODO Auto-generated method stub
         
    }
    @Override
    public String execute(IWebview pWebview, String func_name, String[] args) {
         
        Context context = pWebview.getContext();
     
         
        //getString是一个同步的请求
        if("drawPie".equals(func_name)){
            View mPieView = getPieView(context); 
             
            if(args.length>0){
                 
                JSONTokener jsonParser = new JSONTokener(args[0]); 
                JSONObject param=null;
                try {
                     
                     
                    param = (JSONObject) jsonParser.nextValue();
                     
                    int x=(int) (param.getInt("x")*pWebview.obtainWebview().getScale());
                    int y=(int) (param.getInt("y")*pWebview.obtainWebview().getScale());
                    int width=(int) (param.getInt("width")*pWebview.obtainWebview().getScale());
                    int height=(int) (param.getInt("height")*pWebview.obtainWebview().getScale());
                     
                    Log.i("NATIVE PLUGIN", "Got scale: "+ pWebview.obtainWebview().getScale());
                    Log.i("NATIVE PLUGIN", "Got x:  "+ x + " : "+param.getInt("x"));
                    Log.i("NATIVE PLUGIN", "Got y: "+ y + " : "+param.getInt("y"));
                    Log.i("NATIVE PLUGIN", "Got width: "+ width + " : "+param.getInt("width")); 
                    Log.i("NATIVE PLUGIN", "Got height: "+ height + " : "+param.getInt("height"));
                     
                    pWebview.obtainWebview().addView(mPieView, new AbsoluteLayout.LayoutParams(width,height, x, y ));
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }  
                 
                 
            }
            else{
                Log.i("NATIVE PLUGIN", "could not got param");
                pWebview.obtainWebview().addView(mPieView);
            }
             
        }
        return null;
         
    }
    @Override
    public void init(AbsMgr arg0, String arg1) {
        // TODO Auto-generated method stub
         
    }
     
    /**
    * 构造饼图数据
    * 当然数据原则上应该从参数中传入，本例就偷懒了
    */
    private CategorySeries getDataSet() {
        // 构造数据
        CategorySeries pieSeries = new CategorySeries("&#25163;&#26426;&#24320;&#21457;&#32773;&#27604;&#20363;");
        pieSeries.add("Android", 28);
        pieSeries.add("IOS", 46);
        pieSeries.add("&#20854;&#20182;", 26);
        return pieSeries;
    }
     
    /**
    * 获取一个饼图渲染器
    */
    private DefaultRenderer getPieRenderer() {
        // 构造一个渲染器
        DefaultRenderer renderer = new DefaultRenderer();
         
         
        // 设置渲染器显示缩放按钮
        renderer.setZoomButtonsVisible(false);
        // 设置渲染器允许放大缩小
        renderer.setZoomEnabled(false);
        // 设置渲染器标题文字大小
        renderer.setChartTitleTextSize(20);
        // 给渲染器增加3种颜色
        SimpleSeriesRenderer yellowRenderer = new SimpleSeriesRenderer();
        yellowRenderer.setColor(Color.YELLOW);
        SimpleSeriesRenderer blueRenderer = new SimpleSeriesRenderer();
        blueRenderer.setColor(Color.BLUE);
        SimpleSeriesRenderer redRenderer = new SimpleSeriesRenderer();
        redRenderer.setColor(Color.RED);
        renderer.addSeriesRenderer(yellowRenderer);
        renderer.addSeriesRenderer(blueRenderer);
        renderer.addSeriesRenderer(redRenderer);
        // 设置饼图文字字体大小和饼图标签字体大小
        renderer.setLabelsTextSize(15);     
        renderer.setLegendTextSize(15);
        // 消除锯齿
        renderer.setAntialiasing(true);
        // 设置背景颜色
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.WHITE);
        // 设置线条颜色
        renderer.setAxesColor(Color.BLACK);
     
        return renderer;
    }
     
     
    private GraphicalView getPieView(Context context) {
        return ChartFactory.getPieChartView(context, getDataSet(), getPieRenderer());
    }
     
}