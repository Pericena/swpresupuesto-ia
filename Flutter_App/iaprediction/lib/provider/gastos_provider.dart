// lib/providers/expense_provider.dart
import 'package:flutter/material.dart';
import 'package:iaprediction/models/gastos_model.dart';
import 'package:iaprediction/services/gasto_service.dart';

class GastoProvider with ChangeNotifier {
  final GastoService _expenseService = GastoService();
  List<Gasto> _expenses = [];
  List<Gasto> _anomalies = [];
  List<Gasto> _predictionsStartDate = [];
  List<Gasto> _predictionsStartDateMonths = [];
  bool _isLoadinga = false;
  bool _isLoadingp = false;

  List<Gasto> get expenses => _expenses;
  List<Gasto> get anomalies => _anomalies;
  List<Gasto> get predictionsStartDate => _predictionsStartDate;
  List<Gasto> get predictionsStartDateMonths => _predictionsStartDateMonths;
  bool get isLoadinga => _isLoadinga;
  bool get isLoadingp => _isLoadingp;

  Future<void> fetchAnomalies() async {
    _isLoadinga = true;
    // notifyListeners();
    try {
      var anomaliesData = await _expenseService.detectAnomalies();

      List<Gasto> originalData = anomaliesData.originalData;
      List<Gasto> anomalies = anomaliesData.anomalias;
      _expenses = originalData;
      _anomalies = anomalies;
      // print(_anomalies);
    } catch (e) {
      print(e);
    }
    _isLoadinga = false;
    notifyListeners();
  }

  Future<void> fetchPredictionsBetweenDates(
      String startDate, int months) async {
    _isLoadingp = true;
    notifyListeners();
    try {
      var predictionData = await _expenseService.predictBetweenDatesWithMonths(startDate, months);

      List<Gasto> originalData = predictionData.originalData;
      List<Gasto> predictions = predictionData.predictions;
      _predictionsStartDate = originalData;
      _predictionsStartDateMonths = predictions;
      // print(_predictionsStartDateMonths);
    } catch (e) {
      print(e);
    }
    _isLoadingp = false;
    notifyListeners();
  }
}
