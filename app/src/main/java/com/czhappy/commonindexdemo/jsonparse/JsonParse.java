package com.czhappy.commonindexdemo.jsonparse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParse {
	/**
	 * copy the attributes from JSON Object to Class we'd better just use to
	 * copy basic attribute, the recursion support is unchecked. (T)
	 * JsonParse.copy(t.getClass(), new JSONObject(result));
	 * 
	 * @param clazz
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static Object copy(Class clazz, JSONObject json)
			throws ReflectException {
		Object o;
		try {
			o = newInstance(clazz, null);
			Field[] fields = clazz.getDeclaredFields();
			String fieldName, sValue;
			Object fieldValue;
			for (Field field : fields) {
				if (isNeedSetAccessible(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.setAccessible(true);
				fieldName = field.getName();
				if (json.has(fieldName)) {
					sValue = json.get(fieldName).toString();
					fieldValue = getObjectFromString(field, sValue);
					try {
						field.set(o, fieldValue);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(e);
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			throw new ReflectException("Json data parse error:", e);
		}
		return o;
	}

	/**
	 * cast string to a Object by given Class,not support List閵嗕工rray data type
	 * and etc
	 * 
	 * @param clazz
	 * @param s
	 * @return
	 * @throws ReflectException
	 */
	public static Object getObjectFromString(Class clazz, String s)
			throws ReflectException {
		Object obj = s;
		if (clazz == boolean.class || clazz == Boolean.class) {
			try {
				obj = Boolean.parseBoolean(s);
			} catch (Exception e) {
				obj = false;
			}
		} else if (clazz == char.class || clazz == Character.class) {
			try {
				obj = s;
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == byte.class || clazz == Byte.class) {
			try {
				obj = Byte.parseByte(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == short.class || clazz == Short.class) {
			try {
				obj = Short.parseShort(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == int.class || clazz == Integer.class) {
			try {
				obj = Integer.parseInt(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == long.class || clazz == Long.class) {
			try {
				obj = Long.parseLong(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == float.class || clazz == Float.class) {
			try {
				obj = Float.parseFloat(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == double.class || clazz == Double.class) {
			try {
				obj = Double.parseDouble(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == String.class) {
			if (s == null || "".equals(s) || "null".equalsIgnoreCase(s)) {
				obj = null;
			} else {
				obj = s;
			}
		} else {
			// complex Object,the input String is JSON String
			// TODO support multi-type String data
			try {
				JSONObject jsonObject = new JSONObject(s);
				obj = copy(clazz, jsonObject);
			} catch (JSONException e) {
				obj = null;
			}

		}
		return obj;
	}

	public static Object getObjectFromString(Field field, String s)
			throws ReflectException {
		Class clazz = field.getType();
		Object obj = s;
		if (clazz == boolean.class || clazz == Boolean.class) {
			try {
				obj = Boolean.parseBoolean(s);
			} catch (Exception e) {
				obj = false;
			}
		} else if (clazz == char.class || clazz == Character.class) {
			try {
				obj = s.charAt(0);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == byte.class || clazz == Byte.class) {
			try {
				obj = Byte.parseByte(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == short.class || clazz == Short.class) {
			try {
				obj = Short.parseShort(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == int.class || clazz == Integer.class) {
			try {
				obj = Integer.parseInt(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == long.class || clazz == Long.class) {
			try {
				obj = Long.parseLong(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == float.class || clazz == Float.class) {
			try {
				obj = Float.parseFloat(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == double.class || clazz == Double.class) {
			try {
				obj = Double.parseDouble(s);
			} catch (Exception e) {
				obj = -1;
			}
		} else if (clazz == String.class) {
			if (s == null || "".equals(s) || "null".equalsIgnoreCase(s)) {
				obj = null;
			} else {
				obj = s;
			}
		} else if (isListType(clazz)) {
			// just support List interface,only instance a ArrayList to put data
			obj = new ArrayList();
			// get the generic type of List
			Class genericClass = getFieldGenericType(field);
			// json array
			try {
				JSONArray jsonArray = new JSONArray(s);
				boolean flag = isSimpleDataType(genericClass);
				for (int i = 0; i < jsonArray.length(); i++) {
					((List) obj).add(flag ? getObjectFromString(genericClass,
							jsonArray.getString(i)) : copy(genericClass,
							jsonArray.getJSONObject(i)));

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			// complex Object,the input String is JSON String
			// TODO support multi-type String data
			try {
				JSONObject jsonObject = new JSONObject(s);
				obj = copy(clazz, jsonObject);
			} catch (JSONException e) {
				obj = null;
			}

		}
		return obj;
	}

	/**
	 * new a java class's instance
	 * 
	 * @param Class
	 *            ,the java class
	 * @param args
	 *            ,the arguments required by constructor
	 * @return
	 * @throws ReflectException
	 */
	public static Object newInstance(Class clazz, Object[] args)
			throws ReflectException {
		try {
			Class[] argsClass = null;
			if (args != null) {
				argsClass = new Class[args.length];
				for (int i = 0; i < args.length; i++) {
					argsClass[i] = args[i].getClass();
				}
			}
			Constructor constructor = clazz.getDeclaredConstructor(argsClass);
			if (isNeedSetAccessible(constructor.getModifiers())) {
				constructor.setAccessible(true);
			}
			return constructor.newInstance();
		} catch (Exception e) {
			throw new ReflectException("New instance occurs exception", e);
		}

	}

	/**
	 * judge whether need to set the accessible attribute
	 * 
	 * @param modefiers
	 * @return
	 */
	private static boolean isNeedSetAccessible(int modefiers) {
		if (!Modifier.isPublic(modefiers)) {
			return true;
		}
		return false;
	}

	/**
	 * judge whether list type
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isListType(Class clazz) {
		if (List.class.isAssignableFrom(clazz)) {
			return true;
		}
		return false;
	}

	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
	 * 
	 * @param Field
	 *            field 字段
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("unchecked")
	public static Class getFieldGenericType(Field field, int index) {
		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			if (index >= fieldArgTypes.length || index < 0) {
				throw new RuntimeException("你输入的索引"
						+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
			}
			return (Class) fieldArgTypes[index];
		}
		return Object.class;
	}

	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
	 * 
	 * @param Field
	 *            field 字段
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("unchecked")
	public static Class getFieldGenericType(Field field) {
		return getFieldGenericType(field, 0);
	}

	public static boolean isSimpleDataType(Class clazz) {
		if (clazz == boolean.class || clazz == char.class
				|| clazz == byte.class || clazz == short.class
				|| clazz == int.class || clazz == long.class
				|| clazz == float.class || clazz == double.class
				|| clazz == Boolean.class || clazz == Character.class
				|| clazz == Byte.class || clazz == Short.class
				|| clazz == Integer.class || clazz == Long.class
				|| clazz == Float.class || clazz == Double.class
				|| clazz == String.class) {
			return true;
		}
		return false;
	}
}
