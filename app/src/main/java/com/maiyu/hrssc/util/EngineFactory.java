package com.maiyu.hrssc.util;


import java.io.IOException;
import java.util.Properties;

/**
 * 业务逻辑工厂类
 * @author xiongning
 *
 * 2015年5月18日
 */
public class EngineFactory {
    private static Properties properties;
    static {
        properties = new Properties();
        try {
            properties.load(EngineFactory.class.getClassLoader().getResourceAsStream("engine.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取实例
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clazz) {
        String name = clazz.getSimpleName();
        String className = properties.getProperty(name);
        try {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
