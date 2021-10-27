package mybatis.batch;


import com.opensource.mybatis.basedata.PoolDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

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
            PooledDataSource ds = PoolDataSource.createPooledDataSource("application.properties");
            Connection conn = ds.getConnection();

            System.out.println(ds.getConnection().toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
