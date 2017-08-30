package cc.linkedme.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  

public class MapUtil {
	public transient static final Logger log = LoggerFactory.getLogger(MapUtil.class);
	
	public static <K, T> Map<K, Set<T>> newSetMap(K[] keys, T type) {
		Map<K, Set<T>> map = new HashMap<K, Set<T>>();
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], new HashSet<T>());
		}
		return map;
	}

	public static <K, T> void setMapValue(Map<K, Set<T>> map, K key, T[] value) {
		if (value != null && map != null && map.get(key) != null) {
			map.get(key).addAll(Arrays.asList(value));
		} else {
			throw new RuntimeException("map,map key or value is null,please check");
		}
	}

	public static <K> void setMapValue(Map<K, Set<String>> map, K key, String slipts) {
		if (slipts != null && !"".equals(slipts)) {
			setMapValue(map, key, slipts.split(","));
		}
	}

	/**
	 * 合并两个Map中的Set
	 * @param <K>
	 * @param <T>
	 * @param des
	 * @param src
	 */
	public static <K, T> void mergeMap(Map<K, Set<T>> des, Map<K, Set<T>> src) {
		Iterator<K> itsrc = src.keySet().iterator();
		while (itsrc.hasNext()) {
			K key = itsrc.next();
			Set<T> set = des.get(key);
			if (set == null) {
				des.put(key, src.get(key));
			}else{
				set.addAll(src.get(key));
			}
		}

	}

	public static <K, T> boolean isEmptyMapSet(Map<K, Set<T>> map) {
		for (K key : map.keySet()) {
			if (!map.get(key).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public static <K, T> boolean hasEmptyMapArray(Map<K, T[]> map) {
		return isEmptyMapArray(map, true);
	}

	public static <K, T> boolean isEmptyMapArray(Map<K, T[]> map, boolean all) {
		boolean flag = true;
		for (K key : map.keySet()) {
			flag = false;
			if (all) {
				if (map.get(key).length ==0) {
					return true;
				}else{
					log.info("map "+key+ " is not empty: first element is:" + map.get(key)[0]);
				}
			}else{
				if(map.get(key).length > 0){
					log.info("map "+key+ " is not empty: first element is:" + map.get(key)[0]);
					return false;
				}
			}

		}
		return flag;
	}

	public static <K, T> String printMapSet(Map<K, Set<T>> map) {
		StringBuffer s = new StringBuffer();
		if (map==null) {
			return s.toString();
		}
		for (K key : map.keySet()) {
			s.append(key + ":" + Arrays.asList(map.get(key)) + "---------");
		}
		return s.toString();
	}

	public static <K, T> String printMapArray(Map<K, T[]> map) {
		StringBuffer s = new StringBuffer();
		for (K key : map.keySet()) {
			s.append(key + ":" + Arrays.asList(map.get(key)) + "---------");
		}
		return s.toString();
	}

	public static <K, T> void fillMapList(Map<K, List<T>> map, K key, List<T> list) {
		List<T> temp = map.get(key);
		if (temp != null) {
			temp.addAll(list);
		} else {
			map.put(key, list);
		}
	}
	
	/**
     * 
      * DTO转map
      * 
      * @param source
      * @param target 
      * @return void
     */
    public static void objectToMap(Object source, Map target) {
        try {
            target.putAll(ClassInvokeUtil.initCommonDTO(source));
        }
        catch (Exception e) {
            throw new RuntimeException("fail to  mapToObject!",e);
        }
    }

    /**
     * 
      * Map转dto
      * 
      * @param source
      * @param target 
      * @return void
     */
    public static void mapToObject(Map source, Object target) {
        try {
            ClassInvokeUtil.objectToMap(source, target);
        }
        catch (Exception e) {
            throw new RuntimeException("fail to  objectToMap!",e);
        }
    }
    
    public static Map initCommonDTO(Object commonDTO) throws Exception {
        Map rsMap = new HashMap();
        Class dtoClass = commonDTO.getClass();
        Field[] fields = dtoClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field filed = fields[i];
            String filedName = filed.getName();
            Object value = getMethodValue(commonDTO, upperFirst(filedName));
            rsMap.put(filedName, value);
        }
        return rsMap;
    }
    
    private static String upperFirst(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
    }
    
    public static Object getMethodValue(Object obj, String methodName) throws Exception {
        methodName = "get" + upperFirst(methodName);
        Method method = getTargetMethod(obj, methodName);
        return method.invoke(obj, null);
    }
    
    public static Method getTargetMethod(Object facade, String facadeMethod) {
        Class type = facade.getClass();
        Method methods[] = type.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (facadeMethod.equals(methods[i].getName())) {
                Class[] paramTypes = methods[i].getParameterTypes();
                if (null == paramTypes || 0 == paramTypes.length || 1 == paramTypes.length) {
                    return methods[i];
                }
                else {
                    // 只调用参数个数为0或者1的方法，其他忽略
                    continue;
                }
            }
        }
        throw new RuntimeException("Invalid target method " + facadeMethod + " In Class" + facade);
    }
}
