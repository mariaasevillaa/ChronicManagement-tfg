CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS patient (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       user_id INT NOT NULL UNIQUE,
                                       name VARCHAR(255),
    surname VARCHAR(255),
    date_of_birth VARCHAR(50),
    chronic_condition VARCHAR(255),
    diagnosis_date VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS health_professional (
                                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                                   user_id INT NOT NULL UNIQUE,
                                                   name VARCHAR(255),
    surname VARCHAR(255),
    speciality VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS hp_patient (
                                          hp_id INT NOT NULL,
                                          patient_id INT NOT NULL,
                                          PRIMARY KEY (hp_id, patient_id),
    FOREIGN KEY (hp_id) REFERENCES health_professional(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

CREATE TABLE IF NOT EXISTS symptoms (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS achievements (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            name VARCHAR(255),
    description TEXT,
    points_reward INT,
    reports_needed INT
    );

CREATE TABLE IF NOT EXISTS gamification_status (
                                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                                   patient_id INT,
                                                   points INT DEFAULT 0,
                                                   streak_days INT DEFAULT 0,
                                                   FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

CREATE TABLE IF NOT EXISTS patient_achievements (
                                                    patient_id INT NOT NULL,
                                                    achievement_id INT NOT NULL,
                                                    PRIMARY KEY (patient_id, achievement_id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (achievement_id) REFERENCES achievements(id)
    );

CREATE TABLE IF NOT EXISTS daily_reports (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             patient_id INT,
                                             mood INT,
                                             medication_taken INT,
                                             notes TEXT,
                                             date VARCHAR(50),
    FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

CREATE TABLE IF NOT EXISTS alerts (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      patient_id INT,
                                      message TEXT,
                                      date VARCHAR(50),
    resolved INT DEFAULT 0,
    type VARCHAR(100),
    FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

CREATE TABLE IF NOT EXISTS monitoring_parameters (
                                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                                     patient_id INT,
                                                     mood_threshold INT,
                                                     missed_medication_days INT,
                                                     missed_reports_days INT,
                                                     FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

CREATE TABLE IF NOT EXISTS daily_symptoms (
                                              daily_id INT NOT NULL,
                                              symptoms_id INT NOT NULL,
                                              PRIMARY KEY (daily_id, symptoms_id),
    FOREIGN KEY (daily_id) REFERENCES daily_reports(id),
    FOREIGN KEY (symptoms_id) REFERENCES symptoms(id)
    );