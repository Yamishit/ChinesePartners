package com.clannad.common.commonutils;

import java.util.Collection;

/**
 * Created by F_ck on 2016/10/11.
 * 集合操作工具
 */

public class CollectionUtils {
    /**
     * 判断集合是否为null或者0个元素
     *
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
}
