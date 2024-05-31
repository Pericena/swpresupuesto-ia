from flask import Flask, request, jsonify
import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.ensemble import IsolationForest

app = Flask(__name__)

@app.route('/detect_anomalies', methods=['POST'])
def detect_anomalies():
    data = request.json
    df = pd.DataFrame(data)
    
    iso = IsolationForest(contamination=0.1)
    df['anomaly'] = iso.fit_predict(df[['monto']])
    anomalies = df[df['anomaly'] == -1]
    
    return jsonify(anomalies.to_dict(orient='records'))

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    df = pd.DataFrame(data['expenses'])
    n_months = data['n_months']
    
    df['month_index'] = df['year'] * 12 + df['monto']
    X = df['month_index'].values.reshape(-1, 1)
    y = df['monto'].values
    
    model = LinearRegression()
    model.fit(X, y)
    
    last_month_index = df['month_index'].max()
    predictions = []
    
    for i in range(1, n_months + 1):
        month_index = last_month_index + i
        predicted_amount = model.predict([[month_index]])[0]
        year = month_index // 12
        month = month_index % 12
        predictions.append({'año': year, 'mes': month, 'monto': predicted_amount})
    
    return jsonify(predictions)

if __name__ == '__main__':
    app.run(debug=True)
