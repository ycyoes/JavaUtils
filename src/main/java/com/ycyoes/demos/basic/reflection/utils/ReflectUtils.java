package com.ycyoes.demos.basic.reflection.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;
import java.util.stream.Collectors;

import com.ycyoes.demos.basic.reflection.RichType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ycyoes.utils.test.StringUtils.lineToHump;

public class ReflectUtils {
	private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		List<String> list = new ArrayList<String>();
		System.out.println(isCollection(List.class));
		System.out.println(isCollection(list.getClass()));

		List<Class<?>> cls = new ArrayList<Class<?>>();
		cls.add(Object.class);
		cls.add(List.class);
		cls.add(SortedSet.class);
		System.out.println(getTypes(cls));
		
		Field fields = RichType.class.getDeclaredField("richField");
		System.out.println(fields);
	}

	/**
	 * 判断是否为集合
	 * A.class.isAssignableFrom(B.class) -> A是否为B的父类或父接口
	 * @param <T>
	 * @param type
	 * @return
	 */
	public static <T> boolean isCollection(Class<T> type) {
	    return Collection.class.isAssignableFrom(type);
	  }
	
	//form class name - 组装类名
	public static String getTypes(List<Class<?>> list) {
		return Optional.ofNullable(list).orElseGet(Collections::emptyList)
		          .stream().map(Class::getSimpleName).collect(Collectors.joining(","));
	}

	/**
	 * ResultSet设值到实体
	 *
	 * @param rs
	 * @param clz
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> resultSetToEntity(ResultSet rs, Class<T> clz) throws Exception{
		if (rs == null) {
			logger.warn("ResultSet为空.");
			return Collections.EMPTY_LIST;
		}
		List<T> list = new ArrayList<>();
		//获取结果集元数据
		ResultSetMetaData rsmeta = rs.getMetaData();
		Map<String, Class> map = new HashMap<>();
		Arrays.stream(clz.getDeclaredFields()).forEach((fs) -> {
			map.put(fs.getName(), fs.getType());
		});

		while(rs.next()) {
			T t = clz.getConstructor().newInstance();
			//获取结果集中字段数
			int count = rsmeta.getColumnCount();
			//循环取出字段名，并将元数据中数值赋值给对应实体对象属性
			for(int i = 0; i < count; i++) {
				//获取字段名
				String rsName = rsmeta.getColumnName(i + 1);
				String name = rsName.indexOf("_") > -1 ? lineToHump(rsName) : rsName;
				if (map.containsKey(name)) {
					String setName = "set" + name.toUpperCase().substring(0, 1) + name.substring(1);
					Class<?> type = map.get(name);
					Method setMethod = clz.getMethod(setName, type);
					//数据库中decimal类型，ResultSet中为Unknown MySQL Type,调用实体set方法设值报错：argument type mismatch
					//单独匹配进行设值
					switch (type.getName()) {
						case "java.lang.Long":
							setMethod.invoke(t, rs.getLong(rsName));
							break;
						case "java.lang.Short":
							setMethod.invoke(t, rs.getShort(rsName));
							break;
						default: setMethod.invoke(t, rs.getObject(rsName));
					}
				}
			}
			list.add(t);
		}
		return list;
	}
}
