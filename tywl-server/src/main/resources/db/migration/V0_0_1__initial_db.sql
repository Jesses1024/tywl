-- 部门表
create table department(
  id int not null primary key auto_increment,
  name varchar(255) not null,
  `type` varchar(255) not null default 'none',
  description varchar(255)
);

-- 角色表
create table role(
  id int not null primary key auto_increment,
  name varchar(255) not null,
  dept_id int not null, -- 部门ID
  description varchar(255),
  constraint fk_role_dept_id foreign key (dept_id) references department(id)
);

-- 角色权限表
create table role_perm(
	role_id varchar(255) not null, -- 角色
	perm varchar(255) not null, -- 权限标识
	data_scope varchar(2048) not null, -- 数据权限范围(部门ids)
	constraint pk_role_perm primary key (role_id, perm),
	constraint fk_role_perm_role_id foreign key (role_id) references role(id)
);

-- 用户表
create table `user`(
  username varchar(255) not null primary key,
  password varchar(255) not null,
  first_name varchar(255),
  last_name varchar(255),
  email varchar(255),
  phone_number varchar(255),
  gender varchar(255),
  is_enabled boolean not null default true,
  is_account_non_expired boolean not null default true,
  is_account_non_locked boolean not null default true,
  is_credentials_non_expired boolean not null default true,
  created_date datetime not null default current_timestamp,
  updated_date datetime not null default current_timestamp
);

-- 用户角色表
create table user_role(
  user_username int not null,
  role_id int not null,
  constraint pk_user_role primary key (user_username, role_id),
  constraint fk_user_role_u_id foreign key (user_username) references `user`(username),
  constraint fk_user_role_r_id foreign key (role_id) references role(id)
);