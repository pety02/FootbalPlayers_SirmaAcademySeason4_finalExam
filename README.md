# Java Spring Boot Football Players Application

## Project Description

This is a Java Spring Boot application that analyzes and visualizes information about football players and teams based on data from CSV files. The application identifies player pairs who have played together in common matches for the longest time and presents this information through web views.

## Main Requirements

- Reading data from the following CSV files:
  - `player.csv` (ID, FullName, TeamID)
  - `teams.csv` (ID, Name, ManagerFullName, Group)
  - `matches.csv` (ID, ATeamID, BTeamID, Score)
  - `records.csv` (ID, PlayerID, fromMinutes, toMinutes)
- The data from CSV files is used to populate the database.
- The application must implement CRUD operations for Player, Team, and Match.
- The user interface is implemented with Thymeleaf.

## Database
- PostgreSQL
- Database Relations
  
  ![app_db](https://github.com/user-attachments/assets/52e7bb69-5dbd-4272-a9b4-e6f9fca02dd9)

## Architecture

The project is implemented as a monolithic Java Spring Boot application with an MVC architecture. The main components are:

- **adapters**: Interfaces and classes for adapting model objects to data transfer objects and vice versa.
- **controllers**: Interfaces and classes for managing views and handling HTTP requests.
- **dtos**: Data transfer objects and validation.
- **models**: Entity objects annotated with Hibernate annotations that build tables in PostgreSQL.
- **repositories**: Interfaces and classes responsible for interacting with the database.
- **service**: Interfaces and classes responsible for the connection between repository and controller classes.
- **utils**: Utility interfaces and classes (annotations, converters, validations).

## Views

- **Home**: Project description and useful links.

  ![app_home](https://github.com/user-attachments/assets/40ebb0ed-1a9f-4d8e-83bb-cc987d1a2d0c)

- **Statistics**: Tabular view of players from different teams who have played in common matches for the longest time.

  ![app_statistics](https://github.com/user-attachments/assets/4b6a9cdf-aa84-4dce-8319-307a9cbc0ad0)

- **Matches**: Tabular view of full information about all matches.

  ![app_matches](https://github.com/user-attachments/assets/483de267-3e60-4eec-9c5f-ba4babd6d93f)

- **Teams**: Tabular view of full information about teams.

  ![app_teams](https://github.com/user-attachments/assets/6a5eb4d7-b7e1-4a6a-89a0-544731e98b5f)

- **Footballers**: Tabular view of full information about football players.

  ![app_footballers](https://github.com/user-attachments/assets/46ab7126-9566-4062-9326-d8a3e78b7eaa)

## Each view offers the ability to add, update, and delete records.

### Here are the designs for adding and updating a Match.
![app_update_match](https://github.com/user-attachments/assets/fff84a67-522c-4152-af3d-a38eb6e6ecb0)
![app_create_match](https://github.com/user-attachments/assets/a20b6410-562b-435d-8fa9-83ad5d3123dc)

### Here are the designs for adding and updating a Team.
![app_create_team](https://github.com/user-attachments/assets/698371e4-f118-49e0-b5e9-be488bf006fc)
![app_update_footballer](https://github.com/user-attachments/assets/e73e3d44-8a01-4dc0-830d-fa21bd3542ad)

### Here are the designs for adding and updating a Player (Footballer).
![app_update_team](https://github.com/user-attachments/assets/84ce82f6-a23e-4a8f-9e7d-6731e8a9b66b)
![app_create_footballer](https://github.com/user-attachments/assets/e60b421d-12b6-48e1-b0a2-6e5c864a61e9)

## Functionality

- On loading the Home page, the database is populated with data from CSV files if the database is empty (the intermediate tables are empty).
- CRUD operations for Player, Team, and Match are implemented.
- Allows visualization of statistics, matches, teams, and football players.

## Installation and Running

1. Clone the repository:
   ```bash
   git clone https://github.com/pety02/FootbalPlayers_SirmaAcademySeason4_finalExam

