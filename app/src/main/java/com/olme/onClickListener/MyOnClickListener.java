package com.olme.onClickListener;

import java.util.List;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

/**
 * 监听app底部菜单的点击事件
 */

public class MyOnClickListener implements View.OnClickListener {
	private int index = 0;
	private ViewPager viewpager;
	private List<TextView> textviewList;
	
	public MyOnClickListener(ViewPager viewpager, List<TextView> textviewList, int i) {
		index = i;
		this.viewpager = viewpager;
		this.textviewList = textviewList;
	}

	@Override
	public void onClick(View v) {
		textviewList.get(viewpager.getCurrentItem()).setTextColor(android.graphics.Color.parseColor("#FFFFFF"));
		textviewList.get(index).setTextColor(android.graphics.Color.parseColor("#55c8d6"));
		viewpager.setCurrentItem(index);
	}
};
