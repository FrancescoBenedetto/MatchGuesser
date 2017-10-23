sed '/d*,\{631\}/d' features_and_labels.csv > features_labels_clean.csv
sed -i 's/_0/medium/g' features_labels_clean.csv
sed -i 's/,o,/,medium,/g' features_labels_clean.csv
