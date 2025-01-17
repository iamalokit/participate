


CREATE USER 'alokit'@'localhost' IDENTIFIED BY 'VmAj1327';
GRANT ALL PRIVILEGES ON *.* TO 'alokit'@'localhost' WITH GRANT OPTION;
CREATE DATABASE PARTICIPATEV1;
grant all on PARTICIPATEV1.* to 'alokit'@'localhost';
drop table event;
drop table event_location;
select * from PARTICIPATEV1.event;
select * from event;
select * from eventlocation;
drop table eventlocation;



select * from event;

create database mall character set utf8;

use mall;
grant all privileges on *.* to 'reader' @'%' identified by 'VmAj1327!';
SELECT 
   user 
FROM 
   mysql.user;
   
use PARTICIPATEV1;
select * from ums_admin;
select * from ums_admin_role_relation;
select * from ums_admin_permission_relation;

select * from event_category;
DROP TABLE IF EXISTS `event_category`;

CREATE TABLE `event_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `level` int(1) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(64) NOT NULL,
  `event_type` varchar(64) DEFAULT NULL,
  `participant_type` varchar(64) DEFAULT NULL,
  `event_category_id` bigint(20) DEFAULT NULL,
  `event_location_id` bigint(20) DEFAULT NULL,
  `event_date` varchar(255) DEFAULT NULL,
  `event_cost` varchar(255) DEFAULT NULL,
  `event_pic` varchar(255) DEFAULT NULL,
  `event_description` text,
  `create_time` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;


CREATE TABLE `event_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `location_name` varchar(64) NOT NULL,
  `address_line_1` varchar(64) DEFAULT NULL,
  `address_line_2` varchar(64) DEFAULT NULL,
  `zip_code` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `state` varchar(64) DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

select * from event_category;
select * from event;
select * from event_location;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL ,
  `email` varchar(100) DEFAULT NULL ,
  `nick_name` varchar(200) DEFAULT NULL ,
  `note` varchar(500) DEFAULT NULL ,
  `create_time` datetime DEFAULT NULL ,
  `login_time` datetime DEFAULT NULL ,
  `status` int(1) DEFAULT '1' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `admin_count` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT '1',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `admin_login_log`;
CREATE TABLE `admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `admin_role_relation`;
CREATE TABLE `admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,	
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;