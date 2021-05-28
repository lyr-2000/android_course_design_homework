package io.github.lyr2000.novel_app.common.html;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

/**
 * @Author lyr
 * @create 2021/4/15 13:42
 */
public class MapDfsUtil {
    // StringBuilder sb = new StringBuilder();
    // static class Visitor {
    //
    // }
    //中序遍历
    public static boolean isTag(String key) {
        if ("form".equals(key)) {

        } else if ("div".equals(key)) {

        } else if ("label".equals(key)) {

        } else if ("input".equals(key)) {

        } else {
            return false;
        }
        return true;
    }

    public static String change(String x) {
        int idx = x.indexOf("<");
        if (idx >= 0 && idx < x.length()) {
            return x.substring(0, idx) + ">" + x.substring(idx);
        }
        return x;
    }

    public static String dfs(Map<String, Object> map, Class clazz) {
        final Set<Map.Entry<String, Object>> set = map.entrySet();
        String html = "";
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (isTag(key)) {
                html = html + "<" + key + "";
                String xx = dfs((Map<String, Object>) value, clazz);
                html += " " + change(xx);
                // html += ">";
                if("input".equals(key) == false) {
                    if (((Map<?, ?>) value).get("content")!=null) {
                        html += ((Map<?, ?>) value).get("content");
                    }

                    html += "</" + key + ">";
                }

            } else if ("attr".equals(key)) {
                //遍历，并且加上具体属性
                Map<String, Object> attrs = (Map<String, Object>) value;
                for (Map.Entry attrss : attrs.entrySet()) {
                    String xxx = (String) attrss.getValue();
                    // if ("${className}".equals(xxx)) {
                    //     xxx = clazz.getSimpleName();
                    // }
                    xxx = xxx.replace("${className}", clazz.getSimpleName());
                    html += " " + attrss.getKey() + "= '" + xxx + "' ";
                }
                //属性加完了

            } else if ("$forEach".equals(key)) {
                Field[] fs = clazz.getDeclaredFields();
                // html += " >";
                // for (Field f: fs) {
                //     html += dfsField((Map) value,f);
                // }
                for (Field f : fs) {
                    if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
                        continue;
                    }
                    html += dfsField((Map) value, f);
                }
                html += "";
            }
        }

        return html;
    }

    public static String dfsField(Map map, Field f) {
        final Set<Map.Entry<String, Object>> set = map.entrySet();
        String html = "";
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (isTag(key)) {
                html = html + "<" + key;
                String xx = change(dfsField((Map<String, Object>) value, f));
                html += " " + xx;
                if (xx.contains(">") == false) {
                    html += " > ";
                }
                if (key.equals("input") ==false) {
                    if (((Map<?, ?>) value).get("content")!=null) {
                        String content = (String) ((Map<?, ?>) value).get("content");
                        html += content.replace("${fieldName}",f.getName());
                    }



                    html += "" + "</" + key + ">";
                }

            } else if ("attr".equals(key)) {
                //遍历，并且加上具体属性
                Map<String, Object> attrs = (Map<String, Object>) value;
                for (Map.Entry attrss : attrs.entrySet()) {
                    String xxx = (String) attrss.getValue();
                    // if ("${className}".equals(xxx)) {
                    //     xxx = clazz.getSimpleName();
                    // }
                    xxx = xxx.replace("${fieldName}", f.getName());
                    html += " " + attrss.getKey() + "= '" + xxx + "' ";
                }
            }
        }
        return html;
    }

    public static String getHtml(Map map, Class clazz)  {
       return MapDfsUtil.dfs(map,  clazz);
    }

    // public static void main(String[] args) {
    //     File file = new File("D:\\ASUS\\Desktop\\my_demo\\server\\小说app后端\\src\\main\\resources\\html.yml");
    //     Yaml yaml = new Yaml();
    //     try (
    //             FileInputStream in = new FileInputStream(file);
    //     ) {
    //         final Map load = yaml.load(in);
    //         // System.out.println(load);
    //         System.out.println(dfs(load, BookInfo.class));
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    // }
}
