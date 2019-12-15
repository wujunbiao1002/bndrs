package com.bndrs.voice.common;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串通用类
 * 
 * @author dcc
 *
 */
public class StringUtil {
	/**
	 * 是否不为空
	 *
	 * @param s
	 * @return
	 */
	public static boolean isNotNullOrEmpty(String s) {
		return s != null && !"".equals(s.trim());
	}

	/**
	 * 是否为空
	 *
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String s) {
		return s == null || "".equals(s.trim());
	}

    public static List<String> splitToList(String carrier, String s) {
		List<String> list=new ArrayList<String>();
		if(isNotNullOrEmpty(carrier)){
			String[] arr = carrier.split(s);
			for(int i=0;i<arr.length;i++){
				list.add(arr[i]);
			}
		}
		return list;
    }

	/**
	 * sql like 条件
	 * @param inputString
	 * @return
	 */
	public static String getSqlLikeValue(String inputString){
		return '%'+inputString+'%';
	}
}
