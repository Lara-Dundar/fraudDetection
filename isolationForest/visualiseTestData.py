import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt


df = pd.read_csv("data//winequality-red.csv", delimiter=';')

#plt.scatter(x=df["fixed acidity"], y=df["quality"])
#plt.scatter(x=df["volatile acidity"], y=df["quality"])
#plt.scatter(x=df["citric acid"], y=df["quality"])
#plt.scatter(x=df["residual sugar"], y=df["quality"])
#plt.scatter(x=df["chlorides"], y=df["quality"])
#plt.scatter(x=df["free sulfur dioxide"], y=df["quality"])
#plt.scatter(x=df["total sulfur dioxide"], y=df["quality"])
#plt.scatter(x=df["density"], y=df["quality"])
#plt.scatter(x=df["pH"], y=df["quality"])
#plt.scatter(x=df["sulphates"], y=df["quality"])
#plt.scatter(x=df["alcohol"], y=df["quality"])
plt.show()