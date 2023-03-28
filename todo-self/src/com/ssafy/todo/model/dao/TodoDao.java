package com.ssafy.todo.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.todo.model.Todo;

public interface TodoDao {
	void deleteTodo(int no) throws SQLException;
	void insertTodo(Todo todo) throws SQLException;
	List<Todo> selectTodo() throws SQLException;
	void clearTodo() throws SQLException;
}
