package com.example.administrator.sample.two;

/**
 * Created by shizi on 17/5/26.
 */

public interface MZHolderCreator<VH extends MZViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}
