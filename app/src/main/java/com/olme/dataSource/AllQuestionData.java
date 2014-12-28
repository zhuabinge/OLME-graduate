package com.olme.dataSource;

import com.olme.activity.R;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/17.
 */
public class AllQuestionData {
    public static LinkedList<HashMap<String, Object>> getDataSource() {
        LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("id","1");
        map1.put("img", R.drawable.face);
        map1.put("username", "Bingo");
        map1.put("imagecontent",R.drawable.desktop);
        map1.put("date","2014-08-04");
        map1.put("phone","Tomcat开启不了");
        map1.put("address","Tomcat开启时一直提示8080端口被占用，怎么修改端口");
        map1.put("count","2");
        HashMap<String,Object> map2 = new HashMap<String,Object>();
        map2.put("id","2");
        map2.put("img", R.drawable.glasses);
        map2.put("imagecontent",R.drawable.desktop);
        map2.put("username", "Tracy");
        map2.put("date","2014-08-04");
        map2.put("phone","win7接收不了wifi怎么办");
        map2.put("address","舍友开wifi，别人可以接，但我一连接就出现连接不了，要配置什么地方吗");
        map2.put("count","2");
        HashMap<String,Object> map3 = new HashMap<String,Object>();
        map3.put("id","3");
        map3.put("imagecontent",R.drawable.desktop);
        map3.put("img", R.drawable.neck);
        map3.put("username", "Lily");
        map3.put("date","2014-08-03");
        map3.put("phone","电脑开机时发出嘟嘟声，开启不了");
        map3.put("address","如题");
        map3.put("count","3");
        HashMap<String,Object> map4 = new HashMap<String,Object>();
        map4.put("id","4");
        map4.put("imagecontent",R.drawable.desktop);
        map4.put("img", R.drawable.desktop);
        map4.put("username", "Jack");
        map4.put("date","2014-08-01");
        map4.put("phone","struts的配置文件怎么写");
        map4.put("address","我想配置一个action来处理jsp页面的请求并进行转发，但不知道怎么配置，有没有教程或模板？");
        map4.put("count","3");
        HashMap<String,Object> map5 = new HashMap<String,Object>();
        map5.put("id","5");
        map5.put("imagecontent",R.drawable.desktop);
        map5.put("img", R.drawable.face);
        map5.put("username", "Bingo");
        map5.put("date","2014-08-01");
        map5.put("phone","android模拟器开启不了");
        map5.put("address","android的模拟器开起来很慢，根本用不了！！");
        map5.put("count","1");
        HashMap<String,Object> map6 = new HashMap<String,Object>();
        map6.put("id","6");
        map6.put("imagecontent",R.drawable.desktop);
        map6.put("img", R.drawable.glasses);
        map6.put("username", "Tracy");
        map6.put("date","2014-08-01");
        map6.put("phone","Json数据怎么解释");
        map6.put("address","刚接触Json数据格式，有没有什么资料！");
        map6.put("count","0");
        HashMap<String,Object> map7 = new HashMap<String,Object>();

        map7.put("imagecontent",R.drawable.desktop);
        map7.put("img", R.drawable.face);
        map7.put("username", "Bingo");
        map7.put("date","2014-08-01");
        map7.put("phone","Web怎么发布");
        map7.put("address","不懂啊，求大神指导！！！");
        map7.put("count","1");
        HashMap<String,Object> map8 = new HashMap<String,Object>();
        map8.put("id","4");
        map8.put("img", R.drawable.glasses);
        map8.put("username", "Tracy");
        map8.put("date","2014-08-01");
        map8.put("imagecontent",R.drawable.desktop);
        map8.put("phone","在局域网模仿QQ怎么判断好友在线情况");
        map8.put("address","就是不通过服务器来通知好友下线，但要知道好友什么时候下线怎么做！");
        map8.put("count","3");
        HashMap<String,Object> map9 = new HashMap<String,Object>();
        map9.put("id","9");
        map9.put("imagecontent",R.drawable.desktop);
        map9.put("img", R.drawable.neck);
        map9.put("username", "Lily");
        map9.put("date","2014-08-01");
        map9.put("phone","JQuery怎么使用");
        map9.put("address","求大神指导！！！");
        map9.put("count","6");
        HashMap<String,Object> map10 = new HashMap<String,Object>();
        map10.put("id","10");
        map10.put("img", R.drawable.desktop);
        map10.put("username", "Jack");
        map10.put("imagecontent",R.drawable.desktop);
        map10.put("date","2014-08-01");
        map10.put("phone","PS背景色怎么调");
        map10.put("address","我想实现渐变效果！");
        map10.put("count","1");
        HashMap<String,Object> map11 = new HashMap<String,Object>();
        map11.put("id","11");
        map11.put("img", R.drawable.face);
        map11.put("username", "Bingo");
        map11.put("imagecontent",R.drawable.desktop);
        map11.put("date","2014-08-01");
        map11.put("phone","Android怎么设置网络权限");
        map11.put("address","我的android连接服务器时提示没有网络权限，不知道怎么设置");
        map11.put("count","2");
        HashMap<String,Object> map12 = new HashMap<String,Object>();
        map12.put("id","12");
        map12.put("imagecontent",R.drawable.desktop);
        map12.put("img", R.drawable.glasses);
        map12.put("username", "Tracy");
        map12.put("date","2014-08-01");
        map12.put("phone","Android中页面怎么实现横放");
        map12.put("address","具体要设置什么参数？");
        map12.put("count","3");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
        list.add(map11);
        list.add(map12);
        return list;
    }
}