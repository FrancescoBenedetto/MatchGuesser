select Match.id,League.name,home.team_long_name, away.team_long_name,
Match.away_team_api_id, Match.season,
Match.date, Match.home_team_goal, Match.away_team_goal,

p1.overall_rating as Player_Attributes_1,
p2.overall_rating as Player_Attributes_2,
p3.overall_rating as Player_Attributes_3,
p4.overall_rating as Player_Attributes_4,
p5.overall_rating as Player_Attributes_5,
p6.overall_rating as Player_Attributes_6,
p7.overall_rating as Player_Attributes_7,
p8.overall_rating as Player_Attributes_8,
p9.overall_rating as Player_Attributes_9,
p10.overall_rating as Player_Attributes_10,
p11.overall_rating as Player_Attributes_11,
p12.overall_rating as Player_Attributes_12,
p13.overall_rating as Player_Attributes_13,
p14.overall_rating as Player_Attributes_14,
p15.overall_rating as Player_Attributes_15,
p16.overall_rating as Player_Attributes_16,
p17.overall_rating as Player_Attributes_17,
p18.overall_rating as Player_Attributes_18,
p19.overall_rating as Player_Attributes_19,
p20.overall_rating as Player_Attributes_20,
p21.overall_rating as Player_Attributes_21,
p22.overall_rating as Player_Attributes_22


from Match, League, Team as home, Team as away,

Player_Attributes as p1,
Player_Attributes as p2,
Player_Attributes as p3,
Player_Attributes as p4,
Player_Attributes as p5,
Player_Attributes as p6,
Player_Attributes as p7,
Player_Attributes as p8,
Player_Attributes as p9,
Player_Attributes as p10,
Player_Attributes as p11,
Player_Attributes as p12,
Player_Attributes as p13,
Player_Attributes as p14,
Player_Attributes as p15,
Player_Attributes as p16,
Player_Attributes as p17,
Player_Attributes as p18,
Player_Attributes as p19,
Player_Attributes as p20,
Player_Attributes as p21,
Player_Attributes as p22

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

	
	and Match.league_id=10257
	