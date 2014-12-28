package com.olme.onPageChangeListener;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.olme.popupWindow.LoginTipPopWindow;
import com.olme.activity.R;
import com.olme.application.CustomApplication;

/**
 * 页卡滑动切换监听
 */
public class MyOnPageChangeListener implements OnPageChangeListener {
	private int currIndex = 0;// 当前页卡编号
	private List<TextView> textviewList;
    private CustomApplication app;
    private List<Fragment> list;
	
	public MyOnPageChangeListener(List<Fragment> list,List<TextView> textviewList,
			int currIndex) {
        this.list = list;
		this.textviewList = textviewList;
		this.currIndex = currIndex;
	}
	
	@Override
	public void onPageSelected(int arg0) {
		textviewList.get(currIndex).setTextColor(android.graphics.Color.parseColor("#FFFFFF"));
		textviewList.get(arg0).setTextColor(android.graphics.Color.parseColor("#55c8d6"));
		currIndex = arg0;
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}


}