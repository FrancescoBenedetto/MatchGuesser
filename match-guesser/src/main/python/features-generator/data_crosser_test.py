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
        romas_handkeeper_player_api_id = dc.get_fifa_stats(self.roma_inter_2008_match_data, self.player_stats_data)
        print(romas_handkeeper_player_api_id)
        #romas_handkeeper_name = players[players.players_api_id=romas_handkeeper_player_api_id]['name']
        self.assertEqual('Doni', 'Doni')
"""
    def get_fifa_stats(self):
        self.assertTrue('FOO'.isupper())
        self.assertFalse('Foo'.isupper())

    def test_split(self):
        s = 'hello world'
        self.assertEqual(s.split(), ['hello', 'world'])
        # check that s.split fails when the separator is not a string
        with self.assertRaises(TypeError):
            s.split(2)
"""


if __name__ == '__main__':
    unittest.main()
