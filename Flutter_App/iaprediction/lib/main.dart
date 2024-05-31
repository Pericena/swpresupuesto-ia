import 'package:flutter/material.dart';
import 'package:iaprediction/provider/gastos_provider.dart';
import 'package:iaprediction/screens/ia_screen.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => GastoProvider()),
      ],
      child: const MaterialApp(
        debugShowCheckedModeBanner: false,
        home: IAScreen(),
      ),
    );
  }
}