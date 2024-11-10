# Task Management App

This app demonstrates the use of parallel task execution with proper state management in Jetpack Compose. It fetches data asynchronously from multiple sources and handles each task's success or failure individually while continuing the execution of the other tasks.

## Features
- **Parallel Execution of Tasks**: Tasks are fetched in parallel using Kotlin coroutines.
- **State Management**: The app uses sealed classes to manage different states of tasks: `NoState`, `Loading`, `Success`, and `Error`.
- **Error Handling**: If one task fails, other tasks continue running independently, and the error is reported for the failed task.
- **UI with Jetpack Compose**: The UI is built with Jetpack Compose, making it declarative, responsive, and easy to maintain.

## Architecture
- **Kotlin Coroutines**: Used for asynchronous task execution.
- **Jetpack Compose**: For creating a responsive and modern UI.
- **State Management**: A `sealed class` is used to manage the state of each task (`TaskState`).
- **Error Handling**: Each task's failure is handled independently to ensure that the UI remains responsive and other tasks continue running.
