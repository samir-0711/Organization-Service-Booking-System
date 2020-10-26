-- Database creation

Create table Username_pwd (
Username varchar(30) not null,
Password varchar(100) not null,
primary key (Username) 
);

create table organization_id_pwd (
org_id varchar(30) not null,
org_pwd varchar(100) not null,
org_type varchar(1) not null,
CONSTRAINT org_constraint CHECK (org_type='h' or org_type='r' or org_type='s' or org_type='e'),
primary key (org_id)
);

create table User_info (
user_id varchar(30) not null,
user_First_name varchar(30) not null,
user_Last_name varchar(30) not null,
user_gender varchar(10) not null,
user_phone_no_1 varchar(15) not null,
user_email_address varchar(50) not null,
CONSTRAINT gender_constraint CHECK (user_gender='M' or user_gender='F' or user_gender='m' or user_gender='f'),
primary key (user_id),
foreign key (user_id) references Username_pwd (Username)
);

create table Restaurant_info (
R_id varchar(30) not null,
R_name varchar(30) not null,
R_type varchar(20) not null,
R_contanct_person varchar(30) not null,
R_phone_no_1 varchar(15) not null,
R_email_address varchar(50) not null,
R_address varchar(300) not null,
R_Veg_Non_Veg varchar(2) not null,
R_area varchar(25) not null,
R_city varchar(25) not null,
R_state varchar(25) not null,
R_AC_or_not varchar(2) not null,
R_pincode varchar(6) not null,
CONSTRAINT Veg_Non_Veg_constraint CHECK (R_Veg_Non_Veg='V' or R_Veg_Non_Veg='N' or R_Veg_Non_Veg='NV'),
CONSTRAINT AC_or_not_constraint CHECK (R_AC_or_not='AC' or R_AC_or_not='NA'),
primary key (R_id),
foreign key (R_id) references organization_id_pwd (org_id)
);

create table Hotel_info (
H_id varchar(30) not null,
H_name varchar(30) not null,
H_type varchar(20) not null,
H_contanct_person varchar(30) not null,
H_phone_no_1 varchar(15) not null,
H_email_address varchar(50) not null,
H_address varchar(300) not null,
H_area varchar(25) not null,
H_city varchar(25) not null,
H_state varchar(25) not null,
H_pincode varchar(6) not null,
primary key (H_id),
foreign key (H_id) references organization_id_pwd (org_id)
);

create table Salon_info (
S_id varchar(30) not null,
S_name varchar(30) not null,
S_type varchar(1) not null,
S_contanct_person varchar(30) not null,
S_phone_no_1 varchar(15) not null,
S_email_address varchar(50) not null,
S_address varchar(300) not null,
S_area varchar(25) not null,
S_city varchar(25) not null,
S_state varchar(25) not null,
S_pincode varchar(6) not null,
CONSTRAINT S_type_constraint CHECK (S_type='m' or S_type='w' or S_type='b'),
primary key (S_id),
foreign key (S_id) references organization_id_pwd (org_id)
);

create table Salon_booking_info (
Username_customer varchar(30) not null,
S_id varchar(30) not null,
S_Arrival_Date varchar(11) not null,
S_Arrival_Time varchar(15) not null,
S_timestamp varchar(50) not null,
primary key (Username_customer,S_id,S_Arrival_Date,S_Arrival_Time),
foreign key (Username_customer) references Username_pwd (Username),
foreign key (S_id) references organization_id_pwd (org_id)
);

create table Home_services_info (
HS_id varchar(30) not null,
HS_name varchar(30) not null,
HS_contanct_person varchar(30) not null,
HS_phone_no_1 varchar(15) not null,
HS_email_address varchar(50) not null,
HS_address varchar(300) not null,
HS_area varchar(25) not null,
HS_city varchar(25) not null,
HS_state varchar(25) not null,
HS_pincode varchar(6) not null,
primary key (HS_id),
foreign key (HS_id) references organization_id_pwd (org_id)
);

create table HS_visting_charge_entry (
HS_id varchar(30) not null,
HS_type varchar(1) not null,
HS_visiting_charge varchar(9) not null,
primary key (HS_id, HS_type),
CONSTRAINT HS_type_constraint2 CHECK (HS_type='p' or HS_type='e' or HS_type='c'),
foreign key (HS_id) references organization_id_pwd (org_id)
);

create table HS_customer_address (
HS_Booking_id varchar(10) not null,
Username_customer varchar(30) not null,
HS_id varchar(30) not null,
Address varchar(100) not null,
pincode varchar(6) not null,
Preferred_Date varchar(15) not null,
Preferred_Time varchar(15) not null,
primary key (HS_Booking_id),
foreign key (Username_customer) references Username_pwd (Username),
foreign key (HS_id) references organization_id_pwd (org_id)
);

create table Home_service_type_booking_info (
HS_Booking_id varchar(10) not null,
HS_type varchar(1) not null,
HS_timestamp varchar(50) not null,
CONSTRAINT HS_type_constraint3 CHECK (HS_type='p' or HS_type='e' or HS_type='c'),
primary key (HS_Booking_id,HS_type,HS_timestamp),
foreign key (HS_Booking_id) references HS_customer_address (HS_Booking_id)
);

create view HS_name_id as (
select HS_id,HS_name,HS_phone_no_1
from Home_services_info);

create view HS_table as (
select d.HS_timestamp,g.HS_name,d.HS_type,f.Preferred_Date,f.Preferred_Time,g.HS_phone_no_1,f.Username_customer
from Home_service_type_booking_info as d natural join HS_customer_address as f natural join HS_name_id as g
);

create view S_table as (
select f.S_timestamp,d.S_name,d.S_type,f.S_Arrival_Date,f.S_Arrival_Time,d.S_phone_no_1,f.Username_customer
from Salon_booking_info as f natural join Salon_info as d
);

create view User_id_name(Username_customer,user_First_name,user_Last_name,user_phone_no_1) as (
	select user_id,user_First_name,user_Last_name,user_phone_no_1
    from User_info
);

create view Salon_visible_entry(Sid,_timestamp,_id,_name,_num,_date,_time) as (
select S_id,S_timestamp,Username_customer,concat(concat(user_First_name," "),user_Last_name),user_phone_no_1,S_Arrival_Date,S_Arrival_Time
from Salon_booking_info natural join Home_service_type_booking_info
);

create view Home_Service_visible_entry(Hid,_timestamp,_id,_name,_num,_type,_date,_time) as (
select HS_id,HS_timestamp,Username_customer,concat(concat(user_First_name," "),user_Last_name),user_phone_no_1,HS_type,Preferred_Date,Preferred_Time
from HS_customer_address natural join Home_service_type_booking_info natural join User_id_name
);

create view counter as (
Select coalesce(max(HS_Booking_id),0) as d from HS_customer_address
);

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'mysql'

