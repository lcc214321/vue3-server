package com.luocc.vue3.server.util;

import com.luocc.vue3.server.bean.vo.PageInfo;

import java.util.List;
import java.util.Objects;

public class PageUtil {

    public static <T> PageInfo<T> build(Integer pageNo, Integer pageSize, List<T> list, Integer total) {

        // 封装pageInfo
        if (Objects.isNull(pageNo)) {
            pageNo = 1;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = total;
        }

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(list);
        pageInfo.setTotal(total);
        if (total != 0) {
            pageInfo.setPages((total - 1) / pageSize + 1);
        }
        pageInfo.setHasPreviousPage(pageNo > 1);
        pageInfo.setHasNextPage(pageNo < pageInfo.getPages());
        pageInfo.setIsFirstPage(pageNo == 1);
        pageInfo.setIsLastPage(pageNo == pageInfo.getPages() || pageInfo.getPages() == 0);
        pageInfo.setPrePage((pageNo > 1) ? (pageNo - 1) : pageNo);
        pageInfo.setNextPage((pageNo < pageInfo.getPages()) ? (pageNo + 1) : pageInfo.getPages());

        return pageInfo;
    }

    public static Integer getStartRow(Integer pageNo, Integer pageSize) {
        return (pageNo - 1) * pageSize;
    }

}
