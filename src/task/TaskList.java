package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
	public List<Task> taskList = new ArrayList<>();

	public void showList(List<Task> taskList) {
		System.out.println("╔══════════════════════════════════╗");
		System.out.println("║            TASK LIST             ║ ");
		System.out.println("╠══════════════════════════════════╣");

		for (Task task : taskList) {
			System.out.println("║ -" + task.title);
		}
	}

	public void showTasksDetails(List<Task> taskList) {
		for (Task task : taskList) {
			System.out.println("==============================");
			System.out.println(" Title: " + task.title);
			System.out.println(" Description: " + task.description);
			System.out.println(" Progress: " + task.progress);
			System.out.println("==============================");
		}

	}
}
