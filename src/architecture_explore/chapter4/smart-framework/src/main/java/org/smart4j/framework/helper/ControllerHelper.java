package org.smart4j.framework.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 反映加载controller所有的action，封装成request和handle
 * @author jack
 * @since 1.0.0
 */
public final class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            controllerClassSet.forEach(controllerClass -> {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    for (Method m : methods) {
                        if (m.isAnnotationPresent(Action.class)) {
                            Action action = m.getAnnotation(Action.class);
                            String mapping = action.value();
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, m);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath){
        return ACTION_MAP.get(new Request(requestMethod, requestPath));
    }
}
