package com.olme.tool;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

/**
 * Created by root on 15-3-9.
 */
@EBean
public class MyErrorHandler implements RestErrorHandler {

    @RootContext
    Context context;

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        // TODO Auto-generated method stub
        e.printStackTrace();
        //Log.d("REST", e.toString());
        showError();
    }

    @UiThread
    void showError(){
        Toast toast = Toast.makeText(context, "连接服务器失败！", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
