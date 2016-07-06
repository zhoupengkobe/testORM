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
 * ����ʹ��Map����װһ����¼
 * ʹ��List<Map<String,Object>>�洢������¼
 * @author ko
 *
 */
public class Demo2 {
	public static void test01(){
		Connection conn = JDBCUtil.getMysqlConn();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 Map<String, Object> row = new HashMap<String, Object>();
			try {
				ps = conn.prepareStatement("select empname,salary,age from emp where id=?");
				ps.setObject(1, 1);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					row.put("empname", rs.getObject(1));
					row.put("salary", rs.getObject(2));
					row.put("age", rs.getObject(3));
				}
			} catch (Exception e) {
			}finally {
				JDBCUtil.close(rs, ps, conn);
			}
			//����Map���Ǳ�����һ�еĶ�����Ϣ
			for (String key:row.keySet()) {
				System.out.print(key+"---->"+row.get(key)+"\t");
			}
	}
	
	
	public static void test02(){
		Connection conn = JDBCUtil.getMysqlConn();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			try {
				ps = conn.prepareStatement("select empname,salary,age from emp where id>?");
				ps.setObject(1, 1);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					Map<String, Object> row = new HashMap<String, Object>();
					row.put("empname", rs.getObject(1));
					row.put("salary", rs.getObject(2));
					row.put("age", rs.getObject(3));
					list.add(row);
				}
			} catch (Exception e) {
			}finally {
				JDBCUtil.close(rs, ps, conn);
			}
			//����Map���Ǳ�����һ�еĶ�����Ϣ
			for (Map<String, Object> row : list) {
				
				for (String key:row.keySet()) {
					System.out.print(key+"---->"+row.get(key)+"\t");
				}
				System.out.println();
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
			//����Map���Ǳ�����һ�еĶ�����Ϣ
			for (String empname : maps.keySet()) {
				Map<String, Object> row = maps.get(empname);
				for (String key:row.keySet()) {
					System.out.print(key+"---->"+row.get(key)+"\t");
				}
				System.out.println();
			}
	}

	public static void main(String[] args) {
		test03();
	}

}
