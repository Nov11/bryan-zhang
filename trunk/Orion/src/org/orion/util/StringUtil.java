
package org.orion.util;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class StringUtil {
	/**
	 * �ָ�� (Ĭ��Ϊ: , �� ; �� |)
	 */
	private static final String SPLIT_DELIMITER = ",|;|\\|";
	
	private StringUtil() {
	}

	

	/**
	 * ���ո�ʽ����ʽ��Java���ݶ���
	 * @param pattern ��ʽ��
	 * @param obj Java���ݶ���
	 * @return ��ʽ������ַ���
	 */
	public static String format(String pattern, Object obj) {
		if (obj == null)
			return "";

		if (obj instanceof String) {
			return (String) obj;
		}

		if (pattern == null) {
			return obj.toString();
		}

		if (obj instanceof java.util.Date) {
			return format(pattern, (java.util.Date) obj);
		}

		if (obj instanceof java.math.BigDecimal) {
			return format(pattern, (java.math.BigDecimal) obj);
		}

		if (obj instanceof Double) {
			return format(pattern, (Double) obj);
		}

		if (obj instanceof Float) {
			return format(pattern, (Float) obj);
		}

		if (obj instanceof Long) {
			return format(pattern, (Long) obj);
		}

		if (obj instanceof Integer) {
			return format(pattern, (Integer) obj);
		}

		return obj.toString();
	}

	/**
	 * ���ո�ʽ����ʽ��Date����
	 * @param pattern ��ʽ��
	 * @param locale ��������
	 * @param d Date����
	 * @return ��ʽ������ַ���
	 */
	public static String format(String pattern, Locale locale, java.util.Date d) {
		if (d == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
		return sdf.format(d);
	}

	/**
	 * ���ո�ʽ����ʽ��Date����
	 * @param pattern ��ʽ��
	 * @param d Date����
	 * @return ��ʽ������ַ���
	 */
	public static String format(String pattern, java.util.Date d) {
		if (d == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}

}