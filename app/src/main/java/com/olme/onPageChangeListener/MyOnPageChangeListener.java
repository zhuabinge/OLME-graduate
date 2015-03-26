package com.olme.onPageChangeListener;

import java.util.List;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;



/**
 * 页卡滑动切换监听
 */
public class MyOnPageChangeListener implements OnPageChangeListener {
	private int currIndex = 0;// 当前页卡编号
	private List<TextView> textviewList;
	
	public MyOnPageChangeListener(List<TextView> textviewList,
			int currIndex) {
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