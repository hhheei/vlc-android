package org.videolan.vlc.util

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import org.videolan.vlc.R
import org.videolan.vlc.VLCApplication

object AppUtils {

    fun getVersionName(context: Context): String {
        return context.packageManager.getPackageInfo(VLCApplication.appContext.packageName, 0).versionName
    }

    fun getVersionCode(context: Context): Long {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            context.packageManager.getPackageInfo(context.packageName, 0).longVersionCode
        else context.packageManager.getPackageInfo(context.packageName, 0).versionCode.toLong()
    }

    fun isBeta(context: Context): Boolean {
        return context.resources.getBoolean(R.bool.is_beta)
    }

    fun totalMemory(): Long {
        val statFs = StatFs(Environment.getRootDirectory().absolutePath)
        return (statFs.blockCount * statFs.blockSize).toLong()
    }

    fun freeMemory(): Long {
        val statFs = StatFs(Environment.getRootDirectory().absolutePath)
        return (statFs.availableBlocks * statFs.blockSize).toLong()
    }

    fun busyMemory(): Long {
        val statFs = StatFs(Environment.getRootDirectory().absolutePath)
        val total = (statFs.blockCount * statFs.blockSize).toLong()
        val free = (statFs.availableBlocks * statFs.blockSize).toLong()
        return total - free
    }
}