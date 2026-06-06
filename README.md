# Patient monitoring and gamification system

Web application design using Spring Boot and Thymeleaf focused on healthcare monitoring and patient engagement.
The platform allows interaction between patients and doctors, facilitating medical follow-up and encouraging patient engagement through gamification techniques.
The application uses a relational database hosted on Railway for data storage and management.

### Technologies used
- Java
- CSS
- HTML
- Thymeleaf
- MySQL
- Railway Database
- Maven

### The project source code (src folder) is structured as follow

#### src/main/java
- The model package contains the main entities and classes: Achievement, Alert, DailyReportSymptom, DailyReport, GamificationStatus, HealthProfessional, HpPatient, MonitoringParameter, Patient, PatientAchievement, Symptom, User.
- The controllers package handles HTTP requests and navigation through views.
- The repositories packages implements the methods defined in the interfaces: JDBCAchievementsManager, JDBCAlertsManager, JDBCDailyReportManager, JDBCDailySymptomsManager, JDBCGamificationManager, JDBCHealthcareManagement, JDBCHpPatientManager, JDBCMonitoringParametersManager, JDBCPatientAchievementsManager, JDBCPatientManager, JDBCSymptomsManager, JDBCUserManager

#### src/main/resources
- The static/images folder contains all the photos and icons used and the css
- The templates folder contains the Thymeleaf HTML file used in the application view
- application.properties contains the main Spring Boot configuration: Application name, SQLite database connection, JDBC driver configuration, SQL initialization settings

### Features of the application
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

### The database stores information related to:
- Patients
- Healthcare professional
- Different achievements
- Gamification status
- Alerts
- Patient Symptoms
- Users role
- Daily reports
- Monitoring parameters

### Security considerations
The application includes basic security measures such as role-based access, session management and encrypted password storage.

#### Requirements for local execution
- Java 17+
- Maven
- MySQLite
- IntelliJ IDEA or Eclipse
  
### Installation and execution
This project is currently configured for deployment using Railway. The application uses a relational database hosted on Railway, and the database connection is managed through environment variables.

For local execution, it would be necessary to configure a local compatible database and update the corresponding values in `application.properties`.
The deployed version of the application can be accessed at: https://chronicmanagement-tfg-production.up.railway.app


### Disclaimer
This project was developed for academic and educational purposes as part of a Bachelor Thesis in Biomedical Engineering.
The platform is intended to support patient self-monitoring and medical follow-up, but it is not intended to replace professional medical judgement, clinical diagnosis or medical treatment.
