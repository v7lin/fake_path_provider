package io.github.v7lin.fakepathprovider;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FakePathProviderPlugin
 */
public class FakePathProviderPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "v7lin.github.io/fake_path_provider");
        channel.setMethodCallHandler(new FakePathProviderPlugin(registrar));
    }

    private static final String METHOD_GETTEMPORARYDIRECTORY = "getTemporaryDirectory";
    private static final String METHOD_GETDOCUMENTSDIRECTORY = "getDocumentsDirectory";

    private final Registrar registrar;

    public FakePathProviderPlugin(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (METHOD_GETTEMPORARYDIRECTORY.equals(call.method)) {
            result.success(PathProvider.getTemporaryDirectory(registrar.context()).getAbsolutePath());
        } else if (METHOD_GETDOCUMENTSDIRECTORY.equals(call.method)) {
            result.success(PathProvider.getDocumentsDirectory(registrar.context()).getAbsolutePath());
        } else {
            result.notImplemented();
        }
    }
}
