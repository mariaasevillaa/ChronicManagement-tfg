PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
INSERT IGNORE INTO users VALUES(2,'maria@gmail.com','1234','patient');
INSERT INTO users VALUES(3,'marta@gmail.com','1234','patient');
INSERT INTO users VALUES(4,'fernando@gmail.com','1234','patient');
INSERT INTO users VALUES(5,'lola.sanchez@hospital.com','1234','DOCTOR');
INSERT INTO users VALUES(6,'raul.martinez@hospital.com','1234','DOCTOR');
INSERT INTO users VALUES(7,'lucia@gmail.com','1234','patient');
INSERT INTO users VALUES(8,'juan@gmail.com','1234','patient');
INSERT INTO users VALUES(9,'marta2@gmail.com','1234','patient');
INSERT INTO users VALUES(10,'jaime@gmail.com','1234','patient');
INSERT INTO users VALUES(11,'jordi@gmail.com','1234','patient');
INSERT INTO users VALUES(12,'paula@gmail.com','1234','patient');
INSERT INTO users VALUES(13,'beltraleku@gmail.com','beltranmono','patient');
INSERT INTO users VALUES(14,'maria2@gmail.com','$2a$10$2aVpDNW2MriRTmNP8TtVxOt1h7TDGZ98GRfYIe8/yE6N0PeS1W5Ty','patient');
INSERT INTO users VALUES(15,'jaime.martinez@hospital.com','1234','DOCTOR');
INSERT INTO users VALUES(16,'mercedes@gmail.com','1234','patient');
INSERT INTO users VALUES(17,'curro@gmail.com','$2a$10$YzhsPPh1jrTbrQsCvUgJNeq4M4gTsrHRdZ1M9oVNt6t/AfHP0Sn0S','patient');
INSERT INTO users VALUES(18,'martina@hospital.com','$2a$10$Yxp/dDedoxObHauk7KD6GeHjUuccMSQn645ocgDFi2.cDuxiSQzXS','DOCTOR');
INSERT INTO users VALUES(19,'mercedes1604@gmail.com','$2a$10$WhOdYoj45nPcpR2mLMgI7em5hCJxCy8aNGaBJCmUinyOOsZRynX42','patient');
CREATE TABLE IF NOT EXISTS "daily_reports" (
CREATE TABLE IF NOT EXISTS "patient" (
                                         "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
                                         "user_id"	INTEGER NOT NULL UNIQUE,
                                         "name"	TEXT,
                                         "surname"	TEXT,
                                         "date_of_birth"	TEXT,
                                         "chronic_condition"	TEXT,
                                         "diagnosis_date"	TEXT,
                                         FOREIGN KEY("user_id") REFERENCES "users"("id")
);
INSERT INTO patient VALUES(2,2,'Maria','Sevilla','2004-03-04','Diabetes','2026-06-28');
INSERT INTO patient VALUES(3,3,'Marta','Ruiz','2004-03-04','diabetes','2022-06-28');
INSERT INTO patient VALUES(4,4,'Fernando','demedina','2004-03-04','diabetes','2022-06-28');
INSERT INTO patient VALUES(5,7,'lucia','ramirez','2000-02-02','hearth','2026-04-01');
INSERT INTO patient VALUES(6,8,'Juan','Gomez','2006-02-27','Cancer','2026-04-09');
INSERT INTO patient VALUES(7,9,'Marta','Diez','2003-11-06','Diabetes','2026-04-15');
INSERT INTO patient VALUES(8,10,'jaime','lopez','2007-07-04','Cancer','2026-05-01');
INSERT INTO patient VALUES(9,11,'jordi','lol','2009-01-04','hearth','2026-05-04');
INSERT INTO patient VALUES(10,12,'paula','pipu','2008-01-01','Diabetes','2026-05-04');
INSERT INTO patient VALUES(11,13,'Beltrán','Gónzalez Lecuona','2004-03-28','Hipermelanización','2020-03-13');
INSERT INTO patient VALUES(12,14,'Maria','Romero','2019-05-25','Cancer','2026-05-25');
INSERT INTO patient VALUES(13,16,'Mercedes','sanchez','2004-01-20','Cancer','2026-05-13');
INSERT INTO patient VALUES(14,17,'Curro','Jimenez ','2008-10-15','Diabetes','2026-05-20');
INSERT INTO patient VALUES(15,19,'Mercedes','Romero','1971-04-16','tiroides','2026-05-06');
CREATE TABLE IF NOT EXISTS "health_professional" (
                                                     "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
                                                     "user_id"	INTEGER NOT NULL UNIQUE,
                                                     "name"	TEXT,
                                                     "surname"	TEXT,
                                                     "speciality"	TEXT,
                                                     FOREIGN KEY("user_id") REFERENCES "users"("id")
);
INSERT INTO health_professional VALUES(1,5,'Lola','Sanchez','Cardiology');
INSERT INTO health_professional VALUES(2,6,'Raul','Martniez','Endocrinologist');
INSERT INTO health_professional VALUES(3,15,'Jaime','Matinez','Cardiology');
INSERT INTO health_professional VALUES(4,18,'Martina','Rodriguez','Cardiology');
CREATE TABLE IF NOT EXISTS "hp_patient" (
                                            "hp_id"	INTEGER NOT NULL,
                                            "patient_id"	INTEGER NOT NULL,
                                            FOREIGN KEY("patient_id") REFERENCES "patient"("id"),
                                            PRIMARY KEY("hp_id","patient_id"),
                                            FOREIGN KEY("hp_id") REFERENCES "health_professional"("id")
);
INSERT INTO hp_patient VALUES(1,2);
INSERT INTO hp_patient VALUES(1,3);
INSERT INTO hp_patient VALUES(2,2);
INSERT INTO hp_patient VALUES(1,4);
INSERT INTO hp_patient VALUES(1,5);
INSERT INTO hp_patient VALUES(1,6);
INSERT INTO hp_patient VALUES(1,7);
INSERT INTO hp_patient VALUES(1,8);
INSERT INTO hp_patient VALUES(1,9);
INSERT INTO hp_patient VALUES(1,10);
INSERT INTO hp_patient VALUES(1,11);
INSERT INTO hp_patient VALUES(4,14);
INSERT INTO hp_patient VALUES(4,2);
CREATE TABLE IF NOT EXISTS "symptoms" (
                                          "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
                                          "name"	TEXT NOT NULL
);
INSERT INTO symptoms VALUES(1,'Fatigue');
INSERT INTO symptoms VALUES(2,'Headache');
INSERT INTO symptoms VALUES(3,'Dizziness');
INSERT INTO symptoms VALUES(4,'Nausea');
INSERT INTO symptoms VALUES(5,'Vomiting');
INSERT INTO symptoms VALUES(6,'Fever');
INSERT INTO symptoms VALUES(7,'Chills');
INSERT INTO symptoms VALUES(8,'Muscle pain');
INSERT INTO symptoms VALUES(9,'Joint pain');
INSERT INTO symptoms VALUES(10,'Abdominal pain');
INSERT INTO symptoms VALUES(11,'Chest pain');
INSERT INTO symptoms VALUES(12,'Shortness of breath');
INSERT INTO symptoms VALUES(13,'Cough');
INSERT INTO symptoms VALUES(14,'Palpitations');
INSERT INTO symptoms VALUES(15,'Swelling (edema)');
INSERT INTO symptoms VALUES(16,'Sleep problems');
INSERT INTO symptoms VALUES(17,'Insomnia');
INSERT INTO symptoms VALUES(18,'Excessive sleepiness');
INSERT INTO symptoms VALUES(19,'Weakness');
INSERT INTO symptoms VALUES(20,'Diarrhea');
INSERT INTO symptoms VALUES(21,'Constipation');
INSERT INTO symptoms VALUES(22,'Loss of appetite');
INSERT INTO symptoms VALUES(23,'Anxiety');
INSERT INTO symptoms VALUES(24,'Low mood');
INSERT INTO symptoms VALUES(25,'Irritability');
INSERT INTO symptoms VALUES(26,'Difficulty concentrating');
INSERT INTO symptoms VALUES(27,'High blood sugar symptoms');
INSERT INTO symptoms VALUES(28,'Low blood sugar symptoms');
INSERT INTO symptoms VALUES(29,'High blood pressure symptoms');
INSERT INTO symptoms VALUES(30,'Low blood pressure symptoms');
INSERT INTO symptoms VALUES(31,'Other');
CREATE TABLE IF NOT EXISTS "gamification_status" (
                                                     "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
                                                     "patient_id"	INTEGER,
                                                     "points"	INTEGER DEFAULT 0,
                                                     "streak_days"	INTEGER DEFAULT 0
);
INSERT INTO gamification_status VALUES(3,2,70,0);
INSERT INTO gamification_status VALUES(4,3,80,0);
INSERT INTO gamification_status VALUES(5,4,20,0);
INSERT INTO gamification_status VALUES(6,5,10,0);
INSERT INTO gamification_status VALUES(7,7,10,0);
INSERT INTO gamification_status VALUES(8,6,10,0);
INSERT INTO gamification_status VALUES(9,8,40,0);
INSERT INTO gamification_status VALUES(10,9,80,0);
INSERT INTO gamification_status VALUES(11,10,10,0);
INSERT INTO gamification_status VALUES(12,11,10,0);
INSERT INTO gamification_status VALUES(13,15,10,0);
CREATE TABLE IF NOT EXISTS "patient_achievements" (
                                                      "patient_id"	INTEGER NOT NULL,
                                                      "achievement_id"	INTEGER NOT NULL,
                                                      FOREIGN KEY("patient_id") REFERENCES "patient"("id"),
                                                      PRIMARY KEY("patient_id","achievement_id"),
                                                      FOREIGN KEY("achievement_id") REFERENCES "achievements"("id")
);
INSERT INTO patient_achievements VALUES(4,1);
INSERT INTO patient_achievements VALUES(5,1);
INSERT INTO patient_achievements VALUES(3,1);
INSERT INTO patient_achievements VALUES(3,2);
INSERT INTO patient_achievements VALUES(7,1);
INSERT INTO patient_achievements VALUES(6,1);
INSERT INTO patient_achievements VALUES(8,1);
INSERT INTO patient_achievements VALUES(9,1);
INSERT INTO patient_achievements VALUES(9,2);
INSERT INTO patient_achievements VALUES(10,1);
INSERT INTO patient_achievements VALUES(11,1);
INSERT INTO patient_achievements VALUES(2,1);
INSERT INTO patient_achievements VALUES(2,2);
INSERT INTO patient_achievements VALUES(15,1);
CREATE TABLE IF NOT EXISTS "daily_reports" (
                                               "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
                                               "patient_id"	INTEGER,
                                               "mood"	INTEGER,
                                               "medication_taken"	INTEGER,
                                               "notes"	TEXT,
                                               "date"	TEXT
);
INSERT INTO daily_reports VALUES(16,2,3,1,'i am sad','2026-06-17');
INSERT INTO daily_reports VALUES(17,3,1,1,'i am sad','2026-06-16');
INSERT INTO daily_reports VALUES(18,2,3,1,'nothing','2026-06-20');
INSERT INTO daily_reports VALUES(19,3,4,1,'nothing','2026-06-20');
INSERT INTO daily_reports VALUES(20,2,3,1,'nothing','2026-06-23');
INSERT INTO daily_reports VALUES(21,2,5,1,'nothing','2026-08-17');
INSERT INTO daily_reports VALUES(22,2,4,1,'nothing','2026-08-18');
INSERT INTO daily_reports VALUES(23,4,2,0,'nothing','2026-06-17');
INSERT INTO daily_reports VALUES(24,5,2,0,'nothing','2026-06-17');
INSERT INTO daily_reports VALUES(25,3,2,0,'i am happy','2026-04-23');
INSERT INTO daily_reports VALUES(26,3,2,1,'nothing','2026-04-24');
INSERT INTO daily_reports VALUES(27,3,1,1,'nothing','2026-04-27');
INSERT INTO daily_reports VALUES(28,7,2,0,'i am very bad','2026-04-29');
INSERT INTO daily_reports VALUES(29,3,3,1,'nothing','2026-04-30');
INSERT INTO daily_reports VALUES(30,6,3,0,'nothing','2026-05-03');
INSERT INTO daily_reports VALUES(31,8,3,0,'nothing','2026-05-04');
INSERT INTO daily_reports VALUES(32,8,1,0,'nothing','2026-05-03');
INSERT INTO daily_reports VALUES(33,9,3,1,'i am sad','2026-05-04');
INSERT INTO daily_reports VALUES(34,9,4,0,'i am happy','2026-05-05');
INSERT INTO daily_reports VALUES(35,8,5,0,'nothing','2026-05-05');
INSERT INTO daily_reports VALUES(36,9,5,0,'lol','2026-05-06');
INSERT INTO daily_reports VALUES(37,9,5,0,'i am sad','2026-05-07');
INSERT INTO daily_reports VALUES(38,9,5,1,'lol','2026-05-05');
INSERT INTO daily_reports VALUES(39,10,2,1,'le','2026-05-05');
INSERT INTO daily_reports VALUES(40,11,3,0,'Kind of stressed','2026-05-13');
INSERT INTO daily_reports VALUES(41,9,5,1,'nothing','2026-05-14');
INSERT INTO daily_reports VALUES(42,9,5,0,'nothing','2026-05-14');
INSERT INTO daily_reports VALUES(43,3,5,0,'3','2026-05-14');
INSERT INTO daily_reports VALUES(44,3,1,1,'4','2026-05-14');
INSERT INTO daily_reports VALUES(45,8,5,1,'le','2026-05-14');
INSERT INTO daily_reports VALUES(46,2,2,1,'4','2026-05-14');
INSERT INTO daily_reports VALUES(47,4,2,1,'lol','2026-05-14');
INSERT INTO daily_reports VALUES(48,2,1,0,'i am happy','2026-05-15');
INSERT INTO daily_reports VALUES(49,9,4,1,'yes','2026-05-25');
INSERT INTO daily_reports VALUES(50,15,1,1,'que hago','2026-05-26');
CREATE TABLE IF NOT EXISTS "alerts" (
                                        "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
                                        "patient_id"	INTEGER,
                                        "message"	TEXT,
                                        "date"	TEXT,
                                        "resolved"	INTEGER DEFAULT 0, type TEXT,
                                        FOREIGN KEY("patient_id") REFERENCES "patient"("id")
);
INSERT INTO alerts VALUES(2,3,'Patient reported low_mood','2026-06-16',0,'LOW_MOOD');
INSERT INTO alerts VALUES(3,4,'Patient reported low_mood','2026-06-17',0,'LOW_MOOD');
INSERT INTO alerts VALUES(4,5,'Patient reported low mood','2026-06-17',1,'LOW_MOOD');
INSERT INTO alerts VALUES(5,5,'Patient hasn''t taken the medication','2026-06-17',1,'NO_MEDICATION');
INSERT INTO alerts VALUES(6,3,'Patient hasn''t taken the medication','2026-04-23',0,'NO_MEDICATION');
INSERT INTO alerts VALUES(12,7,'Patient reported low mood','2026-04-29',1,'LOW_MOOD');
INSERT INTO alerts VALUES(13,7,'Patient hasn''t taken the medication','2026-04-29',0,'NO_MEDICATION');
INSERT INTO alerts VALUES(17,7,'Patient has not submitted reports for 4 days','2026-05-03',0,'MISSED_REPORT');
INSERT INTO alerts VALUES(18,7,'Patient has not submitted daily reports','2026-05-03',0,'MISSED_REPORT');
INSERT INTO alerts VALUES(22,6,'Patient has not submitted any reports yet','2026-05-03',1,'MISSED_REPORT');
INSERT INTO alerts VALUES(23,7,'Patient has not submitted daily reports','2026-05-03',0,'MISSED_REPORT');
INSERT INTO alerts VALUES(24,6,'Patient hasn''t taken the medication','2026-05-03',0,'NO_MEDICATION');
INSERT INTO alerts VALUES(25,8,'Patient has not submitted any reports yet','2026-05-04',1,'MISSED_REPORT');
INSERT INTO alerts VALUES(26,8,'Patient hasn''t taken the medication','2026-05-04',0,'NO_MEDICATION');
INSERT INTO alerts VALUES(27,8,'Patient reported low mood','2026-05-03',0,'LOW_MOOD');
INSERT INTO alerts VALUES(28,8,'Patient hasn''t taken the medication','2026-05-03',0,'NO_MEDICATION');
INSERT INTO alerts VALUES(29,6,'Patient has not submitted reports for 2 days','2026-05-05',0,'MISSED_REPORT');
INSERT INTO alerts VALUES(30,8,'Patient has not submitted daily reports','2026-05-05',1,'MISSED_REPORT');
INSERT INTO alerts VALUES(31,9,'Patient hasn''t taken the medication','2026-05-05',1,'NO_MEDICATION');
INSERT INTO alerts VALUES(32,9,'Patient has not submitted daily reports','2026-05-05',1,'MISSED_REPORT');
INSERT INTO alerts VALUES(33,8,'Patient hasn''t taken the medication','2026-05-05',0,'NO_MEDICATION');
INSERT INTO alerts VALUES(34,9,'Patient hasn''t taken the medication','2026-05-07',0,'NO_MEDICATION');
INSERT INTO alerts VALUES(35,9,'Patient reported low mood','2026-05-05',1,'LOW_MOOD');
INSERT INTO alerts VALUES(36,10,'Patient has not submitted any reports yet','2026-05-05',1,'MISSED_REPORT');
INSERT INTO alerts VALUES(37,10,'Patient reported low mood','2026-05-05',0,'LOW_MOOD');
INSERT INTO alerts VALUES(38,10,'Patient has not submitted daily reports for 1 days','2026-05-06',0,'MISSED_REPORT');
INSERT INTO alerts VALUES(39,11,'Patient hasn''t taken the medication','2026-05-13',1,'NO_MEDICATION');
INSERT INTO alerts VALUES(40,9,'Patient reported low mood','2026-05-14',1,'LOW_MOOD');
INSERT INTO alerts VALUES(41,9,'Patient reported low mood','2026-05-14',1,'LOW_MOOD');
INSERT INTO alerts VALUES(42,9,'Patient hasn''t taken the medication','2026-05-14',0,'NO_MEDICATION');
INSERT INTO alerts VALUES(43,3,'Patient reported low mood','2026-05-14',0,'LOW_MOOD');
INSERT INTO alerts VALUES(44,2,'Patient reported low mood','2026-05-14',1,'LOW_MOOD');
INSERT INTO alerts VALUES(45,4,'Patient reported low mood','2026-05-14',1,'LOW_MOOD');
INSERT INTO alerts VALUES(46,11,'Patient has not submitted daily reports for 1 days','2026-05-14',0,'MISSED_REPORT');
INSERT INTO alerts VALUES(47,2,'Patient reported low mood','2026-05-15',0,'LOW_MOOD');
INSERT INTO alerts VALUES(48,8,'Patient has not submitted daily reports for 4 days','2026-05-18',0,'MISSED_REPORT');
INSERT INTO alerts VALUES(49,9,'Patient has not submitted daily reports for 11 days','2026-05-25',1,'MISSED_REPORT');
INSERT INTO alerts VALUES(50,9,'Patient reported low mood','2026-05-25',0,'LOW_MOOD');
INSERT INTO alerts VALUES(51,14,'Patient has not submitted any reports yet','2026-05-27',0,'MISSED_REPORT');
INSERT INTO alerts VALUES(52,15,'Patient reported low mood','2026-05-26',0,'LOW_MOOD');
CREATE TABLE IF NOT EXISTS "monitoring_parameters" (
                                                       "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
                                                       "patient_id"	INTEGER,
                                                       "mood_threshold"	INTEGER,
                                                       "missed_medication_days"	INTEGER,
                                                       "missed_reports_days"	INTEGER,
                                                       FOREIGN KEY("patient_id") REFERENCES "patient"("id")
);
INSERT INTO monitoring_parameters VALUES(1,6,2,2,1);
INSERT INTO monitoring_parameters VALUES(2,3,2,2,1);
INSERT INTO monitoring_parameters VALUES(3,5,2,2,1);
INSERT INTO monitoring_parameters VALUES(4,7,2,2,1);
INSERT INTO monitoring_parameters VALUES(5,2,2,2,1);
INSERT INTO monitoring_parameters VALUES(6,4,2,2,1);
INSERT INTO monitoring_parameters VALUES(7,8,1,0,2);
INSERT INTO monitoring_parameters VALUES(8,9,5,1,1);
INSERT INTO monitoring_parameters VALUES(9,10,2,2,1);
INSERT INTO monitoring_parameters VALUES(10,11,2,2,1);
INSERT INTO monitoring_parameters VALUES(11,14,2,2,1);
CREATE TABLE IF NOT EXISTS "daily_symptoms" (
                                                "daily_id"	INTEGER NOT NULL,
                                                "symptoms_id"	INTEGER NOT NULL,
                                                FOREIGN KEY("symptoms_id") REFERENCES "symptoms"("id"),
                                                FOREIGN KEY("daily_id") REFERENCES "daily_reports"("patient_id")
);
INSERT INTO daily_symptoms VALUES(16,4);
INSERT INTO daily_symptoms VALUES(16,17);
INSERT INTO daily_symptoms VALUES(16,22);
INSERT INTO daily_symptoms VALUES(17,1);
INSERT INTO daily_symptoms VALUES(17,30);
INSERT INTO daily_symptoms VALUES(18,13);
INSERT INTO daily_symptoms VALUES(19,17);
INSERT INTO daily_symptoms VALUES(20,5);
INSERT INTO daily_symptoms VALUES(20,18);
INSERT INTO daily_symptoms VALUES(21,11);
INSERT INTO daily_symptoms VALUES(22,14);
INSERT INTO daily_symptoms VALUES(23,1);
INSERT INTO daily_symptoms VALUES(23,2);
INSERT INTO daily_symptoms VALUES(23,4);
INSERT INTO daily_symptoms VALUES(23,8);
INSERT INTO daily_symptoms VALUES(23,9);
INSERT INTO daily_symptoms VALUES(23,13);
INSERT INTO daily_symptoms VALUES(23,21);
INSERT INTO daily_symptoms VALUES(23,23);
INSERT INTO daily_symptoms VALUES(23,24);
INSERT INTO daily_symptoms VALUES(23,26);
INSERT INTO daily_symptoms VALUES(23,31);
INSERT INTO daily_symptoms VALUES(24,8);
INSERT INTO daily_symptoms VALUES(24,14);
INSERT INTO daily_symptoms VALUES(25,1);
INSERT INTO daily_symptoms VALUES(25,11);
INSERT INTO daily_symptoms VALUES(25,24);
INSERT INTO daily_symptoms VALUES(26,8);
INSERT INTO daily_symptoms VALUES(27,1);
INSERT INTO daily_symptoms VALUES(27,2);
INSERT INTO daily_symptoms VALUES(28,1);
INSERT INTO daily_symptoms VALUES(28,2);
INSERT INTO daily_symptoms VALUES(28,20);
INSERT INTO daily_symptoms VALUES(28,23);
INSERT INTO daily_symptoms VALUES(29,9);
INSERT INTO daily_symptoms VALUES(29,16);
INSERT INTO daily_symptoms VALUES(30,1);
INSERT INTO daily_symptoms VALUES(30,3);
INSERT INTO daily_symptoms VALUES(31,3);
INSERT INTO daily_symptoms VALUES(32,5);
INSERT INTO daily_symptoms VALUES(32,14);
INSERT INTO daily_symptoms VALUES(33,26);
INSERT INTO daily_symptoms VALUES(34,1);
INSERT INTO daily_symptoms VALUES(35,1);
INSERT INTO daily_symptoms VALUES(36,1);
INSERT INTO daily_symptoms VALUES(36,2);
INSERT INTO daily_symptoms VALUES(37,14);
INSERT INTO daily_symptoms VALUES(38,1);
INSERT INTO daily_symptoms VALUES(39,2);
INSERT INTO daily_symptoms VALUES(40,1);
INSERT INTO daily_symptoms VALUES(40,20);
INSERT INTO daily_symptoms VALUES(40,23);
INSERT INTO daily_symptoms VALUES(40,31);
INSERT INTO daily_symptoms VALUES(41,8);
INSERT INTO daily_symptoms VALUES(42,2);
INSERT INTO daily_symptoms VALUES(43,2);
INSERT INTO daily_symptoms VALUES(44,3);
INSERT INTO daily_symptoms VALUES(45,13);
INSERT INTO daily_symptoms VALUES(46,8);
INSERT INTO daily_symptoms VALUES(47,9);
INSERT INTO daily_symptoms VALUES(48,13);
INSERT INTO daily_symptoms VALUES(48,16);
INSERT INTO daily_symptoms VALUES(48,28);
INSERT INTO daily_symptoms VALUES(49,5);
INSERT INTO daily_symptoms VALUES(50,16);
CREATE TABLE IF NOT EXISTS "achievements" (
                                              "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
                                              "name"	TEXT,
                                              "description"	TEXT,
                                              "points_reward"	INTEGER,
                                              "reports_needed"	INTEGER
);
INSERT INTO achievements VALUES(1,'First Steps',replace('Submit your first report\n','\n',char(10)),10,1);
INSERT INTO achievements VALUES(2,'Getting Started','Sumbit 5 reports',20,5);
INSERT INTO achievements VALUES(3,'Consistent User','Submit 10 reports',30,10);
INSERT INTO achievements VALUES(4,'Commited Patient','Submit 20 reports',50,20);
INSERT INTO achievements VALUES(5,'Health Master','Submit 50 reports',100,50);
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('users',19);
INSERT INTO sqlite_sequence VALUES('patient',15);
INSERT INTO sqlite_sequence VALUES('daily_reports',50);
INSERT INTO sqlite_sequence VALUES('symptoms',31);
INSERT INTO sqlite_sequence VALUES('gamification_status',13);
INSERT INTO sqlite_sequence VALUES('alerts',52);
INSERT INTO sqlite_sequence VALUES('achievements',5);
INSERT INTO sqlite_sequence VALUES('health_professional',4);
INSERT INTO sqlite_sequence VALUES('monitoring_parameters',11);
COMMIT;