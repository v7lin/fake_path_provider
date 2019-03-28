import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class PathProvider {
  PathProvider._();

  static const String _METHOD_GETTEMPORARYDIRECTORY = 'getTemporaryDirectory';
  static const String _METHOD_GETDOCUMENTSDIRECTORY = 'getDocumentsDirectory';

  static const MethodChannel _channel =
      MethodChannel('v7lin.github.io/fake_path_provider');

  static Future<Directory> getTemporaryDirectory() async {
    String path = await _channel.invokeMethod(_METHOD_GETTEMPORARYDIRECTORY);
    return Directory(path);
  }

  static Future<Directory> getDocumentsDirectory() async {
    String path = await _channel.invokeMethod(_METHOD_GETDOCUMENTSDIRECTORY);
    return Directory(path);
  }
}
