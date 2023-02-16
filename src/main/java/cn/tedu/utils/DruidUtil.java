package cn.tedu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * 是一个工具类：用于通过druid连接池完成连接操作：
 *   数据源对象：javax.sql.DataSource接口对象，表求数据源，可以直接理解为连接池
 *   所有的连接池都会有一个数据源接口，druid实现这个数据源就是DruidDataSource
 */
public class DruidUtil {
    //1:定义一个静态数据源对象
    private static DataSource dataSource;
    //2:通过static代码块完成加载
    static {
        try {
            //3:创建一个Properties对象
            Properties properties=new Properties();
            //4:将配置文件转换字节输入流
            InputStream is=DruidUtil.class.getClassLoader().getResourceAsStream("application.properties");
            //5:使用properties对象加载这个is
            properties.load(is);
            //6:用druid底层实现方式（工厂设计模式）去加载配置文件，创建数据源对象
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //7:定义一个方未能用于返回数据源
    public static DataSource getDataSource(){
        return dataSource;
    }
}
