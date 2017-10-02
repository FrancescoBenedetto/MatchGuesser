year=$1
max_pages=$2
seriea_name=$3

location_dir=~/workspace/MatchGuesser/webpages/$year-seriea/
base_page=http://www.futhead.com/$year/leagues/$seriea_name/?page=

for i in $(seq 1 $max_pages);
do wget -P $location_dir $base_page$i;

done
