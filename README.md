# Patient monitoring and gamification system

Web application design using Spring Boot and Thymeleaf focused on patient monitoring and gamification.
The platform allows interaction between patients and doctors, facilitating medical follow-up and encouraging patient engagement through gamification techniques.
The application uses an SQLite database managed through DB Browser for SQLite for data storage and management

The technologies used are the following ones
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
- The controllers package handles HTTP requests and navigation throught views.
- The repositories packages implements the methods defined in the interfaces: JDBCAchievementsManager, JDBCAlertsManager, JDBCDailyReportManager, JDBCDailySymptomsManager, JDBCGamificationManager, JDBCHealthcareManagement, JDBCHpPatientManager, JDBCMonitoringParametersManager, JDBCPatientAchievementsManager, JDBCPatientManager, JDBCSymptomsManager, JDBCUserManager

src/main/resources
- The static/images folder contains all the photos and icons used and the css
- The templates folder contains the Thymeleaf HTML file used in the application view
- application.properties contains the main Spring Boot configuration: Application name, SQLite database connection, JDBC driver configuration, SQL initialization settings

The application contains different features
1. Healthcare professional
 - configure the threshold parameters
 - review the patients reports
 - handle the patient alerts
 - see the patients progress over time
2. Patient
- fill out daily wellbeing reports
- see progress over time
- see gamification status
- see unlocked and locked achievements

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

To execute the app:
Repository of github:
The application will be available in: http://localhost:8080
