package com.hjq.mall.bean.mine;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MineBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private MineItemBean mItemBean = null;
    private String mValue = null;
    private int mId = 0;

    /**
     * @param itemType  item的类型
     * @param imageUrl  图片链接
     * @param itemBean      子项内容
     * @param value
     * @param id        当前的id
     */
    public MineBean(int itemType,
                    String imageUrl,
                    MineItemBean itemBean,
                    String value,
                    int id
    ) {
        mItemType = itemType;
        mImageUrl = imageUrl;
        mItemBean = itemBean;
        mValue = value;
        mId = id;
    }

    public void setItemType(int itemType) {
        mItemType = itemType;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public MineItemBean getmItemBean() {
        return mItemBean;
    }

    public void setmItemBean(MineItemBean mItemBean) {
        this.mItemBean = mItemBean;
    }

    public String getValue() {
        if (mValue == null) {
            return  "";
        }
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static final class Builder {
        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private MineItemBean itemBean = null;
        private String value = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setItemBean(MineItemBean itemBean) {
            this.itemBean = itemBean;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public MineBean build() {
            return new MineBean(itemType, imageUrl, itemBean , value, id);
        }
    }
}
