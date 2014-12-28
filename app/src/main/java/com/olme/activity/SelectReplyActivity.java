package com.olme.activity;

import android.app.Activity;
import android.content.Intent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Bingo on 2014/8/18.
 *
 * 选择回复评论或者查看消息的主题
 */
@EActivity(R.layout.activity_selectreply)
public class SelectReplyActivity extends Activity {

    @Click(R.id.replyMessage)
    void replyMessageIsClicked(){
        Intent intent = new Intent(this,ReplyActivity_.class);
        startActivity(intent);
        this.finish();
    }

    @Click(R.id.checkTheme)
    void checkThemeIsClicked(){
        Intent intent = new Intent(this,MyQuestionActivity_.class);
        intent.putExtra("headTitle","");
        startActivity(intent);
        this.finish();
    }
}
