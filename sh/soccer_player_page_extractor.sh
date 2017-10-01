year=$1

base_url=http://www.futhead.com
location_dir=~/workspace/MatchGuesser/webpages/$year-seriea-players/

player_links=($(grep -hn "/$year/players/[0-9]*/*/" ~/workspace/MatchGuesser/webpages/$year-seriea/* | cut -d'"' -f 2))

for player_link in "${player_links[@]}"
do
wget -P $location_dir $base_url"${player_link}"
done
