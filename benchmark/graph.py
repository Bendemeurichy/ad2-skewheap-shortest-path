#a script that plots the results of the benchmarking

import matplotlib.pyplot as plt
import numpy as np
import os
import sys

def plot_graphs(comparissonType,operation):
#read the data from the file
    file_name_time = "data/heaptype" + heaptype + operation + "Time.csv"
    file = open(file_name_time,"r")
    data = file.readlines()
    file.close()

    #extract the data from the file
    x = []
    y = []
    for line in data:
        line = line.split()
        x.append(int(line[0]))
        y.append(float(line[1]))

    #plot the graph
    plt.plot(x,y)
    plt.xlabel("Number of elements")
    plt.ylabel("Time in seconds")
    plt.title(heaptype + " " + operation)
    plt.show()
