from multiprocessing import Pool
import multiprocessing
import pandas as pd
import data_crosser as dc
import csv_writer as writer
import logging
import sqlite3



#config logger
logging.basicConfig(filename='./logs/data_crosser.log',
 filemode='w',
 level=logging.DEBUG,
 format='%(asctime)s %(levelname)-8s %(message)s',
 datefmt='%m-%d,%H:%M:%S'
 )

## Fetching data
#Connecting to database
path = "./input/"  #Insert path here
database = path + 'database.sqlite'
conn = sqlite3.connect(database)

#Defining the number of jobs to be run in parallel during grid search
n_jobs = multiprocessing.cpu_count() #Insert number of parallel jobs here

print("Fetching required data tables...")
#logging.info("Fetching required data tables...")
#Fetching required data tables
player_stats_data = pd.read_sql("SELECT * FROM Player_Attributes;", conn)
match_data = pd.read_sql("SELECT * FROM Match WHERE league_id=10257;", conn)

def get_fifa_data_wrapper(match_data):
    return dc.get_fifa_data(match_data, player_stats_data, data_exists = False)

n_matches = len(match_data.index)

#logging.info("launching parallel processing for crossing data")
print("processing data in parallel...")

if __name__ == '__main__':
    with Pool(processes=n_jobs) as p:
        results = p.map(get_fifa_data_wrapper, [match_data.iloc[int(i*(n_matches/n_jobs)):((i+1)*int((n_matches/n_jobs)))-1] for i in range(0,n_jobs)])
        print("writing results on files...")
        for i,res in enumerate(results):
            writer.write_csv_file_parametric(res.to_csv(), i)
        print("all features correctly collected and printed on files")
