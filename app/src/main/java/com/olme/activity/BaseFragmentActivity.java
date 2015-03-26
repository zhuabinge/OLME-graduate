package com.olme.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by root on 15-3-17.
 */
public class BaseFragmentActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * Shows fragment within a given view.
     * Creates new fragment if it has not being created before.
     *
     * @param viewId view where to place the fragment
     * @param tag    fragment tag to created it via reflexion
     */
    public Fragment showCreateFragment(int viewId, String tag, Bundle args) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (null == fragment)
            fragment = createFragment(tag);
        if (args != null){
            if(fragment.getArguments() == null) fragment.setArguments(args);
            else{
                fragment.getArguments().clear();
                fragment.getArguments().putAll(args);
            }
        }
        //replace fragment and add to back stack
        getSupportFragmentManager().beginTransaction().replace(viewId, fragment, tag).addToBackStack(null).commit();

        return fragment;
    }


    public Fragment createFragment(String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (null == fragment) {
            try {
                fragment = (Fragment) Class.forName(tag).newInstance();
            } catch (InstantiationException e) {

            } catch (IllegalAccessException e) {

            } catch (ClassNotFoundException e) {

            }
        }
        return fragment;
    }

}
