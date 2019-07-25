import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonUtils
 * @Description
 * @Author tangguo
 * @Date 2019/5/4 22:31
 * @Version 1.0
 **/
public class JsonUtils {
    public static void main(String[] args) {
//        String str = "{\"numId\":[{\"niceInfo\":\"\",\"proKey\":\"AAAA\",\"serialNumber\":\"11111\"}]}";
        String str = "{\"numId\":[{\"niceInfo\":\"\",\"proKey\":\"AAAA\",\"serialNumber\":\"1111\"}],\"test\":[{\"aaa\":\"\"},{\"bb\":\"\"}]}";
        try{
            Object object = filterNone(str);
            JSONObject jsonInfo = (JSONObject) JSON.toJSON(object);
            System.out.println(jsonInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 过滤值为[] "" null的报文节点
    public static Object filterNone(String jsonStr) throws Exception{
        if(jsonStr==null||jsonStr.equals("")||jsonStr.equals("null"))
            return null;
        if(jsonStr.indexOf("[")==0){
            List<Object> lists = new ArrayList<Object>();
            List<HashMap> list = JSON.parseArray(jsonStr, HashMap.class);
            for(int i=0;i<list.size();i++){
                Map<String,Object> map = list.get(i);
                Map<String,Object> maps = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entrys : map.entrySet()) {
                    Object objs = filterNone(entrys.getValue().toString());
                    if(objs!=null){
                        maps.put(entrys.getKey(),objs);
                    }
                }
                lists.add(maps);
            }
            if(lists.size()==0)
                return null;
            return lists;
        }
        if(jsonStr.indexOf("{")==0){
            Map<String, Object> targetMap = new HashMap<String, Object>();
            LinkedHashMap<String, Object> jsonMap = JSON.parseObject(jsonStr, new TypeReference<LinkedHashMap<String, Object>>(){});
            for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
                Object obj = filterNone(entry.getValue().toString());
                if(obj==null)
                    continue;
                targetMap.put(entry.getKey(),obj);
            }
            if(targetMap.isEmpty())
                return null;
            return targetMap;
        }
        return jsonStr;
    }
    /**
     * @return com.alibaba.fastjson.JSONArray
     * @Author tangguo
     * @Description json根据字母a-z排序 只限于无节点并且无数组的情况
     * @Date 17:26 2019/6/4
     * @Param [jsonObj]
     **/
    private static JSONArray sort(JSONObject jsonObj) {
        JSONArray arr = new JSONArray();
        for (Map.Entry<String, Object> map : jsonObj.entrySet()) {
            JSONObject bb = new JSONObject();
            bb.put(map.getKey(), map.getValue());
            arr.add(bb);
        }
        System.out.println(arr);
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                String aaa = arr.getJSONObject(j).keySet().iterator().next();
                String bbb = arr.getJSONObject(j + 1).keySet().iterator().next();
                int strLength = 0;
                if (aaa.length() > bbb.length()) {
                    strLength = bbb.length();
                } else {
                    strLength = aaa.length();
                }
                for (int k = 0; k < strLength; k++) {
                    char aaaa = aaa.charAt(k);
                    char bbbb = bbb.charAt(k);
                    if (aaaa == bbbb) {
                        continue;
                    }
                    if (aaaa > bbbb) {
                        JSONObject ccc = (JSONObject) arr.getJSONObject(j).clone();
                        arr.getJSONObject(j).clear();
                        arr.getJSONObject(j).put(arr.getJSONObject(j + 1).keySet().iterator().next(), arr.getJSONObject(j + 1).getString(arr.getJSONObject(j + 1).keySet().iterator().next()));
                        arr.getJSONObject(j + 1).clear();
                        arr.getJSONObject(j + 1).put(ccc.keySet().iterator().next(), ccc.getString(ccc.keySet().iterator().next()));
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
        System.out.println(arr);
        return arr;
    }
}
