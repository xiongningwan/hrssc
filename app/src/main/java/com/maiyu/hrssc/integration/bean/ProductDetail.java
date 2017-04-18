package com.maiyu.hrssc.integration.bean;

import java.util.List;

/**
 * 产品详情
 */

public class ProductDetail {
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
}
