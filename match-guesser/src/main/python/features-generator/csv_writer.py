def write_csv_file(features_and_labels):
    out_file = open('./output/features_and_labels.csv', 'w+')
    out_file.write(features_and_labels)
    out_file.close()
