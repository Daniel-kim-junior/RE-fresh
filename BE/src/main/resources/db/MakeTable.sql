create schema refresh;
use refresh;

create table member (
	member_uid VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '사용자 uid',
    department_uid VARCHAR(36) COMMENT '부서 Id',
    member_id VARCHAR(25) NOT NULL COMMENT '사용자 id',
    member_password VARCHAR(64) NOT NULL COMMENT '비밀번호',
    member_name VARCHAR(25) NOT NULL COMMENT '사용자 이름',
    member_cellphone	VARCHAR(25)	NOT NULL	COMMENT '전화번호',
	member_email	VARCHAR(25)	NOT NULL	COMMENT '이메일',
	annual_count	DECIMAL(3,1) DEFAULT '0' COMMENT '연차 갯수',
	member_auth  VARCHAR(25) NOT NULL COMMENT '사용자 권한',
	member_status	VARCHAR(25)		COMMENT '사용자 상태',
	retire_date DATETIME COMMENT '사용자 탈퇴 날짜',
	create_id VARCHAR(25)	NOT NULL	COMMENT '계정 생성자',
	create_tm	DATETIME	NOT NULL	COMMENT '계정 생성날짜',
	update_id	VARCHAR(25)	NOT NULL	COMMENT '계정 수정자',
	update_tm	DATETIME	NOT NULL	COMMENT '계정 수정 시간'
);
create table annual(
	annual_uid VARCHAR(36)  NOT NULL PRIMARY KEY COMMENT '연차 uid',
    member_uid VARCHAR(36)  NOT NULL COMMENT 'member uid',
    annual_type	VARCHAR(50)	NOT NULL	COMMENT '연차타입(반차,연차,월차)',
	start_date	DATETIME	NULL	COMMENT '연차 시작일',
	end_date	DATETIME	NULL	COMMENT '연차 종료일',
	annual_status	VARCHAR(50)	NOT NULL	COMMENT '연차 상태(대기,수락,거부)',
	acceptor VARCHAR(50)  COMMENT '연차 수락인',
	create_tm	DATETIME	NULL COMMENT '연차 생성 시간',
	update_tm	DATETIME	NULL	COMMENT '연차 수정일자'
);

create table department(
	department_uid VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '부서 uid',
    department_id VARCHAR(25) NOT NULL COMMENT '부서 id',
    department_name VARCHAR(25) NOT NULL COMMENT '부서 명',
    department_manager VARCHAR(25) NOT NULL COMMENT '팀장 id',
    create_id VARCHAR(25)	NOT NULL	COMMENT '부서 생성자',
	create_tm	DATETIME	NOT NULL	COMMENT '부서 생성날짜',
	update_id	VARCHAR(25)	NOT NULL	COMMENT '부서 수정자',
	update_tm	DATETIME	NOT NULL	COMMENT '부서 수정시간'
);

CREATE TABLE HOLIDAY (
   id MEDIUMINT NOT NULL AUTO_INCREMENT COMMENT '공휴일 uid',
   date_name CHAR(30) NOT NULL COMMENT '공휴일 이름',
   locdate CHAR(100) NOT NULL COMMENT '날짜',
   PRIMARY KEY (id)
);

CREATE TABLE annual_count (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '연차 집계 ID',
    annual_date DATETIME not null COMMENT '연차 날짜',
	sum_count int not null COMMENT '날짜 당 집계 숫자',
	department_uid VARCHAR(36) NOT NULL COMMENT '부서 uid',
	FOREIGN KEY (department_uid) REFERENCES department(department_uid)
);


alter table annual add unique (member_uid, start_date, end_date);
alter table member add foreign key(department_uid) references department(department_uid);
alter table annual add foreign key(member_uid) references member(member_uid);

Alter table member alter column retire_date set default null;

