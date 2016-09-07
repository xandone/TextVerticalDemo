package com.example.xandone.textverticaldemo.utils;

import android.content.Context;

/**
 * Created by admin on 2016/8/10.
 */
public class StringUtils {
    public static final char[] myChar = {' ', '\n', '\t', ',', '，', '.', '。', '【', '[', ']', '】', '"', '“', '”', '"', '-',
            '-', '！', '!', '~', '~', '@', '$', '￥', '%', '%', '^', '&', '(', '（', '）', ')', '?', '？', '/', '、', '、', '<',
            '《', '》', '>', '+', '_', '`', ';', '；'};

    public static boolean isEmpty(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ' && c != '\t' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 清除字符串中的标点符号
     *
     * @param str
     * @return
     */
    public static String cleanSymbol(String str) {
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        if (isEmpty(str))
            return null;
        for (char ch : str.toCharArray()) {
            flag = true;
            for (char c : myChar) {
                if (ch == c) {
                    flag = false;
                }
            }
            if (flag) {
                sb.append(ch);
            }
        }
        //若输入的只有标点
        if (isEmpty(sb.toString())) {
            return null;
        }
        return sb.toString();
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
