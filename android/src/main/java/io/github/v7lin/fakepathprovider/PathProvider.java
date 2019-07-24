package io.github.v7lin.fakepathprovider;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;

import java.io.File;

import javax.annotation.Nullable;

public final class PathProvider {

    private PathProvider() {
    }

    public static File getTemporaryDirectory(Context context) {
        File cacheDir = null;
        if (context.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED
                && context.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED) {
            cacheDir = context.getExternalCacheDir();
        }
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        return cacheDir;
    }

    public static File getDocumentsDirectory(Context context) {
        return getFilesDirectory(context, Environment.DIRECTORY_DOCUMENTS);
    }

    public static File getFilesDirectory(Context context, @Nullable String type) {
        File filesDir = null;
        if (context.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED
                && context.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED) {
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
}
