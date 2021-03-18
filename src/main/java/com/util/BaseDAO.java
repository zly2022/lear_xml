package com.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T> {

	public int save(T t){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//反射出类的Class对象
			Class c = t.getClass();
			//反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			//获取表名
			String table = c.getName().substring(c.getName().lastIndexOf(".")+1).toLowerCase();
			//新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("insert into "+table+"(");
			for(Field f : fs){
				sql.append(f.getName()+",");
			}
			sql.setCharAt(sql.length()-1, ')');
			sql.append(" values(");
			for(Field f : fs){
				sql.append("?,");
			}
			sql.setCharAt(sql.length()-1, ')');
			System.out.println(sql);

			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			for(int i=0;i<fs.length;i++){
				Field f = fs[i];
				f.setAccessible(true);
				ps.setObject(i+1, f.get(t));
			}
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int update(T t, String pkname){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//反射出类的Class对象
			Class c = t.getClass();
			//反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			//获取表名
			String table = c.getName().substring(c.getName().lastIndexOf(".")+1).toLowerCase();
			//新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("update "+table+" set ");
			for(Field f : fs){
				if(!f.getName().equals(pkname)){
					sql.append(f.getName()+"=?,");
				}
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(" where "+pkname+"=?");
			System.out.println(sql);

			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			int index = 1;
			for(Field f : fs){
				if(!f.getName().equals(pkname)){
					f.setAccessible(true);
					ps.setObject(index++, f.get(t));
				}
			}
			Field f = c.getDeclaredField(pkname);
			f.setAccessible(true);
			ps.setObject(index, f.get(t));
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int delete(T t,String pkname){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//反射出类的Class对象
			Class c = t.getClass();
			//获取表名
			String table = c.getName().substring(c.getName().lastIndexOf(".")+1).toLowerCase();
			//新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("delete from "+table+" where "+pkname+"=?");
			System.out.println(sql);

			//反射出主键字段
			Field f = c.getDeclaredField(pkname);
			f.setAccessible(true);

			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			ps.setObject(1, f.get(t));
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * 根据主键查找返回对象(单行数据)
	 * @param t	包含主键数据的实体类
	 * @param pkname	主键字段名称
	 * @return
	 */
	public T get(T t, String pkname){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		T obj = null;
		try {
			//反射出类的Class对象
			Class c = t.getClass();
			//反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			//获取表名
			String table = c.getName().substring(c.getName().lastIndexOf(".")+1).toLowerCase();
			//新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			for(Field f : fs){
				sql.append(f.getName()+",");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(" from "+table+" where "+pkname+"=?");
			System.out.println(sql);

			Field field = c.getDeclaredField(pkname);
			field.setAccessible(true);
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			ps.setObject(1, field.get(t));
			rs = ps.executeQuery();
			while(rs.next()){
				obj = (T) c.newInstance();
				for(Field f : obj.getClass().getDeclaredFields()){
					f.setAccessible(true);
					if(f.getType().equals(String.class)){
						f.set(obj, rs.getString(f.getName()));
					}else if(f.getType().equals(int.class)||f.getType().equals(Integer.class)){
						f.set(obj, rs.getInt(f.getName()));
					}else if(f.getType().equals(double.class)||f.getType().equals(Double.class)){
						f.set(obj, rs.getDouble(f.getName()));
					}else if(f.getType().equals(Date.class)){
						f.set(obj, rs.getDate(f.getName()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}


	public List<T> list(Class c){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			//反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			//获取表名
			String table = c.getName().substring(c.getName().lastIndexOf(".")+1).toLowerCase();
			//新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			for(Field f : fs){
				sql.append(f.getName()+",");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(" from "+table);
			System.out.println(sql);

			con = DBUtil.getCon();
			System.out.println("测试一波");
			ps = con.prepareStatement(sql.toString());
			System.out.println("测试一1波");
			rs = ps.executeQuery();
			System.out.println("测试一2波");
			while(rs.next()){
				T obj = (T) c.newInstance();
				for(Field f : obj.getClass().getDeclaredFields()){
					f.setAccessible(true);
					if(f.getType().equals(String.class)){
						f.set(obj, rs.getString(f.getName()));
					}else if(f.getType().equals(int.class)||f.getType().equals(Integer.class)){
						f.set(obj, rs.getInt(f.getName()));
					}else if(f.getType().equals(double.class)||f.getType().equals(Double.class)){
						f.set(obj, rs.getDouble(f.getName()));
					}else if(f.getType().equals(Date.class)){
						f.set(obj, rs.getDate(f.getName()));
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}



}
