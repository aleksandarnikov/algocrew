def merge_files(folder, count, ext, out_file):
    ext.replace('.', '')
    with open(out_file, "w") as of:
        for i in range(1, count + 1):
            with open("{}\\{:03d}.{}".format(folder, i, ext), "r") as f:
                for line in f:
                    of.write(line)


def main():
    folder = input("folder: ")
    ext = input("ext: ").strip(".")
    count = int(input("count: "))
    out_file = input("outfile: ")
    merge_files(folder, count, ext, out_file)


if __name__ == '__main__':
    main()
