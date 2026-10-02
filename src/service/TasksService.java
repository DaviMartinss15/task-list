package service;

import java.util.List;

import dao.ListDAO;
import task.Task;
import task.Enum.Progress;

public class TasksService {
	private ListDAO dao = new ListDAO();

	public void insertTask(String title, String description) {
		Task task = new Task(title, description);
		dao.insertTask(task);
	}

	public void deleteTask(String title) {
		dao.deleteTask(title);
	}

	public List<Task> listTasks() {
		List<Task> taskList;
		taskList = dao.listTasks();
		return taskList;
	}

	public void deleteAllTasks() {
		dao.deleteAllTasks();
	}

	public Task getTask(String title) {
		Task task = dao.getTask(title);
		return task;
	}

	public void modifyProgress(Progress newStatus, int id) {
		dao.modifyProgress(newStatus, id);
	}
}
