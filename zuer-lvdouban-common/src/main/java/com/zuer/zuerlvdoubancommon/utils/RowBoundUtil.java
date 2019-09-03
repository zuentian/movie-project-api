package com.zuer.zuerlvdoubancommon.utils;

import org.apache.ibatis.session.RowBounds;

public class RowBoundUtil {
    /**
     * 根据分页属性 生成 RowBounds
     * @param pageSize
     * @param pageIndex
     * @return
     */
    public static RowBounds getRowBounds(String pageSize,String pageIndex){
        int limit =Integer.valueOf(pageSize);
        int offset =Integer.valueOf(pageIndex )* limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return rowBounds;
    }
}
