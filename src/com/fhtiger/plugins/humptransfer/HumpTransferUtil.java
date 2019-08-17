package com.fhtiger.plugins.humptransfer;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HumpTransferUtil
 * 驼峰命名转换
 *
 * @author LFH
 * @since 2019年03月08日 09:59
 */
public final class HumpTransferUtil {

	private static final Pattern UnderLineRegex = Pattern.compile("([A-Za-z\\d]+)(_)?");
	;
	private static final Pattern HumpRegex = Pattern.compile("[A-Z]([a-z\\d]+)?");
	private static final String UnderLine = "_";
	private static final String Empty = "";

	/**
	 * @param underLineStr 下划线文本
	 * @return 驼峰字符串
	 */
	public static String transfer2hump(String underLineStr) {
		return transfer2hump(underLineStr, true);
	}

	/**
	 * &gt;修复非指定格式文本被置空的问题: 2019/8/17 14:10
	 * @param underLineStr 下划线字符串
	 * @param smallCamel   首字母大/小写驼峰
	 * @return 驼峰字符串
	 */
	public static String transfer2hump(String underLineStr, boolean smallCamel) {
		Objects.requireNonNull(underLineStr, "字符串参数不能为空!");
		StringBuilder sb = new StringBuilder();
		String result=underLineStr;
		Matcher matcher = UnderLineRegex.matcher(underLineStr);
		int start=0;
		//匹配正则表达式
		while (matcher.find()) {
			String word = matcher.group();
			if(word.length()==underLineStr.length()){
				return underLineStr;
			}
			//当是true 或者是空的情况
			if (smallCamel && start<1) {
				sb.append(Character.toLowerCase(word.charAt(0)));
			} else {
				sb.append(Character.toUpperCase(word.charAt(0)));
			}

			int index = word.lastIndexOf(UnderLine);
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
			result = result.replace(word,sb.toString());
			sb.delete(0,sb.length());
			start++;
		}
		return result;
	}

	/**
	 * &gt;修复非指定格式文本被置空的问题: 2019/8/17 14:10
	 * @param humpStr 驼峰字符串
	 * @param uppercase 分段首字母大小写
	 * @return 下划线字符串
	 */
	public static String transfer2underline(String humpStr,boolean uppercase) {
		Objects.requireNonNull(humpStr, "字符串参数不能为空!");
		if(humpStr.length()<1){
			return humpStr;
		}
		char first =humpStr.charAt(0);
		humpStr = String.valueOf(first).toUpperCase().concat(humpStr.substring(1));
		String result=humpStr;
		int humpLength = humpStr.length();
		StringBuilder sb = new StringBuilder();
		Matcher matcher = HumpRegex.matcher(humpStr);
		int replaced=0;
		while (matcher.find()) {
			String word = matcher.group();
			if(word.length()==humpLength){
				return humpStr;
			}
			char ft= word.charAt(0);
			sb.append(uppercase? Character.toUpperCase(ft):Character.toLowerCase(ft));
			sb.append(word.substring(1));
			sb.append(matcher.hitEnd() ?Empty : UnderLine);
			result = result.replace(word,sb.toString());
			sb.delete(0,sb.length());
			replaced++;
		}
		if(replaced<1){
			result = first+result.substring(1);
		}
		return result.replaceFirst("_([^_]+)$","$1");
	}

	/**
	 * @param humpStr 驼峰字符串
	 * @return 下划线字符串
	 */
	public static String transfer2underline(String humpStr) {
		return transfer2underline(humpStr,false);
	}

}
