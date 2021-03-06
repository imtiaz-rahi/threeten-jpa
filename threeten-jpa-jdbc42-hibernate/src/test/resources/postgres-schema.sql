DROP TABLE IF EXISTS JAVA_TIME_42;
CREATE TABLE JAVA_TIME_42 (
  ID BIGINT NOT NULL,
  DATE_COLUMN DATE,
  TIME_COLUMN TIME,
  TIMESTAMP_COLUMN TIMESTAMP,
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS JAVA_TIME_42_WITH_ZONE;
CREATE TABLE JAVA_TIME_42_WITH_ZONE (
  ID BIGINT NOT NULL,
  ZONED_TIME TIMESTAMP WITH TIME ZONE,
  OFFSET_TIME TIMESTAMP WITH TIME ZONE,
  PRIMARY KEY (ID)
);
