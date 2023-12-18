package com.example.www;

public class ClassUtils
{

	/**
	 * 패키지+클래스명을 짧게 표현한다..
	 *
	 * 예) com.example.www.Application -> c.e.w.application
	 *
	 * @param clazz
	 * @return
	 */
	public static String getShtClassNm(Class<?> clazz)
	{
		return convertClassNameToAbbreviation(clazz.getPackage().getName()) + getSimpleName(clazz.getName());
	}

	private static String getSimpleName(String className)
	{
		// 클래스명에서 패키지명을 제외한 순수한 클래스명을 반환
		int lastDotIndex = className.lastIndexOf(".");
		if (lastDotIndex != -1)
		{
			return className.substring(lastDotIndex + 1);
		}
		return className;
	}

	private static String convertClassNameToAbbreviation(String className)
	{
		StringBuilder result = new StringBuilder();

		String[] parts = className.split("\\.");

		for (int i = 0; i < parts.length; i++)
		{
			String part = parts[i];

			result.append(part.charAt(0) + ".");
		}

		return result.toString();
	}
}