package cn.xiaolonghong.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * C3P0Demo:
 *
 * @author （Mr.Xiao)龙鸿
 * @version 1.0
 * @目标：
 * @date: 2019-12-28 10:18
 */
public class C3P0Demo {
    public static void main(String[] args) throws Exception {
        // 1.加载src下核心配置文件：c3p0-config.xml。成为一个C3P0的连接池对象（数据源DataSource）
        DataSource dataSource = new ComboPooledDataSource();
        // 指定加载某个c3p0的核心配置文件创建连接池对象
        // DataSource dataSource = new ComboPooledDataSource("dlei-config.xml");

        // 2.通过连接池对象获取连接。
        for(int i = 1 ; i <= 10 ; i++ ){
            Connection con = dataSource.getConnection();
            System.out.println("输出第"+i+"个连接："+con);
            if(i==10)con.close(); // 关闭了第10个连接。
        }

        Connection con = dataSource.getConnection();
        System.out.println("输出第11个连接："+con);

        // 2.拼接一个SQL语句
        // 建表
        StringBuilder sb = new StringBuilder();
        sb.append("create table tb_user1(")
                .append("id int primary key auto_increment,")
                .append("loginName varchar(23),")
                .append("userName varchar(23),")
                .append("passWord varchar(23))");

        // 3.获取一个发送SQL语句的对象：
        PreparedStatement pstm = con.prepareStatement(sb.toString());

        // 4.通过Statement开始发送SQL语句
        System.out.println(pstm.execute());
        System.out.println("建表成功！");
        con.close();
    }
}
