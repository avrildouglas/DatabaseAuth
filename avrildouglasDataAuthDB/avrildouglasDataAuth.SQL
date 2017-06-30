create database avrildouglasdataauth;
create user 'dataauthuse'@'localhost' identified by 'The DataAuth';
grant all on avrildouglasdataauth.* to 'dataauthuse'@'localhost';
