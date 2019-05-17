# fake_path_provider

[![Build Status](https://cloud.drone.io/api/badges/v7lin/fake_path_provider/status.svg)](https://cloud.drone.io/v7lin/fake_path_provider)
[![Codecov](https://codecov.io/gh/v7lin/fake_path_provider/branch/master/graph/badge.svg)](https://codecov.io/gh/v7lin/fake_path_provider)
[![GitHub Tag](https://img.shields.io/github/tag/v7lin/fake_path_provider.svg)](https://github.com/v7lin/fake_path_provider/releases)
[![Pub Package](https://img.shields.io/pub/v/fake_path_provider.svg)](https://pub.dartlang.org/packages/fake_path_provider)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/v7lin/fake_path_provider/blob/master/LICENSE)

A powerful path_provider plugin for Flutter.

## android

````
# 不需要做任何额外接入工作
# 混淆已打入 Library，随 Library 引用，自动添加到 apk 打包混淆
````

## ios

````
# 不需要做任何额外接入工作
````

## flutter

* snapshot

````
dependencies:
  fake_path_provider:
    git:
      url: https://github.com/v7lin/fake_path_provider.git
````

* release

````
dependencies:
  fake_path_provider: ^${latestTag}
````

* example

[示例](./example/lib/main.dart)

## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.io/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our 
[online documentation](https://flutter.io/docs), which offers tutorials, 
samples, guidance on mobile development, and a full API reference.
