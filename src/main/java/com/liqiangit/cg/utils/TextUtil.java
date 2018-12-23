/**
 *
 */
package com.liqiangit.cg.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理文本
 *
 * @author 李强
 */
public class TextUtil {
  private static final Logger logger = LoggerFactory.getLogger(TextUtil.class);

  /**
   * 驼峰转下划线
   *
   * @Description
   * @author 李强
   * @param str
   * @return
   */
  public static String convertCamelCaseToUnderscore(String str) {

    String[] strings = StringUtils.splitByCharacterTypeCamelCase(str);

    String _str = StringUtils.join(strings, "_");

    _str = StringUtils.lowerCase(_str);

    return _str;
  }

  /**
   * 下划线转驼峰
   *
   * @Description
   * @author 李强
   * @param str
   * @return
   */
  public static String convertUnderscoreToCamelCase(String str) {
    if (str == null) {
      return null;
    }
    String[] strings = str.split("_");
    StringBuilder sb = new StringBuilder();
    for (String _str : strings) {
      sb.append(StringUtils.upperCase(_str.substring(0, 1)) + _str.substring(1));
    }

    return sb.toString();
  }

  /**
   * firstLowerCase:首字母小写. <br/>
   *
   * @author:李强 Date: 2016年5月19日 上午11:12:05
   * @param fldName
   * @return
   * @since JDK 1.7
   */
  public static String firstLowerCase(String fldName) {
    String first = fldName.substring(0, 1).toLowerCase();
    String rest = fldName.substring(1, fldName.length());
    String newStr = new StringBuffer(first).append(rest).toString();
    return newStr;
  }
  // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
  public static Map<String, Object> transBean2Map(Object obj) {

      if(obj == null){
          return null;
      }        
      Map<String, Object> map = new HashMap<String, Object>();
      try {
          BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
          PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
          for (PropertyDescriptor property : propertyDescriptors) {
              String key = property.getName();

              // 过滤class属性
              if (!key.equals("class")) {
                  // 得到property对应的getter方法
                  Method getter = property.getReadMethod();
                  Object value = getter.invoke(obj);

                  map.put(key, value);
              }

          }
      } catch (Exception e) {
          System.out.println("transBean2Map Error " + e);
      }

      return map;

  }
  /**
   * velocityMerge:传入模板，返回字符串. <br/>
   *
   * @author:李强 Date: 2016年5月17日 下午2:20:22
   * @param templePath 模板路径
   * @param map 参数
   * @return
   * @since JDK 1.7
   */
  public static String velocityMerge(String templePath, Map<String, Object> map) {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(Velocity.RESOURCE_LOADER, "class");
    ve.setProperty("class.resource.loader.class",
        "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    ve.setProperty("input.encoding", "utf-8");
    ve.setProperty("output.encoding", "utf-8");
    try {
      ve.init();
      Template t = ve.getTemplate(templePath);
      VelocityContext context = new VelocityContext();

      for (Map.Entry<String, Object> entry : map.entrySet()) {
        context.put(entry.getKey(), entry.getValue());
      }

      StringWriter writer = new StringWriter();
      t.merge(context, writer);
      writer.flush();
      writer.close();

      return writer.toString();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }

    return null;
  }
}
