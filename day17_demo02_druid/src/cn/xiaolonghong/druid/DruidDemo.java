package cn.xiaolonghong.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * DruidDemo:
 *
 * @author （Mr.Xiao)龙鸿
 * @version 1.0
 * @目标：阿里巴巴旗下的德鲁伊连接池
 * @date: 2019-12-28 09:49
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        // 1. 读取类路径(src目录)下的属性配置文件，转成一个字节输入流对象
        InputStream is = DruidDemo.class.getResourceAsStream("/druid.properties");
        Properties properties = new Properties();
        properties.load(is);
        // 2.可以通过德鲁伊的数据源工厂类：DruidDataSourceFactory 去加载字节输入流返回一个数据源对象。
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // 3.通过连接池对象获取连接。
        for(int i = 1 ; i <= 10 ; i++ ){
            Connection con = dataSource.getConnection();
            System.out.println("输出第"+i+"个连接："+con);
            if(i==10)con.close(); // 关闭了第10个连接。
        }

        Connection con = dataSource.getConnection();
        System.out.println("输出第11个连接："+con);

        // 4.拼接一个SQL语句
        // 建表
        StringBuilder sb = new StringBuilder();
        sb.append("create table tb_userdruid1(")
                .append("id int primary key auto_increment,")
                .append("loginName varchar(23),")
                .append("userName varchar(23),")
                .append("passWord varchar(23))");

        // 5.获取一个发送SQL语句的对象。
        PreparedStatement pstm = con.prepareStatement(sb.toString());

        // 6.通过Statement开始发送SQL语句
        System.out.println(pstm.execute());
        System.out.println("建表成功！");
        con.close();
    }
}
