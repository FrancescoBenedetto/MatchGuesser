select Match.id,League.name,home.team_long_name, away.team_long_name,
Match.away_team_api_id, Match.season,
Match.date, Match.home_team_goal, Match.away_team_goal,

p1.player_name as player_1,
p2.player_name as player_2,
p3.player_name as player_3,
p4.player_name as player_4,
p5.player_name as player_5,
p6.player_name as player_6,
p7.player_name as player_7,
p8.player_name as player_8,
p9.player_name as player_9,
p10.player_name as player_10,
p11.player_name as player_11,
p12.player_name as player_12,
p13.player_name as player_13,
p14.player_name as player_14,
p15.player_name as player_15,
p16.player_name as player_16,
p17.player_name as player_17,
p18.player_name as player_18,
p19.player_name as player_19,
p20.player_name as player_20,
p21.player_name as player_21,
p22.player_name as player_22


from Match, League, Team as home, Team as away,

Player as p1,
Player as p2,
Player as p3,
Player as p4,
Player as p5,
Player as p6,
Player as p7,
Player as p8,
Player as p9,
Player as p10,
Player as p11,
Player as p12,
Player as p13,
Player as p14,
Player as p15,
Player as p16,
Player as p17,
Player as p18,
Player as p19,
Player as p20,
Player as p21,
Player as p22

where 
	Match.league_id=League.id and League.name like '%serie%'
	and Match.home_team_api_id=home.team_api_id
	and Match.away_team_api_id=away.team_api_id
	
	and Match.home_player_1=p1.player_api_id
and Match.home_player_2=p2.player_api_id
and Match.home_player_3=p3.player_api_id
and Match.home_player_4=p4.player_api_id
and Match.home_player_5=p5.player_api_id
and Match.home_player_6=p6.player_api_id
and Match.home_player_7=p7.player_api_id
and Match.home_player_8=p8.player_api_id
and Match.home_player_9=p9.player_api_id
and Match.home_player_10=p10.player_api_id
and Match.home_player_11=p11.player_api_id

and Match.away_player_1=p12.player_api_id
and Match.away_player_2=p13.player_api_id
and Match.away_player_3=p14.player_api_id
and Match.away_player_4=p15.player_api_id
and Match.away_player_5=p16.player_api_id
and Match.away_player_6=p17.player_api_id
and Match.away_player_7=p18.player_api_id
and Match.away_player_8=p19.player_api_id
and Match.away_player_9=p20.player_api_id
and Match.away_player_10=p21.player_api_id
and Match.away_player_11=p22.player_api_id

	
	and Match.season='2015/2016'
	
