package io.github.v7lin.fakepathprovider;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;

import java.io.File;
import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
            result.success(getTemporaryDirectory(registrar.context()).getAbsolutePath());
        } else if (METHOD_GETDOCUMENTSDIRECTORY.equals(call.method)) {
            result.success(getDocumentsDirectory(registrar.context()).getAbsolutePath());
        } else {
            result.notImplemented();
        }
    }

    private File getTemporaryDirectory(Context context) {
        File cacheDir = null;
        if ((!hasPermissionInManifest(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) && !hasPermissionInManifest(context, Manifest.permission.READ_EXTERNAL_STORAGE)) ||
                (hasPermissionInManifest(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) && checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        hasPermissionInManifest(context, Manifest.permission.READ_EXTERNAL_STORAGE) && checkPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            cacheDir = context.getExternalCacheDir();
        }
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        return cacheDir;
    }

    private File getDocumentsDirectory(Context context) {
        return getFilesDirectory(context, Environment.DIRECTORY_DOCUMENTS);
    }

    private File getFilesDirectory(Context context, @Nullable String type) {
        File filesDir = null;
        if ((!hasPermissionInManifest(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) && !hasPermissionInManifest(context, Manifest.permission.READ_EXTERNAL_STORAGE)) ||
                (hasPermissionInManifest(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) && checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        hasPermissionInManifest(context, Manifest.permission.READ_EXTERNAL_STORAGE) && checkPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            filesDir = context.getExternalFilesDir(type);
        }
        if (filesDir == null) {
            filesDir = context.getFilesDir();
            if (!TextUtils.isEmpty(type)) {
                filesDir = new File(filesDir, type);
                if (!filesDir.exists()) {
                    filesDir.mkdirs();
                }
            }
        }
        return filesDir;
    }

    private boolean hasPermissionInManifest(Context context, @Nonnull String permission) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            return Arrays.asList(info.requestedPermissions).contains(permission);
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    private int checkPermission(Context context, @Nonnull String permission) {
        return context.checkPermission(permission, Process.myPid(), Process.myUid());
    }
}
