package shop.mtcoding.projectjobplan._core;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PagingUtil {
    private int totalCount;
    private int currentPage;

    public PagingUtil(int totalCount, int currentPage) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
    }
    public int getNextPage() {
        return this.currentPage + 1;
    }

    public int getPrevPage() {
        return this.currentPage - 1;
    }

    public boolean isFirst() {
        return this.currentPage == 1;
    }

    public int getTotalPageCount() {
        return (this.totalCount % Constant.PAGING_COUNT == 0) ?
                (this.totalCount / Constant.PAGING_COUNT) :
                (this.totalCount / Constant.PAGING_COUNT + 1);
    }

    public boolean isLast() {
        return this.currentPage == getTotalPageCount();
    }

    public List<Integer> getNumberList() {
        List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i <= getTotalPageCount(); i++) {
            numberList.add(i);
        }
        return numberList;
    }

//    public static boolean isFirst(int currentPage) {
//        return currentPage == 0 ? true : false;
//    }
//
//    public static boolean isLast(int currentPage, int totalCount) {
//        int totalPageCount = getTotalPageCount(totalCount);
//        return currentPage == totalPageCount - 1 ? true : false;
//    }
//
//    public static List<Integer> getPageList(int totalPageCount) {
//        List<Integer> pageList = new ArrayList<>();
//        for (int i = 0; i < totalPageCount; i++) {
//            pageList.add(i);
//        }
//        /* RJS
//        List<Integer> numberList = new ArrayList<>();
//        int allPage;
//        if (totalPage % 10 == 0) {
//            allPage = totalCount - 1;
//            for (int i = 1; i <= allPage; i++) {
//                numberList.add(i);
//                request.setAttribute("numberList", numberList);
//            }
//        } else if (totalPage % 10 != 0) {
//            allPage = totalCount;
//            for (int i = 1; i <= allPage; i++) {
//                numberList.add(i);
//                request.setAttribute("numberList", numberList);
//            }
//        }
//        */
//        return pageList;
//    }
//
//    public static int getTotalPageCount(int totalCount) {
//        int remainCount = totalCount % Constant.PAGING_COUNT;
//        int totalPageCount = totalCount / Constant.PAGING_COUNT;
//        if (remainCount > 0) {
//            totalPageCount += 1;
//        }
//        return totalPageCount;
//   }


}
