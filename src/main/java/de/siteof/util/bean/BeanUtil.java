package de.siteof.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Bean utility class.
 * 
 * @author Daniel Ecer
 */
public class BeanUtil {
	
	public static String formatPropertyName(String propertyName, String wordSpace, boolean uppercaseFirstLetter) {
		StringBuffer sb = new StringBuffer();
		int length = propertyName.length();
		for (int i = 0; i < length; i++) {
			char ch = propertyName.charAt(i);
			if ((i == 0) || (Character.isUpperCase(ch))) {
				if (i > 0) {
					sb.append(wordSpace);
				}
				if (uppercaseFirstLetter) {
					sb.append(Character.toUpperCase(ch));
				} else {
					sb.append(Character.toLowerCase(ch));
				}
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public static String decodeClassName(Class c) {
		return (c != null ? decodeClassName(c.getName()) : null);
	}

	public static String decodeClassName(String className) {
		if (className.startsWith("[")) {
			int arrayDimension	= 0;
			while(className.charAt(arrayDimension) == '[') {
				arrayDimension++;
			}
			char identifier	= className.charAt(arrayDimension);
			StringBuffer sb	= new StringBuffer();
			switch(identifier) {
				case 'Z':
					sb.append("boolean");
					break;
				case 'B':
					sb.append("byte");
					break;
				case 'C':
					sb.append("char");
					break;
				case 'S':
					sb.append("short");
					break;
				case 'I':
					sb.append("int");
					break;
				case 'J':
					sb.append("long");
					break;
				case 'F':
					sb.append("float");
					break;
				case 'D':
					sb.append("double");
					break;
				case 'L':
					int index	= className.indexOf(';', arrayDimension + 1);
					if (index < 0) {
						throw new IllegalArgumentException("missing ';' in class name: " + className);
					}
					sb.append(className.substring(arrayDimension + 1, index));
					break;
				default:
					throw new IllegalArgumentException("unknown class identifier: " + identifier + " in " + className);
			}
			for (int i = 0; i < arrayDimension; i++) {
				sb.append("[]");
			}
			return sb.toString();
		}
		return className;
	}
	
	public static String parsePropertyName(String propertyName, String wordSpace) {
		StringBuffer sb = new StringBuffer();
		int length = propertyName.length();
		boolean uppercaseNext = false;
		for (int i = 0; i < length; i++) {
			char ch = propertyName.charAt(i);
			if (wordSpace.indexOf(ch) >= 0) {
				uppercaseNext	= true;
			} else {
				if (uppercaseNext) {
					sb.append(Character.toUpperCase(ch));
					uppercaseNext	= false;
				} else {
					sb.append(Character.toLowerCase(ch));
				}
			}
		}
		return sb.toString();
	}

	public static boolean isReserved(String name) {
		if (name.equals("class")) {
			return true;
		}
		return false;
	}

	public static String getJavaName(String name) {
		String s	= name;
		if (s.toUpperCase().equals(s)) {
			s	= s.toLowerCase();
		}
		StringBuffer sb	= new StringBuffer(s.length());
		boolean uppercaseNext	= false;
		int index	= 0;
		while (index < s.length()) {
			char ch	= s.charAt(index);
			if (uppercaseNext) {
				ch	= Character.toUpperCase(ch);
				uppercaseNext	= false;
			}
			if (ch == '-') {
				uppercaseNext	= true;
			} else if ((ch == '[') && (s.charAt(index + 1) == ']')) {
				index++;
				sb.append("Array");
			} else if (Character.isJavaIdentifierPart(ch)) {
				sb.append(ch);
			} else {
				sb.append("_");
			}
			index++;
		}
		s	= sb.toString();
		if (isReserved(s)) {
			s	= "_" + s;
		}
		return s;
	}

	public static String getClassName(String name) {
		String s	= getJavaName(name);
		if (name.toUpperCase().equals(name)) {
			s	= s.toUpperCase();
		}
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

	public static String getGetterSetterSuffix(String name) {
		String s	= getJavaName(name);
		if (name.toUpperCase().equals(name)) {
			s	= s.toUpperCase();
		}
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

	public static String getConstantName(String name) {
		StringBuffer sb	= new StringBuffer();
		boolean insideWord	= false;
		for (int i = 0; i < name.length(); i++) {
			char ch	= name.charAt(i);
			if ((ch == '_') || (!Character.isJavaIdentifierPart(ch))) {
				sb.append('_');
				insideWord	= false;
			} else if (Character.isUpperCase(ch)) {
				if (insideWord) {
					sb.append('_');
					insideWord	= false;
				}
				sb.append(ch);
			} else {
				sb.append(Character.toUpperCase(ch));
				insideWord	= Character.isLetter(ch);
			}
		}
		return sb.toString();
	}
	
	public static String getShortName(String className) {
		int index	= className.lastIndexOf('$');
		if (index < 0) {
			index	= className.lastIndexOf('.');
		}
		return (index >= 0 ? className.substring(index + 1) : className);
	}
	
	public static String getShortName(Class c) {
		return getShortName(c);
	}
	
	public static Object getFieldValue(Class declaringClass, Object instance, String fieldName) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
		if (declaringClass == null) {
			declaringClass	= instance.getClass();
		}
		Field field	= declaringClass.getField(fieldName);
		return field.get(instance);
	}
	
	public static Object getFieldValue(Class declaringClass, String fieldName) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
		return getFieldValue(declaringClass, null, fieldName);
	}
	
	public static Object[] getDeclaredConstants(Class declaringClass, Class elementType) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields	= declaringClass.getDeclaredFields();
		Collection	constants	= new ArrayList();
		for(int i = 0; i < fields.length; i++) {
			Field field	= fields[i];
			int modifiers	= field.getModifiers();
			if ((Modifier.isStatic(modifiers)) && (Modifier.isPublic(modifiers)) &&
					((elementType == null) || (elementType.isAssignableFrom(field.getClass())))) {
				constants.add(field.get(null));
			}
		}
		return constants.toArray();
	}
	
	public static Object[] getDeclaredConstants(Class declaringClass) throws IllegalArgumentException, IllegalAccessException {
		return getDeclaredConstants(declaringClass, null);
	}

}
