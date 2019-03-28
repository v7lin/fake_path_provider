#import "FakePathProviderPlugin.h"

@implementation FakePathProviderPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"v7lin.github.io/fake_path_provider"
                                     binaryMessenger:[registrar messenger]];
    FakePathProviderPlugin* instance = [[FakePathProviderPlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}

static NSString * const METHOD_GETTEMPORARYDIRECTORY = @"getTemporaryDirectory";
static NSString * const METHOD_GETDOCUMENTSDIRECTORY = @"getDocumentsDirectory";

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    if ([METHOD_GETTEMPORARYDIRECTORY isEqualToString:call.method]) {
        result([self getDirectoryOfType:NSCachesDirectory]);
    } else if ([METHOD_GETDOCUMENTSDIRECTORY isEqualToString:call.method]) {
        result([self getDirectoryOfType:NSDocumentDirectory]);
    } else {
        result(FlutterMethodNotImplemented);
    }
}

- (NSString*)getDirectoryOfType:(NSSearchPathDirectory)directory {
    NSArray* paths = NSSearchPathForDirectoriesInDomains(directory, NSUserDomainMask, YES);
    return paths.firstObject;
}

@end
