package mybatis.batch;


import com.opensource.mybatis.basedata.PoolDataSource;
import com.opensource.mybatis.first.User;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.util.Collection;

public class BatchInsert {
    private static SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        // create an SqlSessionFactory
        try (Reader reader = Resources.getResourceAsReader("mybatis/batch/mybatis-config.xml")) {
            System.out.println("------------before each------------");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            System.out.println("-----" + sqlSessionFactory);
        }

    }

    @Test
    public void batchInsert() {
        System.out.println("-----------test--------------");
        try(SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)){
//            PooledDataSource ds = PoolDataSource.createPooledDataSource("application.properties");
            User user = new User("Json", "json@163.com", 12, 1, "middle school");
            User user1 = new User("mark", "mark@163.com", 12, 1, "mark middle school");
            Configuration configuration = sqlSessionFactory.getConfiguration();
            System.out.println(configuration.getMappedStatementNames());
            Collection collection = configuration.getMappedStatementNames();
            collection.forEach(col -> System.out.println(col));
            sqlSession.insert("insert", user);
            sqlSession.insert("insert", user1);
            sqlSession.flushStatements();
            sqlSession.commit();
        }
    }

}
