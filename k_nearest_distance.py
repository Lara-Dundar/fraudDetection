# -*- coding: utf-8 -*-
"""
Created on Wed Sep 25 13:48:44 2024

@author: lara
"""

import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.neighbors import NearestNeighbors

df = pd.read_csv("data//ClusteringData.csv")
#sns.scatterplot(data=df,x="Weight",y="Height")
plt.scatter(x=df["Weight"], y=df["Height"])
plt.show()

def calculate_minPts(df):
    dimensions = len(df.columns)
    if dimensions == 2:
        return 4
    elif dimensions > 2:
        return dimensions*2
    else:
        print("Cannot Proceed.")

def calculate_epsilon(df,minPts):
    neighbors = NearestNeighbors(n_neighbors=minPts)
    neighbors_fit = neighbors.fit(df.iloc[1:,:])
    distances, indices = neighbors_fit.kneighbors(df.iloc[1:,:])
    distances = np.sort(distances, axis=0)
    distances = distances[:,1]
    plt.plot(distances)
    plt.ylabel('Distance')
    plt.show()
    return distances

def get_elbowPoint(distances):
    first_derivative = np.diff(distances)
    second_derivative = np.diff(first_derivative)
    elbow_index = np.argmax(second_derivative) + 1
    elbow_distance = distances[elbow_index]
    return elbow_distance

minPts = calculate_minPts(df)
distances = calculate_epsilon(df,minPts)

parameters = {
    'epsilon': [get_elbowPoint(distances)],
    'minPts': [minPts]
    }

df = pd.DataFrame(parameters)
df.to_csv('data//parameters.csv', index=False)
print("CSV file for parameters created successfully.")