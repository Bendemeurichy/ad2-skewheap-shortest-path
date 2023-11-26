# a script that plots the results of the benchmarking

import matplotlib.pyplot as plt
import numpy as np
from scipy.interpolate import CubicSpline


def plot_graphs():
    # get the data
    operations = ["DecreaseKey", "Add", "Pop"]
    comparrisons = ["Time", "Comp"]

    # read out csv file with ; as seperator

    for operation in operations:
        for comparrison in comparrisons:
            leftistData = np.genfromtxt( "LeftistHeap" + operation + "Bench" + comparrison + ".csv",
                                        delimiter=';')
            skewData = np.genfromtxt( "SkewHeap" + operation + "Bench" + comparrison + ".csv", delimiter=';')
            skewNoOptData = np.genfromtxt( "SkewHeap" + operation + "Bench" + comparrison + "NoOpt.csv", delimiter=';')

            # interpolate the data
            x = np.linspace(0, 10_000_000, 50)
            leftistInterpolated = np.interp(x, leftistData[:, 0], leftistData[:, 1])
            skewInterpolated = np.interp(x, skewData[:, 0], skewData[:, 1])
            skewNoOptInterpolated = np.interp(x, skewNoOptData[:, 0], skewNoOptData[:, 1])

            # plot the data
            plt.plot(x, leftistInterpolated, label="LeftistHeap")
            plt.plot(x, skewInterpolated, label="SkewHeap")
            plt.plot(x, skewNoOptInterpolated, label="SkewHeapNoOpt")
            plt.legend()
            plt.xlabel("Number of operations")
            plt.ylabel(("Time in milliseconds" if comparrison == "Time" else "Number of comparrisons"))
            plt.title(operation + " " + comparrison)
            plt.savefig("graphs/" + operation + comparrison + ".png")
            plt.clf()


if __name__ == "__main__":
    plot_graphs()
