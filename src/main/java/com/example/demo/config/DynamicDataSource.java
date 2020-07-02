package com.example.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-11 16:14
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    //    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
//        super.setDefaultTargetDataSource(defaultTargetDataSource);
//        super.setTargetDataSources(targetDataSources);
//        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
//        super.afterPropertiesSet();
//    }
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

    @Override
    protected Object determineCurrentLookupKey() {
        //return DataSourceContextHolder.getDataSource();
        return getDataSource();
    }
}