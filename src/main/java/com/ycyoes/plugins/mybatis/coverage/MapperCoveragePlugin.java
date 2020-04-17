package com.ycyoes.plugins.mybatis.coverage;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import com.ycyoes.plugins.mybatis.coverage.CoverageResult;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
), @Signature(
        type = Executor.class,
        method = "queryCursor",
        args = {MappedStatement.class, Object.class, RowBounds.class}
), @Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
)})
public class MapperCoveragePlugin implements Interceptor {
    Configuration configuration;
    CoverageResult coverageResult;

    public MapperCoveragePlugin() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement statement = (MappedStatement)invocation.getArgs()[0];
        if (this.coverageResult.addCoveredId(statement.getId())) {
            this.coverageResult.printToConsole();
            this.coverageResult.storeToFile();
        }

        return invocation.proceed();
    }

    Configuration retriveConfiguration(BaseExecutor executor) throws NoSuchFieldException, IllegalAccessException {
        Field configField = BaseExecutor.class.getDeclaredField("configuration");
        configField.setAccessible(true);
        return (Configuration)configField.get(executor);
    }

    Configuration retriveConfiguration(CachingExecutor executor) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = executor.getClass().getDeclaredField("delegate");
        declaredField.setAccessible(true);
        return this.retriveConfiguration((BaseExecutor)declaredField.get(executor));
    }

    void retriveConfigurationAndInitAllMapperIds(Object proxyed) throws NoSuchFieldException, IllegalAccessException, IOException {
        if (this.configuration == null) {
            if (Proxy.isProxyClass(proxyed.getClass())) {
                System.out.println(proxyed);
                Plugin plugin = (Plugin)Proxy.getInvocationHandler(proxyed);
                Field field = plugin.getClass().getDeclaredField("target");
                field.setAccessible(true);
                this.configuration = this.retriveConfiguration((CachingExecutor)field.get(plugin));
            } else if (proxyed instanceof CachingExecutor) {
                this.configuration = this.retriveConfiguration((CachingExecutor)proxyed);
            } else if (proxyed instanceof BaseExecutor) {
                this.configuration = this.retriveConfiguration((BaseExecutor)proxyed);
            }

            if (this.configuration != null) {
                Set<String> allMapperIds = (Set)this.configuration.getMappedStatementNames().stream().filter((s) -> {
                    return s.contains(".");
                }).collect(Collectors.toSet());
                this.coverageResult = new CoverageResult(allMapperIds);
                this.coverageResult.readFromFile();
            } else {
                throw new RuntimeException("could not found mybatis's configuration");
            }
        }
    }

    public Object plugin(Object target) {
        try {
            this.retriveConfigurationAndInitAllMapperIds(target);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}
