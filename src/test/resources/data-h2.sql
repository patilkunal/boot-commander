delete from users;
insert into users(id, username, password, name, email, token, token_create_ts, token_access_ts)
values(users_id_seq.nextval, 'kunal', '$2a$10$sMlE1RPjHYiaLf1T1gtvVu2Y6AtNhX6Ue21Y5PjxYKdCUt29qJSX2', 'Kunal', 'test@nowhere.com', null, null, null);

delete from user_roles;
insert into user_roles(id, user_id, role_name) values(1, 1, 'USER');

delete from test_category;
insert into test_category(id, name, description) values(test_category_id_seq.nextval, 'Category 1', 'Description 1');
insert into test_category(id, name, description) values(test_category_id_seq.nextval, 'Category 2', 'Description 2');
insert into test_category(id, name, description) values(test_category_id_seq.nextval, 'Category 3', 'Description 3');

delete from hosts;
insert into hosts(id, name, hostname, port, securehttp, test_category_id) values(1, 'Host 1', 'hostname1', 80, 0, 1);
insert into hosts(id, name, hostname, port, securehttp, test_category_id) values(2, 'Host 2', 'hostname2', 80, 1, 1);
insert into hosts(id, name, hostname, port, securehttp, test_category_id) values(3, 'Host 3', 'hostname3', 80, 0, 2);


insert into testcase(name, description, test_category_id, rest_url, http_method, http_data)
    values('Get Categories', 'Get a list of all categories', 1, '/boot-commander/categories', 'GET', '');
insert into testcase_instance(name, description, testcase_id, user_id)
    values('Get Categories', 'Get a list of all categoriest', 1, 1 );
