


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
  `keywords` varchar(255) DEFAULT NULL,
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


select * from event;
select * from event_location;