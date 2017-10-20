base_dir=$1

mkdir base_dir/output/

for i in $(seq 10 18);
do mkdir base_dir/output/$i;
done
