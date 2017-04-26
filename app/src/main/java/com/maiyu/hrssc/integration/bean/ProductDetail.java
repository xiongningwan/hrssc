package com.maiyu.hrssc.integration.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 产品详情
 */

public class ProductDetail implements Parcelable {
    private Product product;
    private List<Image> iamges;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Image> getImages() {
        return iamges;
    }

    public void setImages(List<Image> images) {
        this.iamges = images;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.product, flags);
        dest.writeTypedList(this.iamges);
    }

    public ProductDetail() {
    }

    protected ProductDetail(Parcel in) {
        this.product = in.readParcelable(Product.class.getClassLoader());
        this.iamges = in.createTypedArrayList(Image.CREATOR);
    }

    public static final Parcelable.Creator<ProductDetail> CREATOR = new Parcelable.Creator<ProductDetail>() {
        @Override
        public ProductDetail createFromParcel(Parcel source) {
            return new ProductDetail(source);
        }

        @Override
        public ProductDetail[] newArray(int size) {
            return new ProductDetail[size];
        }
    };
}
