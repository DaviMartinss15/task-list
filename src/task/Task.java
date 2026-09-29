package task;

import task.Enum.Progress;

public class Task {
	public String title;
	public String description;
	public Progress progress;
	
	public Task(String title, String description, Progress progress) {
		this.title = title;
		this.description = description;
		this.progress = progress;
		
	}
	
	public void showDetails() {
	    System.out.println("==============================");
	    System.out.println(" Title:    " + title);
	    System.out.println(" Description: " + description);
	    System.out.println(" Progress: " + progress);
	    System.out.println("==============================");
	}
}
