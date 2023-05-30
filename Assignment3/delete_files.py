import os
from os import listdir

# main

my_path_gui = './'

for file_name in listdir(my_path_gui):

    if file_name.endswith('.class'):
        os.remove(my_path_gui + file_name)


# bll

my_path_gui = './bll'

for file_name in listdir(my_path_gui):

    if file_name.endswith('.class'):
        os.remove(my_path_gui + '/' + file_name)

# model

my_path_model = './model'

for file_name in listdir(my_path_model):

    if file_name.endswith('.class'):
        os.remove(my_path_model + '/' + file_name)

# dao

my_path_bl = './dao'

for file_name in listdir(my_path_bl):

    if file_name.endswith('.class'):
        os.remove(my_path_bl + '/' + file_name)

# connection

my_path_bl = './connection'

for file_name in listdir(my_path_bl):

    if file_name.endswith('.class'):
        os.remove(my_path_bl + '/' + file_name)

# view

my_path_bl = './view'

for file_name in listdir(my_path_bl):

    if file_name.endswith('.class'):
        os.remove(my_path_bl + '/' + file_name)