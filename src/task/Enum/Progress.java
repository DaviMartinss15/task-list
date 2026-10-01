package task.Enum;

public enum Progress {
	TO_DO, IN_PROGRESS, DONE;

	public String toDbValue() {
		switch (this) {
		case TO_DO:
			return "todo";
		case IN_PROGRESS:
			return "in-progress";
		case DONE:
			return "done";
		default:
			throw new IllegalArgumentException("Unknown progress: " + this);
		}
	}

	public static Progress fromDbValue(String value) {
		switch (value) {
		case "todo":
			return TO_DO;
		case "in-progress":
			return IN_PROGRESS;
		case "done":
			return DONE;
		default:
			throw new IllegalArgumentException("Unknown db value: " + value);
		}
	}
}
