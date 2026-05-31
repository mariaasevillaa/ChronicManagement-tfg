-- data.sql limpio para MySQL/Railway
-- Solo inserta datos. Las tablas deben estar creadas previamente por schema.sql.

-- users
INSERT IGNORE INTO users VALUES(2,'maria@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(3,'marta@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(4,'fernando@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(5,'lola.sanchez@hospital.com','1234','DOCTOR');
INSERT IGNORE INTO users VALUES(6,'raul.martinez@hospital.com','1234','DOCTOR');
INSERT IGNORE INTO users VALUES(7,'lucia@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(8,'juan@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(9,'marta2@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(10,'jaime@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(11,'jordi@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(12,'paula@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(13,'beltraleku@gmail.com','beltranmono','patient');
INSERT IGNORE INTO users VALUES(14,'maria2@gmail.com','$2a$10$2aVpDNW2MriRTmNP8TtVxOt1h7TDGZ98GRfYIe8/yE6N0PeS1W5Ty','patient');
INSERT IGNORE INTO users VALUES(15,'jaime.martinez@hospital.com','1234','DOCTOR');
INSERT IGNORE INTO users VALUES(16,'mercedes@gmail.com','1234','patient');
INSERT IGNORE INTO users VALUES(17,'curro@gmail.com','$2a$10$YzhsPPh1jrTbrQsCvUgJNeq4M4gTsrHRdZ1M9oVNt6t/AfHP0Sn0S','patient');
INSERT IGNORE INTO users VALUES(18,'martina@hospital.com','$2a$10$Yxp/dDedoxObHauk7KD6GeHjUuccMSQn645ocgDFi2.cDuxiSQzXS','DOCTOR');
INSERT IGNORE INTO users VALUES(19,'mercedes1604@gmail.com','$2a$10$WhOdYoj45nPcpR2mLMgI7em5hCJxCy8aNGaBJCmUinyOOsZRynX42','patient');

-- patient
INSERT IGNORE INTO patient VALUES(2,2,'Maria','Sevilla','2004-03-04','Diabetes','2026-06-28');
INSERT IGNORE INTO patient VALUES(3,3,'Marta','Ruiz','2004-03-04','diabetes','2022-06-28');
INSERT IGNORE INTO patient VALUES(4,4,'Fernando','demedina','2004-03-04','diabetes','2022-06-28');
INSERT IGNORE INTO patient VALUES(5,7,'lucia','ramirez','2000-02-02','hearth','2026-04-01');
INSERT IGNORE INTO patient VALUES(6,8,'Juan','Gomez','2006-02-27','Cancer','2026-04-09');
INSERT IGNORE INTO patient VALUES(7,9,'Marta','Diez','2003-11-06','Diabetes','2026-04-15');
INSERT IGNORE INTO patient VALUES(8,10,'jaime','lopez','2007-07-04','Cancer','2026-05-01');
INSERT IGNORE INTO patient VALUES(9,11,'jordi','lol','2009-01-04','hearth','2026-05-04');
INSERT IGNORE INTO patient VALUES(10,12,'paula','pipu','2008-01-01','Diabetes','2026-05-04');
INSERT IGNORE INTO patient VALUES(11,13,'Beltrán','Gónzalez Lecuona','2004-03-28','Hipermelanización','2020-03-13');
INSERT IGNORE INTO patient VALUES(12,14,'Maria','Romero','2019-05-25','Cancer','2026-05-25');
INSERT IGNORE INTO patient VALUES(13,16,'Mercedes','sanchez','2004-01-20','Cancer','2026-05-13');
INSERT IGNORE INTO patient VALUES(14,17,'Curro','Jimenez ','2008-10-15','Diabetes','2026-05-20');
INSERT IGNORE INTO patient VALUES(15,19,'Mercedes','Romero','1971-04-16','tiroides','2026-05-06');

-- health_professional
INSERT IGNORE INTO health_professional VALUES(1,5,'Lola','Sanchez','Cardiology');
INSERT IGNORE INTO health_professional VALUES(2,6,'Raul','Martniez','Endocrinologist');
INSERT IGNORE INTO health_professional VALUES(3,15,'Jaime','Matinez','Cardiology');
INSERT IGNORE INTO health_professional VALUES(4,18,'Martina','Rodriguez','Cardiology');

-- symptoms
INSERT IGNORE INTO symptoms VALUES(1,'Fatigue');
INSERT IGNORE INTO symptoms VALUES(2,'Headache');
INSERT IGNORE INTO symptoms VALUES(3,'Dizziness');
INSERT IGNORE INTO symptoms VALUES(4,'Nausea');
INSERT IGNORE INTO symptoms VALUES(5,'Vomiting');
INSERT IGNORE INTO symptoms VALUES(6,'Fever');
INSERT IGNORE INTO symptoms VALUES(7,'Chills');
INSERT IGNORE INTO symptoms VALUES(8,'Muscle pain');
INSERT IGNORE INTO symptoms VALUES(9,'Joint pain');
INSERT IGNORE INTO symptoms VALUES(10,'Abdominal pain');
INSERT IGNORE INTO symptoms VALUES(11,'Chest pain');
INSERT IGNORE INTO symptoms VALUES(12,'Shortness of breath');
INSERT IGNORE INTO symptoms VALUES(13,'Cough');
INSERT IGNORE INTO symptoms VALUES(14,'Palpitations');
INSERT IGNORE INTO symptoms VALUES(15,'Swelling (edema)');
INSERT IGNORE INTO symptoms VALUES(16,'Sleep problems');
INSERT IGNORE INTO symptoms VALUES(17,'Insomnia');
INSERT IGNORE INTO symptoms VALUES(18,'Excessive sleepiness');
INSERT IGNORE INTO symptoms VALUES(19,'Weakness');
INSERT IGNORE INTO symptoms VALUES(20,'Diarrhea');
INSERT IGNORE INTO symptoms VALUES(21,'Constipation');
INSERT IGNORE INTO symptoms VALUES(22,'Loss of appetite');
INSERT IGNORE INTO symptoms VALUES(23,'Anxiety');
INSERT IGNORE INTO symptoms VALUES(24,'Low mood');
INSERT IGNORE INTO symptoms VALUES(25,'Irritability');
INSERT IGNORE INTO symptoms VALUES(26,'Difficulty concentrating');
INSERT IGNORE INTO symptoms VALUES(27,'High blood sugar symptoms');
INSERT IGNORE INTO symptoms VALUES(28,'Low blood sugar symptoms');
INSERT IGNORE INTO symptoms VALUES(29,'High blood pressure symptoms');
INSERT IGNORE INTO symptoms VALUES(30,'Low blood pressure symptoms');
INSERT IGNORE INTO symptoms VALUES(31,'Other');

-- achievements
INSERT IGNORE INTO achievements VALUES(1,'First Steps','Submit your first report',10,1);
INSERT IGNORE INTO achievements VALUES(2,'Getting Started','Sumbit 5 reports',20,5);
INSERT IGNORE INTO achievements VALUES(3,'Consistent User','Submit 10 reports',30,10);
INSERT IGNORE INTO achievements VALUES(4,'Commited Patient','Submit 20 reports',50,20);
INSERT IGNORE INTO achievements VALUES(5,'Health Master','Submit 50 reports',100,50);

-- hp_patient
INSERT IGNORE INTO hp_patient VALUES(1,2);
INSERT IGNORE INTO hp_patient VALUES(1,3);
INSERT IGNORE INTO hp_patient VALUES(2,2);
INSERT IGNORE INTO hp_patient VALUES(1,4);
INSERT IGNORE INTO hp_patient VALUES(1,5);
INSERT IGNORE INTO hp_patient VALUES(1,6);
INSERT IGNORE INTO hp_patient VALUES(1,7);
INSERT IGNORE INTO hp_patient VALUES(1,8);
INSERT IGNORE INTO hp_patient VALUES(1,9);
INSERT IGNORE INTO hp_patient VALUES(1,10);
INSERT IGNORE INTO hp_patient VALUES(1,11);
INSERT IGNORE INTO hp_patient VALUES(4,14);
INSERT IGNORE INTO hp_patient VALUES(4,2);

-- gamification_status
INSERT IGNORE INTO gamification_status VALUES(3,2,70,0);
INSERT IGNORE INTO gamification_status VALUES(4,3,80,0);
INSERT IGNORE INTO gamification_status VALUES(5,4,20,0);
INSERT IGNORE INTO gamification_status VALUES(6,5,10,0);
INSERT IGNORE INTO gamification_status VALUES(7,7,10,0);
INSERT IGNORE INTO gamification_status VALUES(8,6,10,0);
INSERT IGNORE INTO gamification_status VALUES(9,8,40,0);
INSERT IGNORE INTO gamification_status VALUES(10,9,80,0);
INSERT IGNORE INTO gamification_status VALUES(11,10,10,0);
INSERT IGNORE INTO gamification_status VALUES(12,11,10,0);
INSERT IGNORE INTO gamification_status VALUES(13,15,10,0);

-- patient_achievements
INSERT IGNORE INTO patient_achievements VALUES(4,1);
INSERT IGNORE INTO patient_achievements VALUES(5,1);
INSERT IGNORE INTO patient_achievements VALUES(3,1);
INSERT IGNORE INTO patient_achievements VALUES(3,2);
INSERT IGNORE INTO patient_achievements VALUES(7,1);
INSERT IGNORE INTO patient_achievements VALUES(6,1);
INSERT IGNORE INTO patient_achievements VALUES(8,1);
INSERT IGNORE INTO patient_achievements VALUES(9,1);
INSERT IGNORE INTO patient_achievements VALUES(9,2);
INSERT IGNORE INTO patient_achievements VALUES(10,1);
INSERT IGNORE INTO patient_achievements VALUES(11,1);
INSERT IGNORE INTO patient_achievements VALUES(2,1);
INSERT IGNORE INTO patient_achievements VALUES(2,2);
INSERT IGNORE INTO patient_achievements VALUES(15,1);

-- daily_reports
INSERT IGNORE INTO daily_reports VALUES(16,2,3,1,'i am sad','2026-06-17');
INSERT IGNORE INTO daily_reports VALUES(17,3,1,1,'i am sad','2026-06-16');
INSERT IGNORE INTO daily_reports VALUES(18,2,3,1,'nothing','2026-06-20');
INSERT IGNORE INTO daily_reports VALUES(19,3,4,1,'nothing','2026-06-20');
INSERT IGNORE INTO daily_reports VALUES(20,2,3,1,'nothing','2026-06-23');
INSERT IGNORE INTO daily_reports VALUES(21,2,5,1,'nothing','2026-08-17');
INSERT IGNORE INTO daily_reports VALUES(22,2,4,1,'nothing','2026-08-18');
INSERT IGNORE INTO daily_reports VALUES(23,4,2,0,'nothing','2026-06-17');
INSERT IGNORE INTO daily_reports VALUES(24,5,2,0,'nothing','2026-06-17');
INSERT IGNORE INTO daily_reports VALUES(25,3,2,0,'i am happy','2026-04-23');
INSERT IGNORE INTO daily_reports VALUES(26,3,2,1,'nothing','2026-04-24');
INSERT IGNORE INTO daily_reports VALUES(27,3,1,1,'nothing','2026-04-27');
INSERT IGNORE INTO daily_reports VALUES(28,7,2,0,'i am very bad','2026-04-29');
INSERT IGNORE INTO daily_reports VALUES(29,3,3,1,'nothing','2026-04-30');
INSERT IGNORE INTO daily_reports VALUES(30,6,3,0,'nothing','2026-05-03');
INSERT IGNORE INTO daily_reports VALUES(31,8,3,0,'nothing','2026-05-04');
INSERT IGNORE INTO daily_reports VALUES(32,8,1,0,'nothing','2026-05-03');
INSERT IGNORE INTO daily_reports VALUES(33,9,3,1,'i am sad','2026-05-04');
INSERT IGNORE INTO daily_reports VALUES(34,9,4,0,'i am happy','2026-05-05');
INSERT IGNORE INTO daily_reports VALUES(35,8,5,0,'nothing','2026-05-05');
INSERT IGNORE INTO daily_reports VALUES(36,9,5,0,'lol','2026-05-06');
INSERT IGNORE INTO daily_reports VALUES(37,9,5,0,'i am sad','2026-05-07');
INSERT IGNORE INTO daily_reports VALUES(38,9,5,1,'lol','2026-05-05');
INSERT IGNORE INTO daily_reports VALUES(39,10,2,1,'le','2026-05-05');
INSERT IGNORE INTO daily_reports VALUES(40,11,3,0,'Kind of stressed','2026-05-13');
INSERT IGNORE INTO daily_reports VALUES(41,9,5,1,'nothing','2026-05-14');
INSERT IGNORE INTO daily_reports VALUES(42,9,5,0,'nothing','2026-05-14');
INSERT IGNORE INTO daily_reports VALUES(43,3,5,0,'3','2026-05-14');
INSERT IGNORE INTO daily_reports VALUES(44,3,1,1,'4','2026-05-14');
INSERT IGNORE INTO daily_reports VALUES(45,8,5,1,'le','2026-05-14');
INSERT IGNORE INTO daily_reports VALUES(46,2,2,1,'4','2026-05-14');
INSERT IGNORE INTO daily_reports VALUES(47,4,2,1,'lol','2026-05-14');
INSERT IGNORE INTO daily_reports VALUES(48,2,1,0,'i am happy','2026-05-15');
INSERT IGNORE INTO daily_reports VALUES(49,9,4,1,'yes','2026-05-25');
INSERT IGNORE INTO daily_reports VALUES(50,15,1,1,'que hago','2026-05-26');

-- alerts
INSERT IGNORE INTO alerts VALUES(2,3,'Patient reported low_mood','2026-06-16',0,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(3,4,'Patient reported low_mood','2026-06-17',0,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(4,5,'Patient reported low mood','2026-06-17',1,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(5,5,'Patient hasn''t taken the medication','2026-06-17',1,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(6,3,'Patient hasn''t taken the medication','2026-04-23',0,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(12,7,'Patient reported low mood','2026-04-29',1,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(13,7,'Patient hasn''t taken the medication','2026-04-29',0,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(17,7,'Patient has not submitted reports for 4 days','2026-05-03',0,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(18,7,'Patient has not submitted daily reports','2026-05-03',0,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(22,6,'Patient has not submitted any reports yet','2026-05-03',1,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(23,7,'Patient has not submitted daily reports','2026-05-03',0,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(24,6,'Patient hasn''t taken the medication','2026-05-03',0,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(25,8,'Patient has not submitted any reports yet','2026-05-04',1,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(26,8,'Patient hasn''t taken the medication','2026-05-04',0,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(27,8,'Patient reported low mood','2026-05-03',0,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(28,8,'Patient hasn''t taken the medication','2026-05-03',0,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(29,6,'Patient has not submitted reports for 2 days','2026-05-05',0,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(30,8,'Patient has not submitted daily reports','2026-05-05',1,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(31,9,'Patient hasn''t taken the medication','2026-05-05',1,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(32,9,'Patient has not submitted daily reports','2026-05-05',1,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(33,8,'Patient hasn''t taken the medication','2026-05-05',0,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(34,9,'Patient hasn''t taken the medication','2026-05-07',0,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(35,9,'Patient reported low mood','2026-05-05',1,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(36,10,'Patient has not submitted any reports yet','2026-05-05',1,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(37,10,'Patient reported low mood','2026-05-05',0,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(38,10,'Patient has not submitted daily reports for 1 days','2026-05-06',0,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(39,11,'Patient hasn''t taken the medication','2026-05-13',1,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(40,9,'Patient reported low mood','2026-05-14',1,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(41,9,'Patient reported low mood','2026-05-14',1,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(42,9,'Patient hasn''t taken the medication','2026-05-14',0,'NO_MEDICATION');
INSERT IGNORE INTO alerts VALUES(43,3,'Patient reported low mood','2026-05-14',0,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(44,2,'Patient reported low mood','2026-05-14',1,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(45,4,'Patient reported low mood','2026-05-14',1,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(46,11,'Patient has not submitted daily reports for 1 days','2026-05-14',0,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(47,2,'Patient reported low mood','2026-05-15',0,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(48,8,'Patient has not submitted daily reports for 4 days','2026-05-18',0,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(49,9,'Patient has not submitted daily reports for 11 days','2026-05-25',1,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(50,9,'Patient reported low mood','2026-05-25',0,'LOW_MOOD');
INSERT IGNORE INTO alerts VALUES(51,14,'Patient has not submitted any reports yet','2026-05-27',0,'MISSED_REPORT');
INSERT IGNORE INTO alerts VALUES(52,15,'Patient reported low mood','2026-05-26',0,'LOW_MOOD');

-- monitoring_parameters
INSERT IGNORE INTO monitoring_parameters VALUES(1,6,2,2,1);
INSERT IGNORE INTO monitoring_parameters VALUES(2,3,2,2,1);
INSERT IGNORE INTO monitoring_parameters VALUES(3,5,2,2,1);
INSERT IGNORE INTO monitoring_parameters VALUES(4,7,2,2,1);
INSERT IGNORE INTO monitoring_parameters VALUES(5,2,2,2,1);
INSERT IGNORE INTO monitoring_parameters VALUES(6,4,2,2,1);
INSERT IGNORE INTO monitoring_parameters VALUES(7,8,1,0,2);
INSERT IGNORE INTO monitoring_parameters VALUES(8,9,5,1,1);
INSERT IGNORE INTO monitoring_parameters VALUES(9,10,2,2,1);
INSERT IGNORE INTO monitoring_parameters VALUES(10,11,2,2,1);
INSERT IGNORE INTO monitoring_parameters VALUES(11,14,2,2,1);

-- daily_symptoms
INSERT IGNORE INTO daily_symptoms VALUES(16,4);
INSERT IGNORE INTO daily_symptoms VALUES(16,17);
INSERT IGNORE INTO daily_symptoms VALUES(16,22);
INSERT IGNORE INTO daily_symptoms VALUES(17,1);
INSERT IGNORE INTO daily_symptoms VALUES(17,30);
INSERT IGNORE INTO daily_symptoms VALUES(18,13);
INSERT IGNORE INTO daily_symptoms VALUES(19,17);
INSERT IGNORE INTO daily_symptoms VALUES(20,5);
INSERT IGNORE INTO daily_symptoms VALUES(20,18);
INSERT IGNORE INTO daily_symptoms VALUES(21,11);
INSERT IGNORE INTO daily_symptoms VALUES(22,14);
INSERT IGNORE INTO daily_symptoms VALUES(23,1);
INSERT IGNORE INTO daily_symptoms VALUES(23,2);
INSERT IGNORE INTO daily_symptoms VALUES(23,4);
INSERT IGNORE INTO daily_symptoms VALUES(23,8);
INSERT IGNORE INTO daily_symptoms VALUES(23,9);
INSERT IGNORE INTO daily_symptoms VALUES(23,13);
INSERT IGNORE INTO daily_symptoms VALUES(23,21);
INSERT IGNORE INTO daily_symptoms VALUES(23,23);
INSERT IGNORE INTO daily_symptoms VALUES(23,24);
INSERT IGNORE INTO daily_symptoms VALUES(23,26);
INSERT IGNORE INTO daily_symptoms VALUES(23,31);
INSERT IGNORE INTO daily_symptoms VALUES(24,8);
INSERT IGNORE INTO daily_symptoms VALUES(24,14);
INSERT IGNORE INTO daily_symptoms VALUES(25,1);
INSERT IGNORE INTO daily_symptoms VALUES(25,11);
INSERT IGNORE INTO daily_symptoms VALUES(25,24);
INSERT IGNORE INTO daily_symptoms VALUES(26,8);
INSERT IGNORE INTO daily_symptoms VALUES(27,1);
INSERT IGNORE INTO daily_symptoms VALUES(27,2);
INSERT IGNORE INTO daily_symptoms VALUES(28,1);
INSERT IGNORE INTO daily_symptoms VALUES(28,2);
INSERT IGNORE INTO daily_symptoms VALUES(28,20);
INSERT IGNORE INTO daily_symptoms VALUES(28,23);
INSERT IGNORE INTO daily_symptoms VALUES(29,9);
INSERT IGNORE INTO daily_symptoms VALUES(29,16);
INSERT IGNORE INTO daily_symptoms VALUES(30,1);
INSERT IGNORE INTO daily_symptoms VALUES(30,3);
INSERT IGNORE INTO daily_symptoms VALUES(31,3);
INSERT IGNORE INTO daily_symptoms VALUES(32,5);
INSERT IGNORE INTO daily_symptoms VALUES(32,14);
INSERT IGNORE INTO daily_symptoms VALUES(33,26);
INSERT IGNORE INTO daily_symptoms VALUES(34,1);
INSERT IGNORE INTO daily_symptoms VALUES(35,1);
INSERT IGNORE INTO daily_symptoms VALUES(36,1);
INSERT IGNORE INTO daily_symptoms VALUES(36,2);
INSERT IGNORE INTO daily_symptoms VALUES(37,14);
INSERT IGNORE INTO daily_symptoms VALUES(38,1);
INSERT IGNORE INTO daily_symptoms VALUES(39,2);
INSERT IGNORE INTO daily_symptoms VALUES(40,1);
INSERT IGNORE INTO daily_symptoms VALUES(40,20);
INSERT IGNORE INTO daily_symptoms VALUES(40,23);
INSERT IGNORE INTO daily_symptoms VALUES(40,31);
INSERT IGNORE INTO daily_symptoms VALUES(41,8);
INSERT IGNORE INTO daily_symptoms VALUES(42,2);
INSERT IGNORE INTO daily_symptoms VALUES(43,2);
INSERT IGNORE INTO daily_symptoms VALUES(44,3);
INSERT IGNORE INTO daily_symptoms VALUES(45,13);
INSERT IGNORE INTO daily_symptoms VALUES(46,8);
INSERT IGNORE INTO daily_symptoms VALUES(47,9);
INSERT IGNORE INTO daily_symptoms VALUES(48,13);
INSERT IGNORE INTO daily_symptoms VALUES(48,16);
INSERT IGNORE INTO daily_symptoms VALUES(48,28);
INSERT IGNORE INTO daily_symptoms VALUES(49,5);
INSERT IGNORE INTO daily_symptoms VALUES(50,16);

-- Como el login usa BCrypt, convertimos contraseñas antiguas en texto plano a un hash BCrypt.
-- OJO: debes probar con la contraseña que corresponda a este hash. Si no funciona, registra/actualiza una contraseña desde la app.
UPDATE users
SET password = '$2a$10$YzhsPPh1jrTbrQsCvUgJNeq4M4gTsrHRdZ1M9oVNt6t/AfHP0Sn0S'
WHERE password = '1234';
