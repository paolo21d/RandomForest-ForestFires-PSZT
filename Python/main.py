import pandas as pd
import math
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error


def main():
    data = pd.read_csv("forestfires.csv")

    kFold = 10

    data = pd.get_dummies(data, prefix_sep='_', drop_first=True)

    err = 0.0

    for i in range(0, kFold):
        chunk = math.floor(data.shape[0]/kFold)
        test = data[chunk*i: chunk*(i+1)]
        train = data[0: chunk*i]
        train = train.append(data[chunk*(i+1):data.shape[0]])

        X_train = train.drop(columns='area')
        y_train = train.loc[:, 'area']

        X_test = test.drop(columns='area')
        Y_test = test.loc[:, 'area']

        forest = RandomForestRegressor(n_estimators=20, max_depth=10, n_jobs=-1)
        forest.fit(X_train, y_train)
        prediction = forest.predict(X_test)

        err += mean_squared_error(prediction, Y_test)

    err /= kFold
    print(err)


if __name__ == '__main__':
    main()
