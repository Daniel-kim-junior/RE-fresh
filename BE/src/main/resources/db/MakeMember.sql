INSERT INTO member (member_uid, department_uid, member_id, member_password, member_name, member_auth ,member_cellphone, member_email, annual_count, member_status, create_id, create_tm, update_id, update_tm) VALUES
(UUID(), (Select department_uid from department where department_name = '개발팀')
, 'admin', '1234', '박영희', 'admin' ,'010-1234-5678', 'member1@gmail.com', 12.0, 'not in use', 'super', NOW(), 'super', NOW()),
(UUID(), (Select department_uid from department where department_name = '개발팀')
, 'member2', '5678', '이순신', '사원' ,'010-5678-1234', 'member2@gmail.com', 10.0, 'not in use', 'admin', NOW(), 'admin', NOW()),
(UUID(), (Select department_uid from department where department_name = '개발팀')
, 'member3', 'abcd', '강감찬','사원' ,'010-1234-5678', 'member3@gmail.com', 7.5, 'in use', 'admin', NOW(), 'admin', NOW()),
(UUID(), (Select department_uid from department where department_name = '개발팀')
, 'member4', 'efgh', '을지문덕','사원' ,'010-5678-1234', 'member4@gmail.com', 3.0, 'in use', 'admin', NOW(), 'admin', NOW()),
(UUID(), (Select department_uid from department where department_name = '개발팀')
, 'member5', 'ijkl', '신사임당', '사원' ,'010-1234-5678', 'member5@gmail.com', 8.0, 'in use', 'admin', NOW(), 'admin', NOW());