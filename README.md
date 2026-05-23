# Patient monitoring and gamification system

Web application design using Spring Boot and Thymeleaf focused on healthcare monitoring and patient engagement.
The platform allows interaction between patients and doctors, facilitating medical follow-up and encouraging patient engagement through gamification techniques.
The application uses an SQLite database managed through DB Browser for SQLite for data storage and management

Technologies used
- Java
- CSS
- HTML
- Thymeleaf
- SQLite
- DB Browser for SQLite
- Maven

The project source code (src folder) is structured as follow

src/main/java
- The model package contains the main entities and classes: Achievement, Alert, DailyReportSymptom, DailyReport, GamificationStatus, HealthProfessional, HpPatient, MonitoringParameter, Patient, PatientAchievement, Symptom, User.
- The controllers package handles HTTP requests and navigation through views.
- The repositories packages implements the methods defined in the interfaces: JDBCAchievementsManager, JDBCAlertsManager, JDBCDailyReportManager, JDBCDailySymptomsManager, JDBCGamificationManager, JDBCHealthcareManagement, JDBCHpPatientManager, JDBCMonitoringParametersManager, JDBCPatientAchievementsManager, JDBCPatientManager, JDBCSymptomsManager, JDBCUserManager

src/main/resources
- The static/images folder contains all the photos and icons used and the css
- The templates folder contains the Thymeleaf HTML file used in the application view
- application.properties contains the main Spring Boot configuration: Application name, SQLite database connection, JDBC driver configuration, SQL initialization settings

Features of the application
#### Healthcare professional
 - Configure monitoring thresholds
 - Review patient reports
 - Manage alerts
 - Monitor patient progress
#### Patient
- Submit daily well-bieng reports
- Visualize personal progress
- Track gamification status
- Unlock achievements

The database stores information related to:
- Patients
- Healthcare professional
- Different achievements
- Gamification status
- Alerts
- Patient Symptoms
- Users role
- Daily reports
- Monitoring parameters

Installation and execution
#### Prerequisites
- Java 17+
- Maven
- SQLite
- DB Browser for SQLite
#### Steps
1. Clone the github repository
2. Open the project with Intellij or Eclipse
3. Configure the SQLite databe path in applications.properties
4. Run the application
5. Open in browser the following link: http://localhost:8080

Future improvements
- Contact the healthprofessional with the patient through the application
- Alerts notifications to the patient

This project is developed for educational purpose
