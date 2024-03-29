package shop.mtcoding.projectjobplan._core.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PagingUtil {

    public static <T> Page<T> getPage(List<T> dataList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dataList.size());

        List<T> pageData = new ArrayList<>();
        if (start <= end) {
            pageData = dataList.subList(start, end);
        }

        return new PageImpl<>(pageData, pageable, dataList.size());
    }
}
