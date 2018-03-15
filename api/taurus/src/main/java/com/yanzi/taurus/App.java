package com.yanzi.taurus;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
       List<Long> list = new ArrayList<>();
       list.add(10000000000l);
       list.add(2l);
       list.add(3l);
       String string = JSON.toJSONString(list);
       System.out.println(string);
       JSONArray jsonArray = JSON.parseArray(string);
       List<Long> result = new ArrayList<>();
       for(int i=0; i<jsonArray.size(); ++i) {
           result.add(jsonArray.getLong(i));
       }
       for(Long item:result) {
           System.out.println(item);;
       }
    }
}
