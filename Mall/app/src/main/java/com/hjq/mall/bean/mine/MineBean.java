package com.hjq.mall.bean.mine;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MineBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;

    /**
     * @param itemType  item的类型
     * @param imageUrl  图片链接
     * @param text      文本
     * @param value
     * @param id        当前的id
     */
    public MineBean(int itemType,
                    String imageUrl,
                    String text,
                    String value,
                    int id
    ) {
        mItemType = itemType;
        mImageUrl = imageUrl;
        mText = text;
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

    public String getText() {
        if (mText == null) {
            return  "";
        }
        return mText;
    }

    public void setText(String text) {
        mText = text;
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
        private String text = null;
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

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public MineBean build() {
            return new MineBean(itemType, imageUrl, text, value, id);
        }
    }
}
