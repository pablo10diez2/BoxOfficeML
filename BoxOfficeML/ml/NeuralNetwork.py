import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import StandardScaler, PolynomialFeatures
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error

import tensorflow as tf

np.set_printoptions(precision=2)

tf.get_logger().setLevel('ERROR')
tf.autograph.set_verbosity(0)

#Function for reading the contents of the csv file FilmName/BoxOffice/Budget

x = []
y = []

def loadData():
    fichero = open("films.csv", "r")
    for linea in fichero:
        linea = linea.strip()
        sub_x = []
        sub_y = []
        campos = linea.split("/")
        sub_x.append(float(campos[2].replace(",", "")))
        sub_y.append(float(campos[1].replace(",", "")))

        global x, y

        x.append(sub_x)
        y.append(sub_y)

    fichero.close()

    #We convert the list to a numpy array
    x = np.array(x)
    y = np.array(y)

loadData()

print("Datos de X:", x)
print("Datos de Y:", y)