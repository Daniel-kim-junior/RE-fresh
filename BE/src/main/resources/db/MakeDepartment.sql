INSERT INTO department (department_uid, department_id, department_name, department_manager, create_id, create_tm, update_id, update_tm)
VALUES
(uuid() , 'dept1', '인사팀', '홍길동', 'admin', NOW(), 'admin', NOW()),
(uuid() , 'dept2', '영업팀', '김철수', 'admin', NOW(), 'admin', NOW()),
(uuid() , 'dept3', '개발팀', '박영희', 'admin', NOW(), 'admin', NOW()),
(uuid() , 'dept4', '디자인팀', '최지민', 'admin', NOW(), 'admin', NOW()),
(uuid() , 'dept5', '서비스팀', '이민준', 'admin', NOW(), 'admin', NOW());