package com.hjq.mall.pro.mine.view.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.mall.R;
import com.hjq.mall.bean.mine.MineBean;
import com.hjq.mall.bean.mine.MineItemType;

import java.util.List;

public class MineAdapter extends BaseMultiItemQuickAdapter<MineBean, BaseViewHolder> {

    public MineAdapter(List<MineBean> data) {
        super(data);
        // 添加布局
        addItemType(MineItemType.ITEM_NORMAL, R.layout.item_mine_normal);
        addItemType(MineItemType.ITEM_LINE, R.layout.item_mine_line);
    }

    // 数据和布局关联
    @Override
    protected void convert(BaseViewHolder helper, MineBean item) {
        switch (helper.getItemViewType()) {
            case MineItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_name, item.getText());
                /*
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                */
                break;
            case MineItemType.ITEM_LINE:
                /*
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                 */
                break;
            default:
                break;
        }
    }

}
