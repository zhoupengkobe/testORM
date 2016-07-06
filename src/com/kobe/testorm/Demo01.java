package com.kobe.testorm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试使用Object[] 来封装一条记录
 * 使用List<Object[]>存储多条记录
 * @author ko
 *
 */
public class Demo01 {

	public static void main(String[] args) {
		Connection conn = JDBCUtil.getMysqlConn();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 Object[] objs = null;
		 List<Object[]> list = new ArrayList<>();
			try {
				ps = conn.prepareStatement("select empname,salary,age from emp where id>?");
				ps.setObject(1, 1);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					objs = new Object[3];
//					System.out.println(rs.getObject(1)+"--->"+rs.getObject(2)+"--->"+rs.getObject(3));
					objs[0] = rs.getObject(1);
					objs[1] = rs.getObject(2);
					objs[2] = rs.getObject(3);
					
					list.add(objs);
				}
			} catch (Exception e) {
			}finally {
				JDBCUtil.close(rs, ps, conn);
			}

			for (Object[] objs1:list) {
				System.out.println(objs1[0]+"--->"+objs1[1]+"--->"+objs1[2]);
			}
	}

}
