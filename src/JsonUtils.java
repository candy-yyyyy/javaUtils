import com.alibaba.fastjson.JSON;
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
    // 过滤值为[] "" null的报文节点
    public static Object filterNone(String jsonStr) throws Exception{
        if(jsonStr==null||jsonStr.equals("")||jsonStr.equals("null"))
            return null;
        if(jsonStr.indexOf("[")==0){
            List<Object> lists = new ArrayList<Object>();
            List<HashMap> list = JSON.parseArray(jsonStr, HashMap.class);
            for(int i=0;i<list.size();i++){
                Map<String,Object> map = list.get(i);
                for (Map.Entry<String, Object> entrys : map.entrySet()) {
                    Map<String,Object> maps = new HashMap<String, Object>();
                    Object objs = filterNone(entrys.getValue().toString());
                    if(objs!=null){
                        maps.put(entrys.getKey(),objs);
                    }
                }
                lists.add(map);
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
}
