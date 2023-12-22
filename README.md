# Best Project Duo App

The Best Project Duo App is a Java-based application that helps identify the pair of employees who have worked together on common projects for the longest period of time. The application reads data from a CSV file containing work history information, calculates the overlap between project timelines, and determines the longest working pair.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven
- PostgreSQL

## Project Structure

- src/main/java/com/academy/bestprojectduoapp: Contains the Java source code.
  - controller: Contains the web controllers.
  - filemanager: Contains the CSVReader and other file-related classes.
  - model: Contains the entity classes (e.g., WorkHistory).
  - repository: Contains the repository interfaces.
  - service: Contains the service classes (e.g., BestDuoService).
- src/main/resources: Contains the application properties file (application.properties).

## Main functionality

The main functionality is in the BestDuoService class implementing a method called findLongestWorkingPair. 
This method takes a list of work history projects as input and finds the pair of employees who have worked together on a common project for the longest period of time. 
The code uses nested loops to compare each pair of projects, calculates the overlapping days between the project timelines, 
and keeps track of the total overlapping days for each pair of employees in a map. 
The method then identifies the pair with the longest overlap and returns their employee IDs and the duration of their collaboration. 
The code also includes a helper method for calculating the overlap between date ranges. 
Overall, this service is designed to find and analyze the most extended collaborative work history between two employees in a given list of projects.
