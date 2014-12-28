package com.olme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.olme.activity.MyQuestionActivity_;
import com.olme.activity.PersonalInfoActivity_;
import com.olme.activity.R;
import com.olme.application.CustomApplication;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Bingo on 2014/8/4.
 * app底部菜单的个人中心
 */
@EFragment(R.layout.tab3)
public class PersonalFragment extends Fragment {
    private CustomApplication app;
    private Toast toast;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        System.out.println("————————> 初始化tretrt");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.tab3, null);

        System.out.println("————————> 初始化");


        LinearLayout mytheme = (LinearLayout)view.findViewById(R.id.myquestion);
        LinearLayout personalInfo = (LinearLayout)view.findViewById(R.id.personalInfo);


        mytheme.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

             Intent intent = new Intent(getActivity(), MyQuestionActivity_.class);
             intent.putExtra("headTitle","我的提问");
             startActivity(intent);
           }
       });

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent = new Intent(getActivity(), PersonalInfoActivity_.class);
              startActivity(intent);

            }
        });

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
