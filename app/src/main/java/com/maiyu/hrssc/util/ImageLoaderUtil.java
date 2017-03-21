package com.maiyu.hrssc.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
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

/**
 * UniversalImageLoader 工具
 * @author Jack
 *
 */
public class ImageLoaderUtil {
    
    /**
     * 初始化 ImageLoader，在其他函数功能使用之前必须调用，只能初始化一次，推荐在 Application 中调用
     * @param context
     */
    public static void initImageLoader(Context context){
        File cacheDir =StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
            .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽  
            .threadPoolSize(3)//线程池内加载的数量
            .threadPriority(Thread.NORM_PRIORITY - 2)
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) //你可以通过自己的内存缓存实现  
            .memoryCacheSize(2 * 1024 * 1024)
            .discCacheSize(50 * 1024 * 1024)
            .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密  
            .tasksProcessingOrder(QueueProcessingType.LIFO)
            .discCacheFileCount(100) //缓存的文件数量  
            .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径  
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
     * @author Jack
     *
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
     * @author Jack
     *
     */
    public interface Callback {
        public void onSuccess();

        public void onError();
    }
}
