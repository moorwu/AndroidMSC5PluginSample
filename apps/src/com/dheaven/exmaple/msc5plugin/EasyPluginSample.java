package com.dheaven.exmaple.msc5plugin;
 
 
import android.app.AlertDialog;
 
import android.content.Context;
 
import android.content.DialogInterface;
 
import io.dcloud.DHInterface.AbsMgr;
 
import io.dcloud.DHInterface.IFeature;
 
import io.dcloud.DHInterface.IWebview;
 
import io.dcloud.adapter.util.AndroidResources;
 
import io.dcloud.util.JSUtil;
 
 
 
 
/**
 
 * 同步代码调用样例
 
 * 用法：通过MSC5.0的HTML页面中的javascript同步地调用本方法
 
 * @author moor
 
 *
 
 */
 
public class EasyPluginSample implements IFeature{
 
    /**
 
     * 用于垃圾对象的回收
 
     */
 
    @Override
 
    public void dispose(String arg0) {
 
        // TODO Auto-generated method stub
 
         
 
    }
 
 
    /**
 
     * 主要实现代码，对应javascipt的调用
 
     */
 
    @Override
 
    public String execute(final IWebview pWebView, final String func_name, final String[] pArgs) {
        //getString是一个同步的请求
    	 
        if("getString".equals(func_name)){
   
         //仅仅只是单纯的返回一个字符串
   
         return JSUtil.wrapJsVar("This is a sample Sync plugin. Just say Hello from NATIVE plugin code! ",true);
   
         }
         return null;
    }
 
 
    /**
 
     * 用于初始化处理
 
     */
 
    @Override
 
    public void init(AbsMgr arg0, String arg1) {
 
    }
}
