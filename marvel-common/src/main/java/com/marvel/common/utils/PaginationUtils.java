package com.marvel.common.utils;

/**
 * @Classname PaginationUtils
 * @Description 分页计算
 * @Author andy
 * @Date 2019/4/22 下午10:58
 * @Version 1.0
 */
public class PaginationUtils {

    /**
     * 分页时计算是否有下一页
     *
     * @param total  总条数
     * @param cursor 当前页码
     * @param count  每页条数
     * @return
     */
    public static long nextCursor(int cursor, int count, long total) {

        if (total == 0 || count == 0) {
            return -1;
        }

        long totalPage = 0;
        //取余数
        long remainder = total % count;
        //如果有余数，总页码+1
        if (remainder > 0) {
            totalPage = total / count + 1;
        }
        if (totalPage > cursor) {
            return cursor + 1;
        } else {
            return -1;
        }
    }
}
