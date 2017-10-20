import pandas as pd
import numpy as np
from time import time

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
        temp = get_fifa_stats(match, player_stats).append(get_match_goals(match))
        return temp
    except:
        print(match)
        #print('---------------------------------------------STATS------------------------------------')
        #print(player_stats.loc[0, 'potential':'gk_reflexes'])

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

        #Get player stats
        stats = player_stats[player_stats.player_api_id == player_id]

        #Identify current stats
        current_stats = stats[stats.date < date].sort_values(by = 'date', ascending = False)[:1]

        if player_id is None or np.isnan(player_id) == True:
            overall_rating = pd.Series(0)
        else:
            current_stats.reset_index(inplace = True, drop = True)
            overall_rating = pd.Series(current_stats.loc[0, 'potential':'gk_reflexes'])

        #Rename stat
        name = [player+"_"+skillname for skillname in overall_rating.axes]
        names.extend(name)

        #Aggregate stats
        player_stats_new = pd.concat([player_stats_new, overall_rating])

    player_stats_new.index = np.concatenate(names).ravel().tolist()
    #player_stats_new['match_api_id'] = match_id

    #player_stats_new.reset_index(inplace = True, drop = True)

    #Return player stats
    return player_stats_new.T.ix[0]

def get_match_goals(match):
    return match.loc['home_team_goal':'away_team_goal']
