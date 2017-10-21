import sqlite3
import pandas as pd
import data_crosser as dc
import csv_writer as writer
import logging

#config logger
logging.basicConfig(filename='./logs/data_crosser.log', filemode='w', level=logging.DEBUG)

## Fetching data
#Connecting to database
path = "./input/"  #Insert path here
database = path + 'database.sqlite'
conn = sqlite3.connect(database)

#Defining the number of jobs to be run in parallel during grid search
n_jobs = 4 #Insert number of parallel jobs here

#Fetching required data tables
#player_data = pd.read_sql("SELECT * FROM Player;", conn)
player_stats_data = pd.read_sql("SELECT * FROM Player_Attributes;", conn)
match_data = pd.read_sql("SELECT * FROM Match WHERE league_id=10257;", conn)

## Generating features, exploring the data, and preparing data for model training
#Generating or retrieving already existant FIFA data
fifa_data = dc.get_fifa_data(match_data, player_stats_data, data_exists = False)

writer.write_csv_file(fifa_data.to_csv(None))
#fifa_data.to_csv('./open/features.csv', 'w+')
