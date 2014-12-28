package com.olme.tool;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Bingo on 2014/8/19.
 */
public class HttpUtil {

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static InputStream getInputStrem(String streamStr )throws IOException{
        InputStream inputStream = null;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        URL url = new URL(streamStr);
        if(url!=null){
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);

            int response_code = httpURLConnection.getResponseCode();
            if(response_code==200){
                inputStream = httpURLConnection.getInputStream();
            }
        }
        return inputStream;
    }

    public static byte[] getByteArray(String streamStr){
        byte data[] = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            URL url = new URL(streamStr);
            if(url!=null){
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                int response_code = httpURLConnection.getResponseCode();
                int len=0;
                byte[] b_data = new byte[1024];
                if(response_code==200){
                    inputStream = httpURLConnection.getInputStream();
                    while((len=inputStream.read(b_data))!=-1){
                        outputStream.write(b_data, 0, len);
                    }
                    data = outputStream.toByteArray();
                }
            }
        }catch(Exception ex){

        }
        return data;

    }
}
