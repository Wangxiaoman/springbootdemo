package cc.linkedme.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

public class ClassInvokeUtil {

    public static Object getMethodValue(Object obj, String methodName) throws Exception {
        methodName = "get" + upperFirst(methodName);
        Method method = getTargetMethod(obj, methodName);
        return method.invoke(obj, null);
    }

    public static void setMethodValue(Object obj, String methodName, Object value) throws Exception {
        methodName = "set" + upperFirst(methodName);
        Method method = getTargetMethod(obj, methodName);
        Object[] params = { value };
        method.invoke(obj, params);
    }

    public static List getAllFields(Object obj) {
        List filedList = new ArrayList();
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            filedList.add(upperFirst(field.getName()));
        }
        return filedList;
    }

    public static Class getReturnType(Object obj, String methodName) {
        methodName = "get" + upperFirst(methodName);
        Method method = getTargetMethod(obj, methodName);
        return method.getReturnType();
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

    public static Map initCommonDTO(Object commonDTO) throws Exception {
        Map rsMap = new HashMap();
        Class dtoClass = commonDTO.getClass();
        Field[] fields = dtoClass.getDeclaredFields();
        Class superclass = dtoClass.getSuperclass();
        if(null != superclass){
            Field[] declaredFields = superclass.getDeclaredFields();
            fields = ArrayUtils.addAll(fields, declaredFields);
        }
        for (int i = 0; i < fields.length; i++) {
            try {
                Field filed = fields[i];
                String filedName = filed.getName();
                Object value = getMethodValue(commonDTO, upperFirst(filedName));
                if(null != value){
                    rsMap.put(filedName, value.toString());
                }
            }
            catch (Exception e) {
                // TODO: handle exception
            }
        }
        return rsMap;
    }

    public static void objectToMap(Map source, Object target) throws Exception {
        Set keyset = source.keySet();
        Iterator iterator = keyset.iterator();

        while (iterator.hasNext()) {
            try{
                Object key = iterator.next();
                setMethodValue(target,String.valueOf(key),source.get(key));
            }catch(Exception e){
                
            }
        }
    }

    private static String upperFirst(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
    }

    public static Object invokeService(Object obj, String methodName, Object requestObj) throws Exception {
        Method method = getTargetMethod(obj, methodName);
        Object[] reqObj = { requestObj };
        if(requestObj == null){
            return method.invoke(obj, null);
        }else{
            return method.invoke(obj, reqObj);
        }
        
    }
}
