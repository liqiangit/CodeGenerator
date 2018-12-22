package com.liqiangit.cg;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtil {
	public static List<Field> getFields(Class tempClass) {
		List<Field> fieldList = new ArrayList<Field>();
		while (tempClass != null) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			Field[] fields=tempClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field=fields[i];
				if(field.getName().equals("serialVersionUID")){
					continue;
				}
				fieldList.add(field);
			}
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}
		return fieldList;
	}
	public static void main(String[] args) {
		getFields(Person.class);
	}
}
