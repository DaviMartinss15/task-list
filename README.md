# Task List

A Java console application to manage a personal to-do list, with tasks persisted in a MySQL database.

## Features

- Add a new task (title + description), created with `todo` status by default
- List all tasks in a compact view
- Show full details of all tasks (title, description, progress)
- Delete a specific task by title
- Delete all tasks at once
- Change the progress of a task (`todo`, `in-progress`, `done`)

## Stack

- **Java**
- **JDBC** for persistence in **MySQL**
- Custom exceptions for database error handling
- Credentials externalized in a `.properties` file (not committed to the repository)

## Architecture

The project is organized in layers, following separation of concerns:

```
connection/   -> MySQL connection management (reads credentials from db.properties)
dao/          -> data access layer (JDBC, SQL queries)
service/      -> business logic, orchestrates DAO calls
task/         -> Task model and Progress enum
task/         -> TaskList, responsible for displaying tasks in the console
exceptions/   -> custom exceptions for the database layer
menu/         -> console interface (Scanner)
Main/         -> application entry point
```

### Progress as an enum

Task progress is modeled as a Java `enum` (`TO_DO`, `IN_PROGRESS`, `DONE`), mapped to a MySQL `ENUM` column (`'todo'`, `'in-progress'`, `'done'`) through explicit conversion methods (`toDbValue()` / `fromDbValue()`), since the naming conventions differ between Java and the database.

## How to run

1. Clone the repository
2. Create a `db.properties` file inside `src/` with your MySQL credentials:
   ```properties
   db.url=jdbc:mysql://localhost:3306/task_list
   db.user=root
   db.password=your_password
   ```
3. Create the `tasks` table in your database, with a default progress of `todo`
4. Run `Main/Application.java`

## Example usage

```
╔══════════════════════════════════╗
║            TASK LIST             ║
╠══════════════════════════════════╣
- Study Java
- Buy groceries
//////////////////////////////////////////////////////
What do you want to do with your tasks?
1 - Insert a task
2 - Delete a task
3 - Show tasks details
4 - Delete all tasks
5 - Modify a task details
0 - Exit
```

## Learning notes

This project was built as a study exercise, practicing:

- JDBC with externalized database credentials
- Mapping a Java enum to a MySQL `ENUM` column
- Layered architecture (DAO / Service / Menu)
- Separating data access, business logic and console display into distinct responsibilities
