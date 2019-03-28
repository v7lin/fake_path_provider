import 'dart:async';
import 'dart:io';

import 'package:fake_path_provider/fake_path_provider.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runZoned(() {
    runApp(MyApp());
  }, onError: (Object error, StackTrace stack) {
    print(error);
    print(stack);
  });

  if (Platform.isAndroid) {
    SystemUiOverlayStyle systemUiOverlayStyle =
    SystemUiOverlayStyle(statusBarColor: Colors.transparent);
    SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Home(),
    );
  }
}

class Home extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _HomeState();
  }
}

class _HomeState extends State<Home> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Fake PathProvider Demo'),
      ),
      body: ListView(
        children: <Widget>[
          ListTile(
            title: const Text('TemporaryDirectory'),
            onTap: () async {
              Directory directory = await PathProvider.getTemporaryDirectory();
              _showTips('TemporaryDirectory',
                  '${directory.existsSync()} - ${directory.path}');
            },
          ),
          ListTile(
            title: const Text('DocumentsDirectory'),
            onTap: () async {
              Directory directory = await PathProvider.getDocumentsDirectory();
              _showTips('DocumentsDirectory',
                  '${directory.existsSync()} - ${directory.path}');
            },
          ),
        ],
      ),
    );
  }

  void _showTips(String title, String content) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(title),
          content: Text(content),
        );
      },
    );
  }
}
