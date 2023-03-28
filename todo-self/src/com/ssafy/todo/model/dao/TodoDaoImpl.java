package com.ssafy.todo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.todo.model.Todo;
import com.ssafy.todo.util.DBUtil;

public class TodoDaoImpl implements TodoDao {
	
	private static TodoDao todoDao = new TodoDaoImpl();
	private DBUtil dbUtil;
	
	private TodoDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static TodoDao getInstance() {
		return todoDao;
	}

	@Override
	public void clearTodo() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from todo");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}		
	}
	
	@Override
	public void deleteTodo(int no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from todo where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}		
	}

	@Override
	public void insertTodo(Todo todo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into todo(content) values(?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, todo.getContent());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<Todo> selectTodo() throws SQLException {
		List<Todo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, content ");
			sql.append("  from todo ");
			sql.append(" order by no ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Todo todo = new Todo();
				todo.setNo(rs.getInt("no"));
				todo.setContent(rs.getString("content"));
				list.add(todo);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
}
