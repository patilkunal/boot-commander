CREATE TABLE USERS(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	USERNAME VARCHAR(100) NOT NULL,
	PASSWORD VARCHAR(100),
	NAME VARCHAR(255),
	EMAIL VARCHAR(255),
	TOKEN VARCHAR(255),
	TOKEN_CREATE_TS TIMESTAMP DEFAULT LOCALTIMESTAMP,
	TOKEN_ACCESS_TS TIMESTAMP DEFAULT LOCALTIMESTAMP)	
	);

CREATE TABLE TEST_CATEGORY(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	NAME VARCHAR(20),
	DESCRIPTION VARCHAR(255));
	
CREATE TABLE HOSTS(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255),
	DESCRIPTION VARCHAR(255),
	HOSTNAME VARCHAR(255) NOT NULL,
	PORT INTEGER NOT NULL,
	SECUREHTTP INTEGER DEFAULT 0 NOT NULL,
	TEST_CATEGORY_ID INTEGER NOT NULL,
	CONSTRAINT FK_HOSTS_TEST_CATEGORY_ID FOREIGN KEY(TEST_CATEGORY_ID) REFERENCES TEST_CATEGORY(ID));

CREATE TABLE TESTCASE(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255),
	DESCRIPTION VARCHAR(255),
	TEST_CATEGORY_ID INTEGER NOT NULL,
	REST_URL VARCHAR(255) NOT NULL,
	HTTP_METHOD VARCHAR(10) NOT NULL,
	HTTP_DATA VARCHAR(4000),
	CONTENT_TYPE VARCHAR(50) DEFAULT 'application/json' NOT NULL,
	VALIDATE_OUTPUT INTEGER DEFAULT 0 NOT NULL,
	OUTPUT_TEMPLATE VARCHAR(4000),
	ALLOW_BLANK_OUTPUT INTEGER DEFAULT 0 NOT NULL,
	VALIDATE_TYPE VARCHAR(10),
	CONSTRAINT FK_TESTCASE_TESTCATEGORY FOREIGN KEY(TEST_CATEGORY_ID) REFERENCES TEST_CATEGORY(ID);
	
CREATE TABLE TESTCASE_INSTANCE(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255),
	DESCRIPTION VARCHAR(255),
	TESTCASE_ID INTEGER NOT NULL,
	USER_ID INTEGER NOT NULL,
	VALIDATE_OUTPUT INTEGER DEFAULT 0 NOT NULL,
	OUTPUT_TEMPLATE VARCHAR(4000),
	ALLOW_BLANK_OUTPUT INTEGER DEFAULT 0 NOT NULL,
	VALIDATE_TYPE VARCHAR(10),
	CONSTRAINT FK_TESTINSTANCE_TESTCASE FOREIGN KEY(TESTCASE_ID) REFERENCES TESTCASE(ID),
	CONSTRAINT FK_TESTINSTANCE_USERS FOREIGN KEY(USER_ID) REFERENCES USERS(ID));

CREATE TABLE TESTCASE_VALUES(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	TESTCASE_INSTANCE_ID INTEGER NOT NULL,
	KEY_NAME VARCHAR(255) NOT NULL,
	KEY_VALUE VARCHAR(255) NOT NULL,
	CONSTRAINT FK_TESTCASEVALUE_TESTINSTANCE FOREIGN KEY(TESTCASE_INSTANCE_ID) REFERENCES TESTCASE_INSTANCE(ID));

CREATE TABLE TESTCASE_RUN(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	TESTCASE_INSTANCE_ID INTEGER NOT NULL,HOST_ID INTEGER NOT NULL,
	SUCCESS INTEGER DEFAULT 1 NOT NULL,
	RUN_DATE TIMESTAMP DEFAULT LOCALTIMESTAMP,
	RETURN_CODE INTEGER DEFAULT 0 NOT NULL,
	ERROR VARCHAR(1024),RESULT VARCHAR(1024),
	CONTENT_TYPE VARCHAR(20),
	CONSTRAINT FK_TESTRUN_TESTCASEINSTANCE FOREIGN KEY(TESTCASE_INSTANCE_ID) REFERENCES TESTCASE_INSTANCE(ID),
	CONSTRAINT FK_TESTRUN_HOST FOREIGN KEY(HOST_ID) REFERENCES HOSTS(ID));

