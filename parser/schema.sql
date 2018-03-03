CREATE DATABASE wallethub;
USE wallethub;

CREATE TABLE WH_LOG(
	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	REQUEST_DATE timestamp NOT NULL,
    IP VARCHAR(15) NOT NULL,
    REQUEST VARCHAR(20) NOT NULL,
    STATUS INTEGER NOT NULL,
    AGENT VARCHAR(500) NOT NULL
);

CREATE TABLE WH_BLOCKED_IP (
	IP VARCHAR(15) PRIMARY KEY NOT NULL,
    REQUEST_NUMBER INTEGER NOT NULL,
    INITIAL_DATE TIMESTAMP NOT NULL,
    FINAL_DATE TIMESTAMP NOT NULL
);