
max_pages=22
location_dir=~/workspace/MatchGuesser/webpages/13-seriea/
base_page=http://www.futhead.com/13/leagues/seriea/?page=

for i in $(seq 1 $max_pages); 
do wget -P $location_dir $base_page$i;

done
