from merge_files import *
import os
import re
import shutil
import difflib
import sys
import time


def generate_merged_files():
    file_names = os.listdir()
    if 'all.in' not in file_names:
        merge_files(os.getcwd(), sum(1 for file_name in file_names if '.in' in file_name), 'in', 'all.in')
    if 'all.out' not in file_names:
        merge_files(os.getcwd(), sum(1 for file_name in file_names if '.out' in file_name), 'out', 'all.out')


def compile_java(script_path):
    file_names = os.listdir()
    shutil.copy(file_names[0], '{}\\temp_files\\{}'.format(script_path, file_names[0]))
    os.chdir('{}\\temp_files'.format(script_path))
    with open(file_names[0], 'r') as f:
        lines = f.readlines()
    with open(file_names[0], 'w') as f:
        for line in lines:
            if not re.search(r'package .*\;', line):
                f.write(line)
    os.system('javac {}'.format(file_names[0]))
    return file_names[0].replace('.java', '')


def run_java_with_input(class_file_name, input_output_path):
    os.system('java {} < {}\\all.in > out.txt'.format(class_file_name, input_output_path))


def compare_results(input_output_path):
    with open('out.txt', 'r') as f:
        program_output = f.readlines()
    with open(input_output_path + '\\all.out', 'r') as f:
        expected_output = f.readlines()
    print('Program output: ')
    print(''.join(program_output))
    print('Expected output: ')
    print(''.join(expected_output))
    print('Difference: ')
    print('\n'.join(
        difflib.unified_diff(program_output, expected_output)))


def judge(file_name, year, problem_letter):
    script_path = os.getcwd()
    listdir = os.listdir()
    if file_name not in listdir:
        print('Please run this from the tools directory.')
        return
    if 'temp_files' not in listdir:
        os.mkdir('temp_files')
    try:
        os.chdir('{}\\..\\acm\\y{}'.format(script_path, year))
    except FileNotFoundError:
        print('Could not find package y{}.'.format(year))
        return
    file_names = os.listdir()
    try:
        package_name = next(file_name for file_name in file_names if problem_letter in file_name)
    except StopIteration:
        print('Could not find package in {} with letter {} in it.'.format(os.getcwd(), problem_letter))
        return
    java_source_path = '{}\\..\\acm\\y{}\\{}'.format(script_path, year, package_name)
    input_output_path = '{}\\..\\problems\\acm\\{}\\{}'.format(script_path, year, problem_letter)
    try:
        os.chdir(input_output_path)
    except FileNotFoundError:
        print('{} is missing, download it from http://acm.ro/{}'.format(os.path.abspath(input_output_path), year))
        return
    os.chdir(java_source_path)
    class_file_name = compile_java(script_path)
    os.chdir(input_output_path)
    generate_merged_files()
    os.chdir(script_path + '\\temp_files')
    run_java_with_input(class_file_name, input_output_path)
    compare_results(input_output_path)
    shutil.rmtree('.\\', ignore_errors=True)


def main():
    if len(sys.argv) != 3:
        print('Usage: judge.py <year> <problem_letter>')
        return
    file_name, year, problem_letter = sys.argv
    judge(file_name, year, problem_letter)


if __name__ == '__main__':
    main()
