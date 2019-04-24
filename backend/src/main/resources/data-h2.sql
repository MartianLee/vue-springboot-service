insert into users (email, name, password, created_date, modified_date) values ('test1@gmail.com', 'admin1',  '12345', now(), now());
insert into users (email, name, password, created_date, modified_date) values ('test2@gmail.com', 'admin2',  '12345', now(), now());

insert into team (name, league, stadium, created_date, modified_date) values ('Liverpool', 1,  'Anfield', now(), now());
insert into team (name, league, stadium, created_date, modified_date) values ('Manchester City', 1,  'Ethihad Stadium', now(), now());

insert into match (match_date, home_team_id, away_team_id, stadium, league, season, created_date, modified_date) values (now(), 1, 2, 'Anfield', '1',  '2018-2018', now(), now());
-- insert into match (match_date, stadium, league, season, created_date, modified_date) values (now(), 'Anfield', '1',  '2018-2018', now(), now());