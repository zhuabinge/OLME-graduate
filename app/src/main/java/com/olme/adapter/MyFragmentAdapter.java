package com.olme.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.olme.fragment.CommunicationFragment_;
import com.olme.fragment.CourseFragment_;
import com.olme.fragment.PersonalFragment_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bingo on 2014/8/16.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list = new ArrayList<Fragment>();

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.list.add(new CourseFragment_());
        this.list.add(new CommunicationFragment_());
        this.list.add(new PersonalFragment_());
    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        return this.list.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.list.size();
    }

//    @Override
//    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//        super.setPrimaryItem(container, position, object);
//        list.get(position).
//    }
}

