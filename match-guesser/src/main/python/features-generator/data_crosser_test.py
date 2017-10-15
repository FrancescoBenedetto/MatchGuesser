import unittest
import sqlite3
import pandas as pd
import data_crosser as dc

class TestDataCrosser(unittest.TestCase):

    def setUp(self):
        path = "./input/"  #Insert path here
        database = path + 'database.sqlite'
        conn = sqlite3.connect(database)
        self.players = pd.read_sql("SELECT * FROM Player;", conn)
        self.player_stats_data = pd.read_sql("SELECT * FROM Player_Attributes;", conn)
        self.roma_inter_2008_match_data = pd.read_sql("SELECT * FROM Match WHERE id=10609;", conn).iloc[0]
        self.match_stats = dc.get_fifa_stats(self.roma_inter_2008_match_data, self.player_stats_data)

    def test_get_fifa_stats_size(self):
        self.assertEqual(self.match_stats.size, 814)

    def test_get_fifa_stats_away_player_dribbling_skill(self):
        self.assertEqual(self.match_stats.loc['away_player_11_dribbling'][0], 92)


if __name__ == '__main__':
    unittest.main()
