import unittest
import sqlite3
import pandas as pd
import data_crosser as dc

class TestDataCrosser(unittest.TestCase):

    @classmethod
    def setUpClass(self):
        path = "./input/"  #Insert path here
        database = path + 'database.sqlite'
        conn = sqlite3.connect(database)
        self.players = pd.read_sql("SELECT * FROM Player;", conn)
        self.player_stats_data = pd.read_sql("SELECT * FROM Player_Attributes;", conn)
        self.roma_inter_2008_match_data = pd.read_sql("SELECT * FROM Match WHERE id=10609;", conn)
        self.match_stats = dc.get_fifa_stats(self.roma_inter_2008_match_data.iloc[0], self.player_stats_data)
        self.one_match_data = dc.get_fifa_data(self.roma_inter_2008_match_data, self.player_stats_data)

    def test_get_fifa_stats_size(self):
        self.assertEqual(self.match_stats.size, 792)

    def test_get_fifa_stats_away_player_dribbling_skill(self):
        self.assertEqual(self.match_stats.loc['away_player_11_dribbling'], 92)

    def test_get_match_goals_one_match_home_team_goal(self):
        self.assertEqual(dc.get_match_goals(self.roma_inter_2008_match_data.iloc[0]).loc['home_team_goal'],0)

    def test_get_match_goals_one_match_away_team_goal(self):
        self.assertEqual(dc.get_match_goals(self.roma_inter_2008_match_data.iloc[0]).loc['away_team_goal'],4)

    def test_get_match_goals_one_match_sizel(self):
        self.assertEqual(dc.get_match_goals(self.roma_inter_2008_match_data.iloc[0]).size,2)

    def test_get_fifa_data_one_match(self):
        self.assertEqual(self.one_match_data.loc[0,'away_player_11_dribbling'],92)

    def test_get_fifa_data_one_match_home_team_goal(self):
        self.assertEqual(self.one_match_data.loc[0,'home_team_goal'],0)

    def test_cut_fifa_stats_stats_number(self):
        self.assertEqual(dc.cut_fifa_useless_stats(self.match_stats).size, 630)


if __name__ == '__main__':
    unittest.main()
