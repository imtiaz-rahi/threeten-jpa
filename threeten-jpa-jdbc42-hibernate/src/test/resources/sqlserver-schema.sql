DROP TABLE IF EXISTS JAVA_TIME_42;
CREATE TABLE JAVA_TIME_42 (
  ID BIGINT NOT NULL,
  DATE_COLUMN DATE,
  TIME_COLUMN TIME,
  TIMESTAMP_COLUMN DATETIME2,
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS JAVA_TIME_42_WITH_ZONE;
CREATE TABLE JAVA_TIME_42_WITH_ZONE (
  ID BIGINT NOT NULL,
  ZONED_TIME DATETIMEOFFSET,
  OFFSET_TIME DATETIMEOFFSET,
  PRIMARY KEY (ID)
);
