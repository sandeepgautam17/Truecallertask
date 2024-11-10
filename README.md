# Truecaller Task App

This app demonstrates the use of parallel task execution with proper state management in Jetpack Compose. It fetches data asynchronously and manipulate the data to cover the requirements for each tasks.

## Features
- **Parallel Execution of Tasks**: Tasks data are generated in parallel using Kotlin coroutines.
- **State Management**: The app uses sealed classes to manage different states of tasks: `Idle`, `Loading`, `Success`, and `Error`.
- **UI with Jetpack Compose**: The UI is built with Jetpack Compose, making it declarative, responsive, and easy to maintain.

## Architecture
- **Kotlin Coroutines**: Used for asynchronous task execution.
- **Jetpack Compose**: For creating a responsive and modern UI.
- **State Management**: A `sealed class` is used to manage the state of each task (`TaskState`).

## Extension Functions
The app uses **extension functions** to manipulate the content fetched for the tasks' results. These functions simplify the logic for each task and are key to performing the required operations efficiently and add reusability.

## UI
The three tasks UI is shown as a dropdown on the same screen. The result for the task is shown once the task item is clicked. 


## Unit Tests
Unit tests for content manipulation extension functions. 