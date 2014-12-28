package com.olme.dataSource;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Bingo on 2014/8/17.
 */
public class AnswerData {
    public static LinkedList<HashMap<String, Object>> getDataSource() {
        LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", "1");
        map1.put("name", "A");
        map1.put("vedioLength", "Bingo");
        map1.put("rating", "5");
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", "2");
        map2.put("name", "B");
        map2.put("vedioLength", "Tracy");
        map2.put("rating", "0");
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("id", "3");
        map3.put("name", "C");
        map3.put("vedioLength", "Lily");
        map3.put("rating", "5");
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("id", "4");
        map4.put("name", "D");
        map4.put("vedioLength", "Jack");
        map4.put("rating", "5");
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("id", "5");
        map5.put("name", "E");
        map5.put("vedioLength", "Bingo");
        map5.put("rating", "0");
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("id", "6");
        map6.put("name", "F");
        map6.put("vedioLength", "Tracy");
        map6.put("rating", "0");
        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("id", "7");
        map7.put("name", "G");
        map7.put("vedioLength", "Bingo");
        map7.put("rating", "5");
        HashMap<String, Object> map8 = new HashMap<String, Object>();
        map8.put("id", "8");
        map8.put("name", "H");
        map8.put("vedioLength", "Tracy");
        map8.put("rating", "0");
        HashMap<String, Object> map9 = new HashMap<String, Object>();
        map9.put("id", "9");
        map9.put("name", "I");
        map9.put("vedioLength", "Lily");
        map9.put("rating", "5");
        HashMap<String, Object> map10 = new HashMap<String, Object>();
        map10.put("id", "10");
        map10.put("name", "J");
        map10.put("vedioLength", "Jack");
        map10.put("rating", "0");
        HashMap<String, Object> map11 = new HashMap<String, Object>();
        map11.put("id", "11");
        map11.put("name", "K");
        map11.put("vedioLength", "Bingo");
        map11.put("rating", "5");
        HashMap<String, Object> map12 = new HashMap<String, Object>();
        map12.put("id", "12");
        map12.put("name", "L");
        map12.put("vedioLength", "Tracy");
        map12.put("rating", "0");
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
    public static LinkedList<HashMap<String, Object>> getAnswerDataSource(int position) {
        LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
        if(position==1){
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Tracy");
            map1.put("rating", "看看8080端口被哪个占用了，关掉。");
            HashMap<String, Object> map3 = new HashMap<String, Object>();
            map3.put("id", "3");
            map3.put("name", "2014-8-09");
            map3.put("vedioLength", "Lily");
            map3.put("rating", "端口被占用，最好就是修改配置文件中端口的值，修改tomcat目录下/conf/server.xml,把里面所有的8080改为其他端口如8086，保存，重启Tomcat");
            list.add(map1);
            list.add(map3);
            return list;
        }else if(position==3){
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-08-04");
            map1.put("vedioLength", "Tracy");
            map1.put("rating", "内存条问题");
            HashMap<String, Object> map3 = new HashMap<String, Object>();
            map3.put("id", "3");
            map3.put("name", "2014-08-04");
            map3.put("vedioLength", "Lily");
            map3.put("rating", "应该是内存条松了，拿出来用橡皮擦擦一下，一般这杨可以解决大部分情况");
            HashMap<String, Object> map4 = new HashMap<String, Object>();
            map4.put("id", "4");
            map4.put("name", "2014-08-05");
            map4.put("vedioLength", "Jack");
            map4.put("rating", "同上！！！除非内存条坏了！！");
            list.add(map1);
            list.add(map3);
            list.add(map4);
            return list;
        }
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", "1");
        map1.put("name", "A");
        map1.put("vedioLength", "Bingo");
        map1.put("rating", "5");
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", "2");
        map2.put("name", "B");
        map2.put("vedioLength", "Tracy");
        map2.put("rating", "0");
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("id", "3");
        map3.put("name", "C");
        map3.put("vedioLength", "Lily");
        map3.put("rating", "5");
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("id", "4");
        map4.put("name", "D");
        map4.put("vedioLength", "Jack");
        map4.put("rating", "5");
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("id", "5");
        map5.put("name", "E");
        map5.put("vedioLength", "Bingo");
        map5.put("rating", "0");
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("id", "6");
        map6.put("name", "F");
        map6.put("vedioLength", "Tracy");
        map6.put("rating", "0");
        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("id", "7");
        map7.put("name", "G");
        map7.put("vedioLength", "Bingo");
        map7.put("rating", "5");
        HashMap<String, Object> map8 = new HashMap<String, Object>();
        map8.put("id", "8");
        map8.put("name", "H");
        map8.put("vedioLength", "Tracy");
        map8.put("rating", "0");
        HashMap<String, Object> map9 = new HashMap<String, Object>();
        map9.put("id", "9");
        map9.put("name", "I");
        map9.put("vedioLength", "Lily");
        map9.put("rating", "5");
        HashMap<String, Object> map10 = new HashMap<String, Object>();
        map10.put("id", "10");
        map10.put("name", "J");
        map10.put("vedioLength", "Jack");
        map10.put("rating", "0");
        HashMap<String, Object> map11 = new HashMap<String, Object>();
        map11.put("id", "11");
        map11.put("name", "K");
        map11.put("vedioLength", "Bingo");
        map11.put("rating", "5");
        HashMap<String, Object> map12 = new HashMap<String, Object>();
        map12.put("id", "12");
        map12.put("name", "L");
        map12.put("vedioLength", "Tracy");
        map12.put("rating", "0");
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
    public static LinkedList<HashMap<String, Object>> getMyMessageDataSource(int position) {
        LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
        if(position==1){
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Tracy");
            map1.put("rating", "网上这类模板有很多，可以搜搜！");
            list.add(map1);
             map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Bingo");
            map1.put("rating", "给我你得QQ，我这里有模板");
            list.add(map1);
             map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Liner");
            map1.put("rating", "读了那么多年书还不知道怎么写！");
            list.add(map1);
            return list;
        }
        else if(position==2){
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Tracy");
            map1.put("rating", "设置属性，同名不指向同一个属性！");
            list.add(map1);
            map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Bingo");
            map1.put("rating", "修改属性！");
            list.add(map1);
            return  list;
        }
        else{
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Tracy");
            map1.put("rating", "同求！");
            list.add(map1);
            return list;
        }

    }


        public static LinkedList<HashMap<String, Object>> getMyAllDataSource() {
            LinkedList<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Tracy");
            map1.put("rating", "网上这类模板有很多，可以搜搜！");
            list.add(map1);
            map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Bingo");
            map1.put("rating", "给我你得QQ，我这里有模板");
            list.add(map1);
            map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Liner");
            map1.put("rating", "读了那么多年书还不知道怎么写！");
            list.add(map1);
            map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Tracy");
            map1.put("rating", "设置属性，同名不指向同一个属性！");
            list.add(map1);
            map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Bingo");
            map1.put("rating", "修改属性！");
            list.add(map1);
            map1 = new HashMap<String, Object>();
            map1.put("id", "1");
            map1.put("name", "2014-8-08");
            map1.put("vedioLength", "Tracy");
            map1.put("rating", "同求！");
            list.add(map1);
            return list;
        }


}
