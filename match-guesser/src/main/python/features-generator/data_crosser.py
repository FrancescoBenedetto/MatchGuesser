import pandas as pd
import numpy as np
from time import time
import logging

def get_fifa_data(matches, player_stats, path = None, data_exists = False):
    ''' Gets fifa data for all matches. '''

    #Check if fifa data already exists
    if data_exists == True:

        fifa_data = pd.read_pickle(path)

    else:

        print("Collecting fifa data for each match...")
        start = time()

        #Apply get_fifa_stats for each match
        fifa_data = matches.apply(lambda x :get_fifaStats_to_matchRes(x, player_stats), axis = 1)

        end = time()
        print("Fifa data collected in {:.1f} minutes".format((end - start)/60))

    #Return fifa_data
    return fifa_data

def get_fifaStats_to_matchRes(match, player_stats):
    try:
        #get stats
        #start = time()
        temp = get_fifa_stats(match, player_stats)
        #end = time()
        #print("Fifa stats collected in {:.1f} seconds".format((end - start)))
        #cut useless stats
        temp = cut_fifa_useless_stats(temp)
        #append match results
        temp = temp.append(get_match_goals(match))
        return temp
    except:
        logging.warning('error getting stats for match '+ str(match['id']))

def get_fifa_stats(match, player_stats):
    ''' Aggregates fifa stats for a given match. '''

    #Define variables
    match_id =  match.match_api_id
    date = match['date']
    players = ['home_player_1', 'home_player_2', 'home_player_3', "home_player_4", "home_player_5",
               "home_player_6", "home_player_7", "home_player_8", "home_player_9", "home_player_10",
               "home_player_11", "away_player_1", "away_player_2", "away_player_3", "away_player_4",
               "away_player_5", "away_player_6", "away_player_7", "away_player_8", "away_player_9",
               "away_player_10", "away_player_11"]
    player_stats_new = pd.DataFrame()
    names = []
    #Loop through all players
    for player in players:

        #Get player ID
        player_id = match[player]

        #check player id is not null
        if player_id is not None and np.isnan(player_id)==False:
            #Get player stats
            stats = player_stats[player_stats.player_api_id == player_id]

            #Identify current stats
            current_stats = stats[stats.date < date].sort_values(by = 'date', ascending = False)[:1]

            #get all the skills of a player
            current_stats.reset_index(inplace = True, drop = True)
            overall_rating = pd.Series(current_stats.loc[0, 'preferred_foot':'gk_reflexes'])

            #Rename stat
            name = [player+"_"+skillname for skillname in overall_rating.axes]
            names.extend(name)

            #Aggregate stats
            player_stats_new = pd.concat([player_stats_new, overall_rating])

    player_stats_new.index = np.concatenate(names).ravel().tolist()

    return player_stats_new.T.iloc[0]

def cut_fifa_useless_stats(match_stats):
    h_p = 'home_player_'
    a_p = 'away_player_'
    #cut handkeepers useless skills
    match_stats = match_stats.drop(match_stats.loc[h_p+'1_'+'preferred_foot':h_p+'1_'+'sliding_tackle'].index)
    match_stats = match_stats.drop(match_stats.loc[a_p+'1_'+'preferred_foot':a_p+'1_'+'sliding_tackle'].index)
    #cut other players useless skills
    for i in range(2,12):
        match_stats = match_stats.drop(match_stats.loc[h_p+str(i)+'_gk_diving':h_p+str(i)+'_gk_reflexes'].index)
        match_stats = match_stats.drop(match_stats.loc[a_p+str(i)+'_gk_diving':a_p+str(i)+'_gk_reflexes'].index)
    return match_stats

def get_match_goals(match):
    return match.loc['home_team_goal':'away_team_goal']
