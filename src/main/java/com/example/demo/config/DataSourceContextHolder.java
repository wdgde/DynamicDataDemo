package com.example.demo.config;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-11 16:33
 **/
public class DataSourceContextHolder {

    private static ThreadLocal<String> datasourceContext = new ThreadLocal<>();

    public static void setDataSource(String datasource) {
        datasourceContext.set(datasource);
    }

    public static String getDataSource() {
        return datasourceContext.get();
    }

    public static void clear() {
        datasourceContext.remove();
    }
}