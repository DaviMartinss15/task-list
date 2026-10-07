package menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import service.TasksService;
import task.Task;
import task.TaskList;
import task.Enum.Progress;

public class Menu {
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		TasksService service = new TasksService();
		do {
			List<Task> tasks = service.listTasks();
			TaskList taskListView = new TaskList();
			taskListView.showList(tasks);

			System.out.println("//////////////////////////////////////////////////////");
			System.out.println("What do you want to do with your tasks?");
			System.out.println("1 - Insert a task");
			System.out.println("2 - Delete a task");
			System.out.println("3 - Show tasks details");
			System.out.println("4 - Delete all tasks");
			System.out.println("5 - Modify an task details");
			System.out.println("0 - Exit");

			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {

			case 1:
				System.out.println("What is the task title?: ");
				String title = sc.nextLine();
				System.out.println("What is the task description?: ");
				String description = sc.nextLine();
				System.out.println("What is the task title?: ");
				Task task = new Task(title, description);
				service.insertTask(title, description);
				break;
			case 2:
				System.out.println("What is the task title you want to delete?: ");
				String titleToDelete = sc.nextLine();
				boolean found = false;
				for (Task t : tasks) {
					if (t.title.equalsIgnoreCase(titleToDelete)) {
						service.deleteTask(titleToDelete);
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println("Task not found!");
				}
				break;
			case 3:
				taskListView.showTasksDetails(tasks);
				break;
			case 4:
				System.out.println("You really want delete all tasks?");
				System.out.println(
						"If you choose this option, all your tasks will be deleted.Do you want to continue? (Y = yes | N = no)");
				String resp = sc.next().toUpperCase();
				if (resp.equals("Y")) {
					service.deleteAllTasks();
				} else if (resp.equals("N")) {
					System.out.println("Operation canceled!");
				} else {
					System.out.println("Not correct choice, operation canceled!");
				}
				break;
			case 5:
				System.out.println("What is the title you want to change the progress?: ");
				String titleToChangeProgress = sc.nextLine();
				boolean foundedTask = false;
				for (Task t : tasks) {
					if (t.title.equalsIgnoreCase(titleToChangeProgress)) {
						foundedTask = true;
						System.out.println("What is the new progress status? 1 - todo 2 - in-progress 3 - done");
						int respToChangeProgress = sc.nextInt();
						sc.nextLine();
						
						if (respToChangeProgress == 1) {
							service.modifyProgress(Progress.TO_DO, titleToChangeProgress);
						} else if (respToChangeProgress == 2) {
							service.modifyProgress(Progress.IN_PROGRESS, titleToChangeProgress);
						} else if (respToChangeProgress == 3) {
							service.modifyProgress(Progress.DONE, titleToChangeProgress);
						} else {
							System.out.println("Operation canceled!");
						}
						break;
					}
				}
				if (!foundedTask) {
					System.out.println("Task not found!");
				}

				break;

			case 0:
				System.out.println("Closing Task list!");
				sc.close();
				choice = 0;
				break;
			default:
				System.out.println("Incorrect enter!");
				break;
			}

		} while (choice != 0);
	}
}
