package com.maiyu.hrssc.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.math.BigDecimal;

/**
 * UniversalImageLoader 工具
 *
 * @author Jack
 */
public class ImageLoaderUtil {

    /**
     * 初始化 ImageLoader，在其他函数功能使用之前必须调用，只能初始化一次，推荐在 Application 中调用
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) //你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                // .discCacheSize(50 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                // .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // .discCacheFileCount(100) //缓存的文件数量
                .diskCacheFileCount(100) //缓存的文件数量
                .diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);
    }

    public static void loadImage(ImageView imageview, String imageUrl, int stub, int empty, int error) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(stub) //加载图片时的图片  
                .showImageForEmptyUri(empty) //没有图片资源时的默认图片  
                .showImageOnFail(error) //加载失败时的图片  
                .cacheInMemory(true) //启用内存缓存  
                .cacheOnDisk(true) //启用外存缓存  
                .considerExifParams(true) //启用EXIF和JPEG图像格式  
                .displayer(new SimpleBitmapDisplayer()).build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imageUrl, imageview, options);
    }

    public static void loadImage(ImageView imageview, String imageUrl, int stub) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(stub) //加载图片时的图片  
                .showImageForEmptyUri(stub) //没有图片资源时的默认图片  
                .showImageOnFail(stub) //加载失败时的图片  
                .cacheInMemory(true) //启用内存缓存  
                .cacheOnDisk(true) //启用外存缓存  
                .considerExifParams(true) //启用EXIF和JPEG图像格式  
                .displayer(new SimpleBitmapDisplayer()).build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imageUrl, imageview, options);
    }

    public static void loadImage(ImageView imageview, String imageUrl, int stub, int empty, int error, Callback callback) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(stub) //加载图片时的图片  
                .showImageForEmptyUri(empty) //没有图片资源时的默认图片  
                .showImageOnFail(error) //加载失败时的图片  
                .cacheInMemory(true) //启用内存缓存  
                .cacheOnDisk(true) //启用外存缓存  
                .considerExifParams(true) //启用EXIF和JPEG图像格式  
                .displayer(new SimpleBitmapDisplayer()).build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imageUrl, imageview, options, new LoadingLisener(callback));
    }

    public static void loadImage(ImageView imageview, String imageUrl, int stub, Callback callback) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(stub) //加载图片时的图片  
                .showImageForEmptyUri(stub) //没有图片资源时的默认图片  
                .showImageOnFail(stub) //加载失败时的图片  
                .cacheInMemory(true) //启用内存缓存  
                .cacheOnDisk(true) //启用外存缓存  
                .considerExifParams(true) //启用EXIF和JPEG图像格式  
                .displayer(new SimpleBitmapDisplayer()).build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imageUrl, imageview, options, new LoadingLisener(callback));
    }

    /**
     * Load Image 监听器
     *
     * @author Jack
     */
    public static class LoadingLisener implements ImageLoadingListener {
        Callback callback;

        public LoadingLisener(Callback callback) {
            this.callback = callback;
        }

        @Override
        public void onLoadingCancelled(String arg0, View arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
            callback.onSuccess();
        }

        @Override
        public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
            callback.onError();
        }

        @Override
        public void onLoadingStarted(String arg0, View arg1) {
            // TODO Auto-generated method stub

        }

    }

    /**
     * Load Image 监听器回调函数
     *
     * @author Jack
     */
    public interface Callback {
        public void onSuccess();

        public void onError();
    }



    public static void clearCache(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");
        ImageLoader imageLoader = ImageLoader.getInstance();
        //imageLoader.clearMemoryCache(cacheDir);
        imageLoader.clearDiskCache();
    }

    /**
     * 获取缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");

        long cacheSize = getFolderSize(cacheDir);
        return getFormatSize(cacheSize);

       /* long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);*/
    }

    // 获取文件大小
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
