insert into test_category(id, name, description) values(1, 'Category 1', 'Description 1');
insert into test_category(id, name, description) values(2, 'Category 2', 'Description 2');
insert into test_category(id, name, description) values(3, 'Category 3', 'Description 3');

insert into hosts(id, name, hostname, port, securehttp, test_category_id) values(1, 'Host 1', 'hostname1', 80, 0, 1);
insert into hosts(id, name, hostname, port, securehttp, test_category_id) values(2, 'Host 2', 'hostname2', 80, 0, 1);
insert into hosts(id, name, hostname, port, securehttp, test_category_id) values(3, 'Host 3', 'hostname3', 80, 0, 2);
