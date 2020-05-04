# folder = input("folder: ")
# ext = input("ext: ").strip(".")
# count = int(input("count: "))
# out_file = input("outfile: ")

folder = u"C:\\Users\\anikov\workspace\\algocrew\\src\\main\\java\\input-output\\I"
count = 21
ext = "out"
out_file = "b.txt"

with open(out_file, "w") as of:
    for i in range(1, count + 1):
        with open("{}/{:03d}.{}".format(folder, i, ext), "r") as f:
            for line in f:
                of.write(line)
