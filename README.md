# Java Spring Boot Football Statistics Application

## Описание на проекта

Това е Java Spring Boot приложение, което анализира и визуализира информация за футболисти и отбори, базирано на данни от CSV файлове. Приложението идентифицира двойките играчи, които са играли заедно в общи мачове за най-дълго време, и представя тази информация чрез уеб изглед.

## Основни изисквания

- Четене на данни от следните CSV файлове:
  - `player.csv` (ID, FullName, TeamID)
  - `teams.csv` (ID, Name, ManagerFullName, Group)
  - `matches.csv` (ID, ATeamID, BTeamID, Score)
  - `records.csv` (ID, PlayerID, fromMinutes, toMinutes)
- Данните от CSV файловете се използват за запълване на база данни.
- Приложението трябва да реализира CRUD операции за Player, Team и Match.
- Потребителският интерфейс е реализиран с Thymeleaf.

## База данни
- PostgresSQL
- Релации в базата
  
  ![app_db](https://github.com/user-attachments/assets/52e7bb69-5dbd-4272-a9b4-e6f9fca02dd9)

## Архитектура

Проектът е реализиран като монолитно Java Spring Boot приложение с MVC архитектура. Основните компоненти са:

- **adapters**: Интерфейси и класове за адаптиране на модел обектите до data transfer обекти и обратно.
- **controllers**: Интерфейси и класове за управление на изгледите и обработка на HTTP заявки.
- **dtos**: Трансфер обекти и валидация.
- **models**: Entity обекти, анотирани с Hibernate анотации, които изграждат таблици в PostgreSQL база данни.
- **repositories**: Интерфейси и класове, отговорни за взаимодействие с базата данни.
- **service**: Интерфейси и класове, отговорни за връзката между repository и controller класовете.
- **utils**: Помощни интерфейси и класове (annotations, converters, validations).

## Изгледи

- **Home**: Описание на проекта и полезни линкове.

![app_home](https://github.com/user-attachments/assets/40ebb0ed-1a9f-4d8e-83bb-cc987d1a2d0c)

- **Statistics**: Табличен изглед на играчи от различни отбори, които са играли в общи мачове за най-дълго време.

![app_statistics](https://github.com/user-attachments/assets/4b6a9cdf-aa84-4dce-8319-307a9cbc0ad0)

- **Matches**: Табличен изглед на пълната информация за всички мачове.

![app_matches](https://github.com/user-attachments/assets/483de267-3e60-4eec-9c5f-ba4babd6d93f)

- **Teams**: Табличен изглед на пълната информация за отборите.

![app_teams](https://github.com/user-attachments/assets/6a5eb4d7-b7e1-4a6a-89a0-544731e98b5f)

- **Footballers**: Табличен изглед на пълната информация за футболистите.

![app_footballers](https://github.com/user-attachments/assets/46ab7126-9566-4062-9326-d8a3e78b7eaa)


## Всеки изглед предлага възможност за добавяне, актуализиране и изтриване на записи. 


### Тук Ви представям дизайните на изгледите за добавяне и актуализиране на Match.
![app_update_match](https://github.com/user-attachments/assets/fff84a67-522c-4152-af3d-a38eb6e6ecb0)
![app_create_match](https://github.com/user-attachments/assets/a20b6410-562b-435d-8fa9-83ad5d3123dc)

### Тук Ви представям дизайните на изгледите за добавяне и актуализиране на Team.
![app_create_team](https://github.com/user-attachments/assets/698371e4-f118-49e0-b5e9-be488bf006fc)
![app_update_footballer](https://github.com/user-attachments/assets/e73e3d44-8a01-4dc0-830d-fa21bd3542ad)

### Тук Ви представям дизайните на изгледите за добавяне и актуализиране на Player (Footballer).
![app_update_team](https://github.com/user-attachments/assets/84ce82f6-a23e-4a8f-9e7d-6731e8a9b66b)
![app_create_footballer](https://github.com/user-attachments/assets/e60b421d-12b6-48e1-b0a2-6e5c864a61e9)

## Функционалност

- При зареждане на Home страницата, базата данни се запълва с данни от CSV файловете, ако базата е празна (междинните таблици са празни).
- Имплементирани са CRUD операции за Player, Team и Match.
- Възможност за визуализация на статистики, мачове, отбори и футболисти.

## Инсталация и стартиране

1. Клонирайте репозитория:
   ```bash
   git clone <(https://github.com/pety02/FootbalPlayers_SirmaAcademySeason4_finalExam)>
