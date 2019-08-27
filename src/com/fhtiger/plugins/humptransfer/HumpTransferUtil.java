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

	private static final Pattern HumpRegex = Pattern.compile("[A-Z]([a-z\\d]+)?");

	private static final Pattern FirstCharPattern = Pattern.compile("[a-zA-Z]");

	private static final String UnderLine = "_";
	private static final String Empty = "";

	private static final String END_CHECK="_([^a-zA-Z\\d])";

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
		StringBuilder sb = new StringBuilder();//中间字符串构造
		StringBuilder resultBuilder = new StringBuilder();//结果字符串构造
		String result=underLineStr;
		Matcher matcher = UnderLineRegex.matcher(underLineStr);
		int start=0,wordIndex;
		//匹配正则表达式
		while (matcher.find()) {
			String word = matcher.group();
			int wl = word.length();
			if(wl==underLineStr.length()){
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
			wordIndex = result.indexOf(word);
			//将result中匹配字符串前面部分和当前部分处理后的结果存入结果字符串构造中
			resultBuilder.append(result, 0, wordIndex).append(sb);
			//将result中已经存入resultBuilder中的部分移除.
			result = result.substring(wordIndex+wl);
			//清空中间字符串构造
			sb.delete(0,sb.length());
			start++;
		}
		if(start>0){
			result = resultBuilder.append(result).toString();
		}
		return result;
	}

	/**
	 * &gt;修复非指定格式文本被置空的问题: 2019/8/17 14:10
	 * &gt;修复转换下划线失败问题: 2019/8/20 18:45
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
		Matcher firstMatcher= FirstCharPattern.matcher(humpStr);
		if (firstMatcher.find()){
			String word = firstMatcher.group();
			first = word.charAt(0);
			humpStr= firstMatcher.replaceFirst(word.toUpperCase());
		}
		String result=humpStr;//先暂存原字符串
		StringBuilder resultBuilder = new StringBuilder();//结果字符串构造
		int humpLength = humpStr.length();
		StringBuilder sb = new StringBuilder();//中间处理字符串构造
		Matcher matcher = HumpRegex.matcher(humpStr);
		int replaced=0;
		while (matcher.find()) {
			String word = matcher.group();
			int wl = word.length();
			if(wl==humpLength){
				return humpStr;
			}
			char ft= word.charAt(0);
			sb.append(uppercase? Character.toUpperCase(ft):Character.toLowerCase(ft));
			sb.append(word.substring(1));
			sb.append(matcher.hitEnd() ?Empty : UnderLine);
			int wordIndex = result.indexOf(word);
			//将result中匹配字符串前面部分和当前部分处理后的结果存入结果字符串构造中
			resultBuilder.append(result, 0, wordIndex).append(sb);
			//将result中已经存入resultBuilder中的部分移除.
			result = result.substring(wordIndex+wl);
			//清空中间字符串构造
			sb.delete(0,sb.length());
			replaced++;
		}
		if(replaced<1){
			result = firstMatcher.replaceFirst(first+"");
		}else{
			result = resultBuilder.append(result).toString().replaceFirst(END_CHECK,"$1");
		}
		return result;
	}

	/**
	 * @param humpStr 驼峰字符串
	 * @return 下划线字符串
	 */
	public static String transfer2underline(String humpStr) {
		return transfer2underline(humpStr,false);
	}

}
