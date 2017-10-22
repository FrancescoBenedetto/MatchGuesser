def write_csv_file(features_and_labels):
    out_file = open('./output/features_and_labels.csv', 'w+')
    out_file.write(features_and_labels)
    out_file.close()

def write_csv_file_parametric(features_and_labels, i):
    out_file = open('./output/features_and_labels'+str(i)+'.csv', 'w+')
    out_file.write(features_and_labels)
    out_file.close()
