// lib/services/gasto_service.dart
import 'package:graphql_flutter/graphql_flutter.dart';
import 'package:iaprediction/graphql_config.dart';
import 'package:iaprediction/models/gastos_model.dart';

class GastoService{
  final cliente = GraphQLConfig().clientToQuery();

  Future<({List<Gasto> originalData, List<Gasto> anomalias})> detectAnomalies() async {
    const String query = r'''
      query {
        detectAnomalies {
          original_data {
            year
            month
            monto
          }
          anomalies {
            year
            month
            monto
          }
        }
      }
    ''';

    final QueryOptions options = QueryOptions(
      document: gql(query),
    );

    final QueryResult result = await cliente.query(options);

    if (result.hasException) {
      throw Exception(result.exception.toString());
    }

    List<Gasto> originalData = (result.data!['detectAnomalies']['original_data'] as List)
      .map((item) => Gasto.fromJson(item))
      .toList();

    List<Gasto> anomalies = (result.data!['detectAnomalies']['anomalies'] as List)
      .map((item) => Gasto.fromJson(item))
      .toList();
    
    return (originalData: originalData, anomalias: anomalies);
  }

  Future<({List<Gasto> originalData, List<Gasto> predictions})> predictBetweenDatesWithMonths(String startDate, int numberOfMonths) async {
    const String query = r'''
      query PredictBetweenDates($startDate: String!, $numberOfMonths: Int!) {
        predictBetweenDatesWithMonths(startDate: $startDate, numberOfMonths: $numberOfMonths) {
          original_data {
            year
            month
            monto
          }
          predictions {
            year
            month
            monto
          }
        }
      }
    ''';

    final QueryOptions options = QueryOptions(
      document: gql(query),
      variables: {
        'startDate': startDate,
        'numberOfMonths': numberOfMonths,
      },
    );

    final QueryResult result = await cliente.query(options);

    if (result.hasException) {
      throw Exception(result.exception.toString());
    }

    List<Gasto> originalData = (result.data!['predictBetweenDatesWithMonths']['original_data'] as List)
      .map((item) => Gasto.fromJson(item))
      .toList();

    List<Gasto> predictions = (result.data!['predictBetweenDatesWithMonths']['predictions'] as List)
      .map((item) => Gasto.fromJson(item))
      .toList();

    return (originalData: originalData, predictions: predictions);
  }
}
