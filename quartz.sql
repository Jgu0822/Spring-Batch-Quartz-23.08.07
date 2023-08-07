-- Quartz의 properties 파일에서 설정해야 할 내용입니다. 이 설정은 데이터베이스에 대한 드라이버 및 설정을 지정합니다.
org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate

-- Quartz의 테이블 생성 스크립트입니다. INNODB 엔진을 사용하도록 수정되었습니다.
-- 아래 SQL 스크립트를 데이터베이스에서 실행하십시오.

-- QRTZ_JOB_DETAILS: 각 구성된 Job의 세부 정보를 저장합니다.
-- QRTZ_TRIGGERS: 구성된 Trigger의 정보를 저장합니다.
-- QRTZ_SIMPLE_TRIGGERS: Simple Trigger의 정보를 저장합니다.
-- QRTZ_CRON_TRIGGERS: Cron Trigger의 정보를 저장합니다.
-- QRTZ_SIMPROP_TRIGGERS: Simple Property Trigger의 정보를 저장합니다.
-- QRTZ_BLOB_TRIGGERS: Blob 타입의 Trigger 정보를 저장합니다.
-- QRTZ_CALENDARS: Quartz Calendar 정보를 Blob 타입으로 저장합니다.
-- QRTZ_PAUSED_TRIGGER_GRPS: 일시 중지된 Trigger 그룹의 정보를 저장합니다.
-- QRTZ_FIRED_TRIGGERS: 실행된 Trigger 및 연관된 Job 실행 정보를 저장합니다.
-- QRTZ_SCHEDULER_STATE: Scheduler 상태 정보를 저장합니다.
-- QRTZ_LOCKS: Lock 정보를 저장합니다.

DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

CREATE TABLE QRTZ_JOB_DETAILS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  JOB_NAME VARCHAR(200) NOT NULL,
  JOB_GROUP VARCHAR(200) NOT NULL,
  DESCRIPTION VARCHAR(250) NULL,
  JOB_CLASS_NAME VARCHAR(250) NOT NULL,
  IS_DURABLE VARCHAR(1) NOT NULL,
  IS_NONCONCURRENT VARCHAR(1) NOT NULL,
  IS_UPDATE_DATA VARCHAR(1) NOT NULL,
  REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
  JOB_DATA BLOB NULL,
  PRIMARY KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
) ENGINE=InnoDB;

-- 나머지 테이블 생성 스크립트는 생략합니다.

-- 테스트용 테이블입니다.
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `job_entity`;
CREATE TABLE `job_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `group` varchar(255) DEFAULT NULL,
  `cron` varchar(255) DEFAULT NULL,
  `parameter` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `vm_param` varchar(255) DEFAULT NULL,
  `jar_path` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `job_entity` VALUES ('1', '첫 번째', 'helloworld', '0/2 * * * * ?', '1', '첫 번째 작업', '', null, 'OPEN');
-- 나머지 INSERT 문은 생략합니다.
