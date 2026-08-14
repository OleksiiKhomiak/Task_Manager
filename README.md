# Task Manager OOP

A small object-oriented Task Manager project created as part of my Personal Development Plan and for practising Java OOP concepts.

## Project Goal

The goal of this project is to practise object-oriented programming by building a simple system for managing projects and tasks.

## Features

* Create and store tasks
* Create users and projects
* Assign users to tasks
* Add tasks to projects
* Set task priority
* Change task status
* Find tasks by ID
* Display unfinished tasks
* Prevent duplicate task IDs
* Validate task data

## OOP Concepts Used

The project demonstrates:

* Classes and objects
* Encapsulation
* Constructors
* Methods
* Enums
* Lists of objects
* Relationships between classes
* Basic validation and exception handling

## Main Classes

### User

Stores information about a user.

### Task

Represents a task with a title, description, priority, status and assigned user.

### Project

Contains a collection of tasks.

### TaskManager

Manages tasks, users and projects and provides the main application functionality.

## Enums

### Priority

* LOW
* MEDIUM
* HIGH

### TaskStatus

* TODO
* IN_PROGRESS
* DONE

## Project Structure

```text
Task Manager
├── docs
│   └── TaskManager.puml
├── src
│   ├── model
│   │   ├── Priority.java
│   │   ├── Project.java
│   │   ├── Task.java
│   │   ├── TaskStatus.java
│   │   └── User.java
│   ├── service
│   │   └── TaskManager.java
│   └── Main.java
├── test
│   └── service
│       └── TaskManagerTest.java
└── README.md
```

## UML

A PlantUML class diagram is available in:

`docs/TaskManager.puml`

The diagram shows the relationships between `User`, `Task`, `Project`, `TaskManager`, `Priority`, and `TaskStatus`.

## Testing

The project includes JUnit 5 unit tests.

The tests check:

* Finding a task by ID
* Handling a task that does not exist
* Changing task status
* Changing task priority
* Returning only unfinished tasks
* Rejecting an empty task title
* Rejecting an invalid task ID
* Rejecting duplicate task IDs

Currently, all 8 unit tests pass successfully.

## How to Run

1. Open the project in IntelliJ IDEA.
2. Make sure a Java JDK is configured.
3. Open `Main.java`.
4. Run the `main()` method.
5. The program will display the tasks and unfinished tasks in the console.

## Personal Development

This project was created to improve my understanding of Java OOP and to practise designing, implementing and testing a small application.

During the project I worked with UML before coding, created several connected classes, used enums and encapsulation, implemented task-management functionality, added validation and created automated unit tests.
