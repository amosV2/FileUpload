package cn.demo.dao;

import java.sql.Connection;
import java.util.List;

import cn.demo.entity.FileBean;
import cn.demo.utils.JdbcUtils;
import cn.demo.utils.ReflectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DAO<T> {

	private QueryRunner queryRunner = new QueryRunner();

	private Class<T> clazz;

	public DAO(){
		clazz = ReflectionUtils.getSuperGenericType(getClass());
	}

	/**
	 * 新增、修改、删除
	 * @param sql
	 * @param args
	 */
	public void update(String sql,Object...args){
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			queryRunner.update(conn,sql,args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn);
		}

	}

	/**
	 * 查询列表
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql,Object...args){
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			return (List<T>) queryRunner.query(conn,sql,new BeanListHandler<>(FileBean.class),args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn);
		}
		return null;
	}

	/**
	 * 查询单个对象
	 * @param sql
	 * @param args
	 * @return
	 */
	public T getOne(String sql,Object...args){
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			return (T) queryRunner.query(conn,sql,new BeanHandler<>(FileBean.class),args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn);
		}
		return null;
	}

	/**
	 * 查询一个字段
	 * @param sql
	 * @param args
	 * @param <E>
	 * @return
	 */
	public <E> E getValue(String sql,Object...args){

		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			return (E) queryRunner.query(conn,sql,new ScalarHandler<>(),args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn);
		}
		return null;
	}

}
