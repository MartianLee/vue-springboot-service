insert into users (email, name, password, created_date, modified_date) values ('test1@gmail.com', 'admin1',  '12345', now(), now());
insert into users (email, name, password, created_date, modified_date) values ('test2@gmail.com', 'admin2',  '12345', now(), now());

insert into league (id, name, created_date, modified_date) values (2, 'English Premier League', now(), now());

insert into team (id, name, league_id, stadium, created_date, modified_date) values (1, 'Test Team1', 2,  'Test Stadium1', now(), now());
insert into team (id, name, league_id, stadium, created_date, modified_date) values (2, 'Test Team2', 2,  'Test Stadium2', now(), now());

insert into match (match_date, home_team_id, away_team_id, goals_home_team, goals_away_team, stadium, league_id, season, created_date, modified_date) values (now(), 1, 2, 3, 2, 'Anfield', 2,  '2018-2018', now(), now());
-- insert into match (match_date, stadium, league, season, created_date, modified_date) values (now(), 'Anfield', '1',  '2018-2018', now(), now());