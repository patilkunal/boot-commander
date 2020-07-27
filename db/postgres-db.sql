create or replace function create_testcasedb_schema ()
    returns void as
$func$
begin
   if exists (select from pg_catalog.pg_tables
              where  schemaname = 'public'
              and    tablename  = 'users') then
      raise notice 'tables already exists.';
   else
    create table users (
        id serial primary key,
        username varchar(100) not null,
        password varchar(100),
        name varchar(255),
        email varchar(255),
        token varchar(255),
        token_create_ts timestamp default now(),
        token_access_ts timestamp default now()
    );

    create unique index idx_users_username on users (username);

    create table user_roles (
        id serial primary key,
        user_id integer references users(id),
        role_name varchar(100)
    );

    create table test_category (
        id serial primary key,
        name varchar(20),
        description varchar(255)
    );

    create table testcase (
        id serial primary key,
        name varchar(255),
        description varchar(255),
        test_category_id integer not null references test_category(id),
        rest_url varchar(255) not null,
        http_method varchar(10) not null,
        http_data varchar(4000),
        content_type varchar(50) default 'application/json' not null,
        validate_output integer default 0 not null,
        output_template varchar(4000),
        allow_blank_output integer default 0 not null,
        validate_type varchar(10)
    );

    create table testcase_instance (
        id serial primary key,
        name varchar(255),
        description varchar(255),
        testcase_id integer not null,
        user_id integer not null,
        validate_output integer default 0 not null,
        output_template varchar(4000),
        allow_blank_output integer default 0 not null,
        validate_type varchar(10),
        constraint fk_testinstance_testcase foreign key (testcase_id) references testcase(id),
        constraint fk_testinstance_users foreign key (user_id) references users(id)
    );

    create index sys_idx_10147 on testcase_instance (user_id);
    create unique index sys_idx_sys_pk_10134_10139 on testcase_instance (id);

    create table hosts (
        id serial primary key,
        name varchar(255),
        hostname varchar(255) not null,
        port integer not null,
        securehttp integer default 0 not null,
        test_category_id integer not null,
        constraint fk_hosts_test_category_id foreign key (test_category_id) references test_category(id)
    );
    create index sys_idx_10113 on hosts (test_category_id);
    create unique index sys_idx_sys_pk_10102_10107 on hosts (id);

    create table testcase_run (
        id serial primary key,
        testcase_instance_id integer not null,
        host_id integer not null,
        success integer default 1 not null,
        run_date timestamp default localtimestamp,
        return_code integer default 0 not null,
        error varchar(1024),
        "result" varchar(4096),
        content_type varchar(20),
        constraint fk_testrun_host foreign key (host_id) references hosts(id),
        constraint fk_testrun_testcaseinstance foreign key (testcase_instance_id) references testcase_instance(id) on delete cascade
    );

    create index sys_idx_10171 on testcase_run (testcase_instance_id);
    create index sys_idx_10173 on testcase_run (host_id);

    create table testcase_values (
        id serial primary key,
        testcase_instance_id integer not null,
        key_name varchar(255) not null,
        key_value varchar(2048),
        value_type varchar(20),
        constraint fk_testcasevalue_testinstance foreign key (testcase_instance_id) references testcase_instance(id) on delete cascade
    );
    create index sys_idx_10157 on testcase_values (testcase_instance_id);
    create unique index sys_idx_sys_pk_10150_10153 on testcase_values (id);

    create table http_headers (
        id serial primary key,
        test_category_id integer,
        name varchar(255),
        "value" varchar(255),
        testcase_id integer,
        constraint fk_httpheaders_testcategory foreign key (test_category_id) references test_category(id)
    );
    create index sys_idx_10205 on http_headers (test_category_id);

    create table schedules (
        id serial primary key,
        name varchar(100),
        cron_expr varchar(100),
        host_id integer not null,
        active integer default 0 not null,
        test_category_id integer not null,
        constraint fk_schedule_category foreign key (test_category_id) references test_category(id) on delete cascade,
        constraint fk_schedule_host foreign key (host_id) references hosts(id)
    );
    create index sys_idx_10185 on schedules (host_id);
    create index sys_idx_10187 on schedules (test_category_id);

    create table scheduled_testcases (
        id serial primary key,
        schedule_id integer not null,
        testcase_instance_id integer not null,
        constraint fk_schedule foreign key (schedule_id) references schedules(id) on delete cascade,
        constraint fk_testcase_instance foreign key (testcase_instance_id) references testcase_instance(id) on delete cascade
    );
    create index sys_idx_10197 on scheduled_testcases (schedule_id);
    create index sys_idx_10199 on scheduled_testcases (testcase_instance_id);

    insert into users values(nextval('users_id_seq'),'admin','$2a$10$smle1rpjhyialf1t1gtvvu2y6atnhx6ue21y5pjxykdcut29qjsx2','admin','admin@localhost','eyj0exaioijkv1qilcjhbgcioijiuzuxmij9.eyjzdwiioijhzg1pbiisimv4cci6mtu0odyxotg1oh0.p7ogqpwzomrrkx8-m4djr1zq8ltwsxrxif-2sklcwldlplakpu94ind0bbt_cz4w7ljjvjpevn0cgoi9hfufzw',null,null);
    insert into user_roles values(nextval('user_roles_id_seq'),1,'superadmin');

    insert into test_category(id, name, description) values(nextval('test_category_id_seq'), 'commander', 'commander test cases');

    insert into hosts(id, name, hostname, port, test_category_id ) values(nextval('hosts_id_seq'), 'boot commander (local)', 'localhost', 9090, 1);

    insert into testcase(id, name, description, test_category_id, rest_url, http_method, http_data)
    values(nextval('testcase_id_seq'), 'get categories', 'get a list of all categories', 1, '/boot-commander/categories', 'get', '');
    insert into testcase_instance(name, description, testcase_id, user_id)
    values('get categories', 'get a list of all categoriest', 1,1 );

end if;
end
$func$ language plpgsql;


select create_testcasedb_schema();