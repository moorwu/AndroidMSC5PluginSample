package com.dheaven.exmaple.msc5plugin;

import io.dcloud.EntryProxy;
import io.dcloud.DHInterface.ICore;
import io.dcloud.DHInterface.ICore.ICoreStatusListener;
import io.dcloud.DHInterface.ISysEventListener.SysEventType;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity implements ICoreStatusListener {

	EntryProxy mEntryProxy = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(mEntryProxy == null){
        	mEntryProxy = new EntryProxy(this,this);
        	mEntryProxy.onCreate(savedInstanceState);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mEntryProxy.onExecute(SysEventType.OnCreateOptionMenu, menu);
    }
	@Override
	public void onPause() {
		super.onPause();
		mEntryProxy.onPause();
	}
	@Override
	public void onResume() {
		super.onResume();
		mEntryProxy.onResume();
	}
	@Override
	public void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		//非点击icon调用activity时才调用newintent事件
		if(intent.getFlags() != 0x10600000){
			mEntryProxy.onNewIntent(intent);
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mEntryProxy.onStop();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean _ret = mEntryProxy.onExecute(SysEventType.OnKeyDown, new Object[]{keyCode,event});
		return _ret ? _ret : super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean _ret = mEntryProxy.onExecute(SysEventType.OnKeyUp, new Object[]{keyCode,event});
		return _ret ? _ret : super.onKeyUp(keyCode, event);
	}
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		boolean _ret = mEntryProxy.onExecute(SysEventType.onKeyLongPress, new Object[]{keyCode,event});
		return _ret ? _ret : super.onKeyLongPress(keyCode, event);
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		try {
			int temp = this.getResources().getConfiguration().orientation;
			if (mEntryProxy != null) {
				mEntryProxy.onConfigurationChanged(temp);
			}
			super.onConfigurationChanged(newConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mEntryProxy.onExecute(SysEventType.OnActivityResult, new Object[]{requestCode,resultCode,data});
	}

	@Override
	public void onCoreInitEnd(ICore arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCoreReady(ICore arg0) {
		// TODO Auto-generated method stub
		
	}
}
