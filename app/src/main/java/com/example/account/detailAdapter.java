package com.example.account;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 黎俐 on 2018/9/9.
 * 明细页面的适配器
 */


public class detailAdapter extends BaseQuickAdapter<Detail_Item,BaseViewHolder>
{
    @Override
    protected void convert(BaseViewHolder helper, Detail_Item item) {
        helper.setImageResource(R.id.list_image,  item.getImageId());
        helper.setText(R.id.account_name,item.getName());
        helper.setText(R.id.account_sort,item.getSort());
        helper.setText(R.id.account_time,item.getTime());
        helper.setText(R.id.account_amount,item.getAmount());

    }


    public detailAdapter(int layoutResId, List data) {
        super(layoutResId,data);
    }


}