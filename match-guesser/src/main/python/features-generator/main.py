import sqlite3
import pandas as pd
import data_crosser as dc

## Fetching data
#Connecting to database
path = "./input/"  #Insert path here
database = path + 'database.sqlite'
conn = sqlite3.connect(database)

#Defining the number of jobs to be run in parallel during grid search
n_jobs = 1 #Insert number of parallel jobs here

#Fetching required data tables
#player_data = pd.read_sql("SELECT * FROM Player;", conn)
player_stats_data = pd.read_sql("SELECT * FROM Player_Attributes;", conn)
#team_data = pd.read_sql("SELECT * FROM Team;", conn)
match_data = pd.read_sql("SELECT * FROM Match WHERE id=10609;", conn)

#print(player_data.iloc[0])

## Generating features, exploring the data, and preparing data for model training
#Generating or retrieving already existant FIFA data
fifa_data = dc.get_fifa_data(match_data, player_stats_data, data_exists = False)

print(fifa_data)
