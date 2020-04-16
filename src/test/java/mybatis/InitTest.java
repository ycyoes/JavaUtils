package mybatis;

import com.ycyoes.demos.basic.reflection.RichType;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis初始化流程相关测试
 *
 * @author ycyoes
 * @date 2020-04-16
 */
public class InitTest {

    @Test
    public void objectFactoryTest() {
        ObjectFactory objectFactory = new DefaultObjectFactory();
        List<String> list = objectFactory.create(ArrayList.class);
        list.add("apple");
        System.out.println(list);
    }

    @Test
    public void reflectorInvokerTest() throws InvocationTargetException, IllegalAccessException {
        ObjectFactory objectFactory = new DefaultObjectFactory();
        RichType richType = objectFactory.create(RichType.class);
        Reflector reflector = new Reflector(RichType.class);
        Invoker invoker = reflector.getSetInvoker("richField");
        invoker.invoke(richType, new Object[] {"set richField"});
        invoker = reflector.getGetInvoker("richField");
        System.out.println("richField = " + invoker.invoke(richType, null));
    }


}
