# GPS Navigation Management System

A Java console-based GPS Navigation Management System designed to manage locations and demonstrate practical **Data Structures and Algorithms (DSA)** concepts.

The project provides features such as adding, viewing, updating, deleting, searching, and sorting locations, along with nearest-location detection, favourite locations, and recent-search tracking. It demonstrates the use of Java Collections, searching algorithms, sorting algorithms, Object-Oriented Programming, and exception handling.

## Features

* Add Location
* View All Locations
* Update Location
* Delete Location
* Search Location by ID
* Search Location by Name
* Linear Search
* Binary Search
* HashMap-based Search
* Sort Locations by Distance
* Sort Locations by Name
* Sort Locations by ID
* Find Nearest Location
* Add Favourite Location
* Remove Favourite Location
* View Favourite Locations
* Track Recent Searches
* Custom Exception Handling
* Input Validation
* Menu-Driven Console Interface

## DSA Concepts Used

This project focuses on implementing and demonstrating fundamental Data Structures and Algorithms.

### Data Structures

* **ArrayList**

  * Stores all saved locations.
  * Provides dynamic storage and indexed access.

* **HashMap**

  * Maps Location IDs to Location objects.
  * Provides fast average-case lookup using the location ID.

* **LinkedList**

  * Maintains recent search history.
  * Keeps the most recently searched location at the beginning.
  * Stores a maximum of 5 recent searches.

### Searching Algorithms

* **Linear Search**

  * Searches locations sequentially by ID.
  * Time Complexity: `O(n)`

* **Binary Search**

  * Searches for a location ID in a sorted temporary list.
  * Time Complexity: `O(log n)` for the search operation.

* **HashMap Search**

  * Direct lookup using the Location ID as the key.
  * Average Time Complexity: `O(1)`

* **Name Search**

  * Performs a case-insensitive partial-name search.
  * Time Complexity: `O(n)`

### Sorting Algorithms

* **Bubble Sort**

  * Sorts locations by distance in ascending order.
  * Time Complexity: `O(n²)`

* **Selection Sort**

  * Sorts locations alphabetically by name.
  * Time Complexity: `O(n²)`

* **Collections.sort()**

  * Uses Java's built-in sorting mechanism to sort locations by ID.

### Other Algorithmic Operations

* Finding the nearest location using minimum-distance traversal.
* Maintaining recent searches using LinkedList operations.
* Managing favourites through location traversal.

## Technologies Used

* **Java**
* **Core Java**
* **Object-Oriented Programming (OOP)**
* **Data Structures and Algorithms (DSA)**
* **Java Collections Framework**

  * ArrayList
  * HashMap
  * LinkedList
* **Exception Handling**
* **Console-Based Application**
* **Eclipse IDE / VS Code**

## Project Structure

```text
GPSNavigationSystem/
│
└── src/
    └── gps/
        ├── GPSMain.java
        ├── GPSService.java
        ├── Location.java
        └── LocationNotFoundException.java
```

### Class Description

| Class                            | Responsibility                                                                           |
| -------------------------------- | ---------------------------------------------------------------------------------------- |
| `GPSMain.java`                   | Handles the menu-driven console interface and user input                                 |
| `GPSService.java`                | Contains CRUD operations, searching, sorting, favourites, recent searches, and DSA logic |
| `Location.java`                  | Represents a GPS location and its properties                                             |
| `LocationNotFoundException.java` | Custom exception for invalid/non-existing location IDs                                   |

## How It Works

The application starts with a menu containing different GPS operations.

```text
=====================================
 GPS NAVIGATION MANAGEMENT SYSTEM
=====================================

--------- MAIN MENU ---------
1. Add Location
2. View All Locations
3. Update Location
4. Delete Location
5. Search Location
6. Sort Locations
7. Find Nearest Location
8. Favourite Locations
9. Recent Searches
10. Exit
------------------------------
```

The user can select an operation and interact with the GPS location records through the console.

## Search Options

The application provides multiple ways to search for a location:

```text
1. ID (Linear)
2. ID (Binary)
3. ID (HashMap)
4. Name
```

This allows comparison between different searching techniques and their performance characteristics.

## Sorting Options

Locations can be sorted using different approaches:

```text
1. Distance (Bubble Sort)
2. Name (Selection Sort)
3. ID (Collections.sort)
```

This demonstrates both manually implemented sorting algorithms and Java's built-in sorting functionality.

## Time Complexity

| Operation             | Algorithm / Data Structure | Time Complexity               |
| --------------------- | -------------------------- | ----------------------------- |
| Add Location          | ArrayList + HashMap        | Average `O(1)`                |
| Search by ID          | Linear Search              | `O(n)`                        |
| Search by ID          | Binary Search              | `O(log n)` after sorting      |
| Search by ID          | HashMap                    | Average `O(1)`                |
| Search by Name        | Linear Search              | `O(n)`                        |
| Sort by Distance      | Bubble Sort                | `O(n²)`                       |
| Sort by Name          | Selection Sort             | `O(n²)`                       |
| Find Nearest Location | Linear Traversal           | `O(n)`                        |
| View Favourites       | Linear Traversal           | `O(n)`                        |
| Recent Search         | LinkedList                 | `O(1)` for add/remove at ends |

## Exception Handling

The project includes a custom exception:

```text
LocationNotFoundException
```

It is used when an operation is requested for a location ID that does not exist.

The application also validates integer and decimal input to prevent invalid user input from terminating the program unexpectedly.

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/galankipraveen97-hash/GPSNavigationSystem.git
```

### 2. Open the Project

Open the project using:

* Eclipse IDE
* IntelliJ IDEA
* Visual Studio Code

### 3. Locate the Main Class

Navigate to:

```text
src/gps/GPSMain.java
```

### 4. Run the Application

Run:

```text
GPSMain.java
```

### 5. Use the Menu

Follow the options displayed in the console to manage GPS locations.

## Example Location Data

A location contains:

* Location ID
* Location Name
* Latitude
* Longitude
* Distance from Current Location
* Favourite Status

Example:

```text
ID: 101 | Name: Hyderabad | Lat: 17.3850 | Lon: 78.4867 | Distance: 5.20 km | Favourite: Yes
```

## Future Enhancements

* Implement **Graph Data Structure** for roads and connections.
* Implement **Dijkstra's Algorithm** for shortest-path navigation.
* Implement **A* Search Algorithm** for optimized route finding.
* Calculate distance automatically using latitude and longitude.
* Add real-time GPS/location services.
* Add graphical user interface using JavaFX or Swing.
* Add database integration using MySQL and JDBC.
* Add map integration using a mapping API.
* Add route history and saved destinations.
* Add user authentication.

## 📸 Screenshots

### Main Menu

*Add application screenshot here.*

### Location Management

*Add application screenshot here.*

### Search Operations

*Add application screenshot here.*

### Sorting Operations

*Add application screenshot here.*

### Favourite Locations

*Add application screenshot here.*

### Recent Searches

*Add application screenshot here.*

## Project Highlights

* Demonstrates practical **Data Structures and Algorithms**
* Implements searching algorithms from scratch
* Implements sorting algorithms from scratch
* Uses multiple Java Collection data structures
* Demonstrates Object-Oriented Programming
* Includes custom exception handling
* Provides a menu-driven console interface
* Designed to be easy to understand and extend

## Author

**Praveen Galanki**

B. Tech – Electronics and Communication Engineering

Aspiring Java Full Stack Developer

----
