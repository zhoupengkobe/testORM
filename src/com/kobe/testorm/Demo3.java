package com.kobe.testorm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.RowData;

/**
 * 使用javabean对象来封装一条记录
 * 使用List<javabean>存储多条记录
 * @author ko
 *
 */
public class Demo3 {
	public static void test01(){
		Connection conn = JDBCUtil.getMysqlConn();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 Emp emp = null;
			try {
				ps = conn.prepareStatement("select empname,salary,age from emp where id=?");
				ps.setObject(1, 1);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					emp = new Emp(rs.getString(1),rs.getDouble(2),rs.getInt(3));
				}
				
			} catch (Exception e) {
			}finally {
				JDBCUtil.close(rs, ps, conn);
			}
			System.out.println(emp.getEmpname()+emp.getSalary()+emp.getAge());
			
	}
	
	
	public static void test02(){
		Connection conn = JDBCUtil.getMysqlConn();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 List<Emp> list = new ArrayList<Emp> ();
			try {
				ps = conn.prepareStatement("select empname,salary,age from emp where id>?");
				ps.setObject(1, 1);
				rs = ps.executeQuery();
				while (rs.next()) {
					Emp emp = new Emp(rs.getString(1),rs.getDouble(2),rs.getInt(3));
					list.add(emp);
				}
				
			} catch (Exception e) {
			}finally {
				JDBCUtil.close(rs, ps, conn);
			}
			//遍历Map就是遍历这一行的多列信息
			for (Emp emp : list) {
				System.out.println(emp.getEmpname()+emp.getSalary()+emp.getAge());
			}
	}
	
	public static void test03(){
		Connection conn = JDBCUtil.getMysqlConn();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		
		 Map<String,Map<String, Object>> maps = new HashMap<String,Map<String, Object>>();
			try {
				ps = conn.prepareStatement("select empname,salary,age from emp where id>?");
				ps.setObject(1, 1);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					Map<String, Object> row = new HashMap<String, Object>();
					row.put("empname", rs.getObject(1));
					row.put("salary", rs.getObject(2));
					row.put("age", rs.getObject(3));
					maps.put(rs.getString(1), row);
				}
			} catch (Exception e) {
			}finally {
				JDBCUtil.close(rs, ps, conn);
			}
			//遍历Map就是遍历这一行的多列信息
			for (String empname : maps.keySet()) {
				Map<String, Object> row = maps.get(empname);
				for (String key:row.keySet()) {
					System.out.print(key+"---->"+row.get(key)+"\t");
				}
				System.out.println();
			}
	}

	public static void main(String[] args) {
		test02();
	}

}
