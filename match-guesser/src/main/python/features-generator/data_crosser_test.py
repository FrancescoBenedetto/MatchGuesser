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
        self.roma_inter_2008_match_data = pd.read_sql("SELECT * FROM Match WHERE id=10609;", conn)

    def test_get_fifa_stats(self):
        romas_handkeeper_player_api_id = dc.get_fifa_stats(self.roma_inter_2008_match_data.iloc[0], self.player_stats_data)
        """
        cross check with:
        select *
        from Player, Player_Attributes
        where Player.player_name='Doni'
        and Player_Attributes.player_fifa_api_id=Player.player_fifa_api_id
        and date='2008-08-30 00:00:00'
        """
        #self.assertEqual(romas_handkeeper_player_api_id.loc['home_player_1_overall_rating'], 81)
        print("done")


if __name__ == '__main__':
    unittest.main()
