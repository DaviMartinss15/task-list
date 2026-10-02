package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ManageConnection;
import exceptions.DbException;
import task.Task;
import task.Enum.Progress;

public class ListDAO {
	public void insertTask(Task task) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO tasks (title, description, progress) VALUES (?, ?, ?);";
		try{
			conn = ManageConnection.openConnect();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, task.title);
			preparedStatement.setString(2, task.description);
			preparedStatement.setString(3, task.progress.toDbValue());
			
			preparedStatement.executeUpdate();
			System.out.println("Task inserted!");
			
		}catch(SQLException e) {
			throw new DbException("An erro are detected: " + e.getMessage());
		}finally {
			ManageConnection.closeStatement(preparedStatement);
			ManageConnection.closeConnection(conn);
		}
	}
	
	public void deleteTask(String title) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM tasks WHERE title = (?);";
		try{
			conn = ManageConnection.openConnect();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, title);
			
			
			preparedStatement.executeUpdate();
			System.out.println("Task deleted!");
			
		}catch(SQLException e) {
			throw new DbException("An erro are detected: " + e.getMessage());
		}finally {
			ManageConnection.closeStatement(preparedStatement);
			ManageConnection.closeConnection(conn);
		}
	}
	
	public List<Task> listTasks() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT title, description, progress FROM tasks;";
		List<Task> taskList = new ArrayList<>();
		try {
			conn = ManageConnection.openConnect();
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				Progress progress = Progress.fromDbValue(resultSet.getString("progress"));
				
				Task task = new Task(title, description, progress);
				taskList.add(task);
			}
			return taskList;
		}catch(SQLException e) {
			throw new DbException("An erro are detected: " + e.getMessage());
		}finally {
			ManageConnection.closeStatement(preparedStatement);
			ManageConnection.closeResultSet(resultSet);
			ManageConnection.closeConnection(conn);
		}
	}
	
	public void deleteAllTasks() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM tasks;";
		try{
			conn = ManageConnection.openConnect();
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.executeUpdate();
			System.out.println("All Tasks are deleted!");
			
		}catch(SQLException e) {
			throw new DbException("An erro are detected: " + e.getMessage());
		}finally {
			ManageConnection.closeStatement(preparedStatement);
			ManageConnection.closeConnection(conn);
		}
		
	}
	
	public Task getTask(String title) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Task task = null;
		String query = "SELECT title, description, progress FROM tasks WHERE title = (?);";
		try {
			conn = ManageConnection.openConnect();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String title_ = resultSet.getString("title");
				String description = resultSet.getString("description");
				Progress progress = Progress.fromDbValue(resultSet.getString("progress"));
				
				task = new Task(title_, description, progress);
			}
			return task;
		}catch(SQLException e) {
			throw new DbException("An erro are detected: " + e.getMessage());
		}finally {
			ManageConnection.closeStatement(preparedStatement);
			ManageConnection.closeResultSet(resultSet);
			ManageConnection.closeConnection(conn);
		}
	}
	
	public void modifyProgress(Progress newStatus, int id) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE tasks SET progress = (?) WHERE id = (?);";
		String statusString = newStatus.toDbValue();
		try{
			conn = ManageConnection.openConnect();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, statusString);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException("An erro are detected: " + e.getMessage());
		}finally {
			ManageConnection.closeStatement(preparedStatement);
			ManageConnection.closeConnection(conn);
		}
		System.out.println("Task progress updated!");
	}
}

