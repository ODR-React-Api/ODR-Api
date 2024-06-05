package com.web.app.domain.getSelectListInfo;

import java.util.Comparator;

public class ReturnComparator implements Comparator<ReturnData> {
    /**
     * 要対応有無の降順 かつ 対応期日の昇順で結合後の２次元配列（もしくはリスト）をソートして、戻す。
     * 
     * @author DUC 郝建润
     * @since 2024/06/04
     * @version 1.0
     */
    @Override
    public int compare(ReturnData o1, ReturnData o2) {
        if (o1.getRequiredSupport() == (o2.getRequiredSupport())) {
            // 対応期日の昇順
            return o1.getCorrespondingDate().compareTo(o2.getCorrespondingDate());
        }
        // 要対応有無の降順
        return Integer.compare(o2.getRequiredSupport(), o1.getRequiredSupport());
    }
}
