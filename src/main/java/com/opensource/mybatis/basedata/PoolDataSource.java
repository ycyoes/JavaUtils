package com.opensource.mybatis.basedata;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Properties;

public class PoolDataSource {
    public static PooledDataSource createPooledDataSource(String resource) throws IOException {
        Properties props = Resources.getResourceAsProperties(resource);
        PooledDataSource ds = new PooledDataSource();
        ds.setDriver(props.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl(props.getProperty("spring.datasource.url"));
        ds.setUsername(props.getProperty("spring.datasource.username"));
        ds.setPassword(props.getProperty("spring.datasource.password"));
        return ds;
    }


}
