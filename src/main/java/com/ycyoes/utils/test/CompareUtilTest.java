package com.ycyoes.utils.test;

import com.ycyoes.demos.basic.reflection.RichType;
import com.ycyoes.utils.CompareUtil;

public class CompareUtilTest {
    private static String getUserAvatarInfoCompareStatus(RichType oldRichType, RichType newRichType){
        CompareUtil util = new CompareUtil.Builder<RichType>()
                .current(newRichType)
                .original(oldRichType)
                .excludeFields(new String[]{"richType", "richField"})   //排除
                .build();
        return util.compare().toString();
    }

    public static void main(String[] args) {
        RichType richType1 = new RichType();
        RichType richType2 = new RichType();
        String status = getUserAvatarInfoCompareStatus(richType1, richType2);
        System.out.println("status: " + status);
    }
}
