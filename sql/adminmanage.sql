SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

drop table if exists loginuser;
create table loginuser(
	login_userid		bigint(20)   not null auto_increment PRIMARY KEY comment '登录用户id',
	username 			varchar(50)	 not null comment '登录用户名',
	password			varchar(50)	 not null comment '登录密码',
	roles         varchar(50)	 not null comment '权限'
)engine=innodb auto_increment=20000 comment = '登录用户表';

insert loginuser(username,password,roles) values('usert1','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert2','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert3','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert4','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert5','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert6','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert7','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert8','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert9','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert10','123456','ROLE_SALER');
insert loginuser(username,password,roles) values('usert11','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert12','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert13','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert14','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert15','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert16','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert17','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert18','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert19','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert20','123456','ROLE_USER');
insert loginuser(username,password,roles) values('usert21','123456','ROLE_USER');
  
	

drop table if exists admin;
create table admin(
	admin_userid		bigint(20)   not null auto_increment PRIMARY KEY comment '管理员id',
	ausername 				varchar(50)	 not null													 comment '管理员用户名',
	apassword				varchar(50)	 not null 													 comment '管理员密码',
	aroles        		varchar(50)	 not null 												 comment '管理员权限',
	signtime      	datetime              													 comment '创建时间'
)engine=innodb auto_increment=1 																	 comment = '管理员表';

insert admin(ausername,apassword,aroles) values('admin','123456','ROLE_ADMIN,ROLE_SYSADMIN');
insert admin(ausername,apassword,aroles) values('admin01','123456','ROLE_SYSADMIN');
insert admin(ausername,apassword,aroles) values('admin02','123456','ROLE_SYSADMIN');
insert admin(ausername,apassword,aroles) values('admin03','123456','ROLE_ADMIN');
insert admin(ausername,apassword,aroles) values('admin04','123456','ROLE_ADMIN');
insert admin(ausername,apassword,aroles) values('admin05','123456','ROLE_ADMIN');
insert admin(ausername,apassword,aroles) values('admin06','123456','ROLE_ADMIN');
insert admin(ausername,apassword,aroles) values('admin07','123456','ROLE_SYSADMIN');
insert admin(ausername,apassword,aroles) values('admin08','123456','ROLE_ADMIN');
insert admin(ausername,apassword,aroles) values('admin09','123456','ROLE_ADMIN');
insert admin(ausername,apassword,aroles) values('admin10','123456','ROLE_SYSADMIN');
insert admin(ausername,apassword,aroles) values('admin11','123456','ROLE_ADMIN');
insert admin(ausername,apassword,aroles) values('admin12','123456','ROLE_ADMIN');



drop table if exists sys_notice;
create table sys_notice (
  notice_id         bigint(20)      not null auto_increment    comment '公告ID',
  notice_title      varchar(50)     not null                   comment '公告标题',
  notice_content    varchar(2000)   default null               comment '公告内容',
  status            char(1)         default '0'                comment '公告状态（0正常 1关闭）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(255)    default null               comment '备注',
  primary key (notice_id)
) engine=innodb auto_increment=1000 comment = '通知公告表';

insert sys_notice(notice_title,create_by) values('公告1','admin01');
insert sys_notice(notice_title,create_by) values('公告2','admin06');
insert sys_notice(notice_title,create_by) values('公告3','admin01');
insert sys_notice(notice_title,create_by) values('公告4','admin02');
insert sys_notice(notice_title,create_by) values('公告5','admin03');
insert sys_notice(notice_title,create_by) values('公告6','admin01');
insert sys_notice(notice_title,create_by) values('公告7','admin05');
insert sys_notice(notice_title,create_by) values('公告8','admin07');
insert sys_notice(notice_title,create_by) values('公告9','admin10');
insert sys_notice(notice_title,create_by) values('公告10','admin11');
insert sys_notice(notice_title,create_by) values('公告11','admin09');
insert sys_notice(notice_title,create_by) values('公告12','admin01');



drop table if exists complainttable;
create table complainttable(
	 complaint_id			    bigint(20)   		not null auto_increment    comment '投诉ID',
	 complaint_user       varchar(50)			not null                   comment '投诉用户',
	 complainted_seller   varchar(50)			not null                   comment '被投诉商家',
	 complaint_reason     varchar(400)		not null                   comment '投诉原因',
	 complaint_time				datetime																	 comment '投诉时间',
	 complaint_process    varchar(400)		not null                   comment '投诉进度',
	 lastupdate_time			datetime    															 comment '最后处理时间',
	 complaint_result			varchar(20)   	DEFAULT NULL							 comment '投诉结果',
	 primary key (complaint_id)
)engine=innodb auto_increment=2000 comment = '投诉信息表';

insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert12','usert2','质量不佳','等待审核');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert14','usert7','延迟发货','反馈成功');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert16','usert4','服务态度差','等待审核');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert18','usert7','质量不佳','反馈成功');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert11','usert1','服务态度差','等待审核');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert14','usert2','延迟发货','反馈成功');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert11','usert9','质量不佳','等待审核');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert17','usert5','延迟发货','反馈成功');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert19','usert8','质量不佳','等待审核');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert21','usert3','质量不佳','等待审核');
insert complainttable(complaint_user,complainted_seller,complaint_reason,complaint_process) values('usert20','usert6','服务态度差','反馈成功');


drop table if exists customer;
create table customer(
	cid 									bigint(20) 			auto_increment,
	username 							varchar(50) 			not null,
	nick_name 						varchar(30) 			not null 								comment '昵称',
	address 							varchar(200) 			DEFAULT null						comment '收货地址',
    sex 								varchar(5) 			default null 						comment '性别',
	telephone 						varchar(20) 			DEFAULT	null 					  comment '手机号',
	create_time 					datetime 		    													comment '创建时间',
	avatar 								varchar(100)      DEFAULT null 						comment '头像路径',
	is_delete 						tinyint 					default 0     					comment '注销标识字段（0-正常，1-注销）',
	locked 								tinyint 					default 0  							comment '锁定标识字段（0-未锁定，1-锁定）',
	insider								varchar(100) 		comment '会员',
	integral							bigint(20)			comment '积分',
	openid								varchar(100) 		comment '对应小程序openid',
	primary key (cid)
)engine=innodb auto_increment=1000 comment = '顾客信息表';
insert customer(username,nick_name,telephone) values('xqj','小丁','13616542325');
insert customer(username,nick_name,telephone) values('usert11','小王','13616542325');
insert customer(username,nick_name,telephone) values('usert12','小张','13616542315');
insert customer(username,nick_name,telephone) values('usert13','小徐','13616542395');
insert customer(username,nick_name,telephone) values('usert14','小杨','13616542345');
insert customer(username,nick_name,telephone) values('usert15','小吴','13614542365');
insert customer(username,nick_name,telephone) values('usert16','小宋','13521542365');
insert customer(username,nick_name,telephone) values('usert17','小美','11616542365');
insert customer(username,nick_name,telephone) values('usert18','小黄','15616542365');
insert customer(username,nick_name,telephone) values('usert19','小赵','12616542365');
insert customer(username,nick_name,telephone) values('usert20','小吕','14616542365');
insert customer(username,nick_name,telephone) values('usert21','小周','15616542365');
insert customer(username,nick_name,telephone) values('usert22','小周的','15616542365');


drop table if exists saler;
create table saler(
	sid 										bigint(20) 			auto_increment,
	username 								varchar(50) 		not null,
	nick_name 							varchar(30)     not null 								comment '昵称',
	telephone 							varchar(20) 		DEFAULT null            comment '手机号',
	sex 										varchar(5) 			default null 						comment '性别',
	address 								varchar(50) 		DEFAULT null 					  comment '发货地址',
	ccreate_time 						datetime 				 												comment '创建时间',
	avatar 									varchar(100)  	DEFAULT null						comment '头像路径',
	is_delete 							tinyint 				default 0  							comment '注销标识字段（0-正常，1-注销）',
	locked 									tinyint 				default 0  							comment '锁定标识字段（0-未锁定，1-锁定）',
	cert_info 							varchar(100) 		not null								comment '认证信息',
	locationplace						varchar(100)		comment '位置',
	fans								bigint(20) 			comment '粉丝数',
	rak									varchar(100)		comment '店铺等级',
	desp1								varchar(200)		comment '描述相符',
	desp2								varchar(200)		comment '服务态度',
	desp3								varchar(200)		comment '物流服务',
	visternum							bigint(20) 			comment '访客数',
	deposit								bigint(20)			comment '保证金额度'
	primary key (sid)				
)engine=innodb auto_increment=5000 comment = '商家信息表';

insert saler(username,nick_name,telephone,cert_info) values('usert1','大王','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert2','大国','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert3','大杨','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert4','大刘','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert5','大潘','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert6','大周','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert7','大红','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert8','大吴','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert9','大朱','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert10','大姜','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert11','大朱速','13616542325','xxxxxxxx');
insert saler(username,nick_name,telephone,cert_info) values('usert12','大姜啊','13616542325','xxxxxxxx');