CREATE TABLE SCHEDULES(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	NAME VARCHAR(100),
	CRON_EXPR VARCHAR(100),
	HOST_ID INTEGER NOT NULL,
	ACTIVE INTEGER DEFAULT 0 NOT NULL,
	TEST_CATEGORY_ID INTEGER NOT NULL,
	CONSTRAINT FK_SCHEDULE_HOST FOREIGN KEY(HOST_ID) REFERENCES HOSTS(ID),
	CONSTRAINT FK_SCHEDULE_CATEGORY FOREIGN KEY(TEST_CATEGORY_ID) REFERENCES TEST_CATEGORY(ID) ON DELETE CASCADE);

CREATE TABLE SCHEDULED_TESTCASES(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	SCHEDULE_ID INTEGER NOT NULL,
	TESTCASE_INSTANCE_ID INTEGER NOT NULL,
	CONSTRAINT FK_SCHEDULE FOREIGN KEY(SCHEDULE_ID) REFERENCES SCHEDULES(ID) ON DELETE CASCADE,
	CONSTRAINT FK_TESTCASE_INSTANCE FOREIGN KEY(TESTCASE_INSTANCE_ID) REFERENCES TESTCASE_INSTANCE(ID) ON DELETE CASCADE);

create table HTTP_HEADERS(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY, 
	TEST_CATEGORY_ID INTEGER NOT NULL, 
	NAME VARCHAR(255) NOT NULL, 
	value VARCHAR(255) NOT NULL,
	CONSTRAINT FK_HTTPHEADERS_TESTCATEGORY FOREIGN KEY(TEST_CATEGORY_ID) REFERENCES PUBLIC.TEST_CATEGORY(ID)
	);	

create table AUDIT_HISTORY(
	ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
	ENTITY VARCHAR(255) NOT NULL, 
	ENTITY_ID INTEGER NOT NULL, 
	HISTORY_TYPE VARCHAR(255) NOT NULL, 
	CHANGE_JSON VARCHAR(200) NOT NULL, 
	CREATED_BY VARCHAR(255) NOT NULL, 
	CREATED_DT TIMESTAMP DEFAULT LOCALTIMESTAMP, 	
	);
	
INSERT INTO USERS VALUES(3,'admin','$2a$10$sMlE1RPjHYiaLf1T1gtvVu2Y6AtNhX6Ue21Y5PjxYKdCUt29qJSX2','admin','admin@localhost','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0ODYxOTg1OH0.p7ogqPWzoMRRkX8-m4DJR1ZQ8LTWSXRXIf-2sklCWLDLplakpU94inD0bbT_Cz4w7LjjVJPeVn0cGOi9HFuFZw',NULL,NULL)
		
	
insert into test_category(name, description) values('BATCH', 'Batch Server Test Cases');
	
insert into hosts(hostname, port, description) values('localhost', 8080, 'Local Machine');
insert into hosts(hostname, port, description) values('localhost', 9090, 'DEV VM (via SSH Tunnel)');
insert into hosts(hostname, port, description) values('10.8.172.139', 8080, 'DEV VM (Direct)');

insert into testcase(name, description, test_category_id, rest_url, http_method, http_data)
values('Get BSID Import Scheduled Jobs', 'Get a list of all BSID Import scheduled job list', 0, '/batch/scheduledBatchJob/bsidImport', 'GET', '');
insert into testcase_instance(name, description, testcase_id)
values('Get BSID Import Scheduled Jobs', 'Get a list of all BSID Import scheduled job list', 0);
insert into testcase(name, description, test_category_id, rest_url, http_method, http_data)
values('Get VOD Import Scheduled Jobs', 'Get a list of all VOD Import scheduled job list', 0, '/scheduler/scheduledBatchJob/vodImport', 'GET', '');
insert into testcase_instance(name, description, testcase_id)
values('Get VOD Import Scheduled Jobs', 'Get a list of all BSID Import scheduled job list', 1);
