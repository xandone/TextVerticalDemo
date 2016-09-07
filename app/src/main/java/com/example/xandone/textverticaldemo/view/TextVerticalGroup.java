package com.example.xandone.textverticaldemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xandone.textverticaldemo.R;
import com.example.xandone.textverticaldemo.utils.StringUtils;


/**
 * Created by admin on 2016/9/6.
 */
public class TextVerticalGroup extends RelativeLayout {

    public static final int SENTENCE_FIVE = 0x001;
    public static final int SENTENCE_SEVEN = 0x002;
    public static final int SENTENCE_COMMA = 0x003;
    public static final int SENTENCE_NORMAL = 0x004;

    public static final String WRAP = "\n";
    private String[] arr;
    private TextView[] tvArr;

    private int groupSentence;
    private String groupContent;
    private int groupColor;
    private int groupSize;

    private int maxHeight;

    private Context context;
    Typeface typeface;

    public TextVerticalGroup(Context context) {
        this(context, null);
    }

    public TextVerticalGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextVerticalGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ziti.ttf");

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextVerticalGroup, defStyleAttr, 0);
        groupSentence = typedArray.getInteger(R.styleable.TextVerticalGroup_group_sentence, SENTENCE_NORMAL);
        groupContent = typedArray.getString(R.styleable.TextVerticalGroup_group_content);
        groupSize = typedArray.getDimensionPixelSize(R.styleable.TextVerticalGroup_group_size, 30);
        groupColor = typedArray.getColor(R.styleable.TextVerticalGroup_group_color, Color.BLACK);
        typedArray.recycle();
    }

    /**
     * 文字内容和句式
     *
     * @param text
     * @param sentence
     */
    public void setContent(String text, int sentence) {
        //不符合要求
        if (StringUtils.isEmpty(text) || sentence < SENTENCE_FIVE || sentence > SENTENCE_NORMAL) {
            return;
        }
        setSentence(text, sentence);
    }

    /**
     * 内容文字颜色
     *
     * @param color
     */
    public void setContentColor(int color) {
        this.groupColor = color;
    }

    /**
     * 内容文字大小
     *
     * @param size
     */
    public void setContentSize(int size) {
        this.groupSize = size;
    }

    /**
     * 设置句式
     *
     * @param sentence
     */
    public void setSentence(String text, int sentence) {
        if (StringUtils.isEmpty(text)) {
            return;
        }
        switch (sentence) {
            case SENTENCE_FIVE:
                dealFiveOrSeven(text, 5);
                break;
            case SENTENCE_SEVEN:
                dealFiveOrSeven(text, 7);
                break;
            case SENTENCE_COMMA:
                dealComma(text);
                break;
            case SENTENCE_NORMAL:
                dealNormal(text, groupSize);
                break;
        }
        initTextView();
    }

    /**
     * 处理五言绝句和七言律诗
     *
     * @param str
     * @param num
     */
    public void dealFiveOrSeven(String str, int num) {
        String noSymbolStr = StringUtils.cleanSymbol(str);
        if (StringUtils.isEmpty(noSymbolStr))
            return;
        int len = noSymbolStr.length();
        arr = new String[len / num + 1];
        for (int i = 0; i < arr.length; i++) {
            if (i * num < len && i * num + num <= len) {
                arr[i] = noSymbolStr.substring(i * num, i * num + num);
            }
            if (i * num < len && i * num + num > len) {
                arr[i] = noSymbolStr.substring(i * num, len);
            }
        }
    }

    /**
     * 处理词曲，以逗号为分割
     *
     * @param str
     */
    public void dealComma(String str) {
        char ch[] = str.toCharArray();
        int len = 0;
        for (char c : ch) {
            if (c == ',' || c == '，') {
                len++;
            }
        }
        arr = new String[len + 1];
        int count = 0;
        int teap = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == ',' || str.charAt(i) == '，') {
                arr[count] = str.substring(teap, i);
                teap = i + 1;//下次从i+1开始分割,去除逗号
                count++;
            }
        }
        arr[count] = str.substring(teap, str.length());
    }

    /**
     * 处理普通语句
     *
     * @param str
     */
    public void dealNormal(String str, int size) {
        int len = str.length();
        //每列可容纳的字数
        int num = maxHeight / StringUtils.sp2px(context, size);
        if (0 == num) {
            return;
        } else {
            arr = new String[len / num + 1];
        }
        for (int i = 0; i < arr.length; i++) {
            if (i * num < len && i * num + num <= len) {
                arr[i] = str.substring(i * num, i * num + num);
            }
            if (i * num < len && i * num + num > len) {
                arr[i] = str.substring(i * num, len);
            }
        }
    }

    /**
     * 添加回车
     *
     * @param str
     * @return
     */
    public String addWrap(String str) {
        if (StringUtils.isEmpty(str))
            return null;
        char[] ch = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : ch) {
            sb.append(c);
            sb.append(WRAP);
        }
        return sb.toString();
    }

    /**
     * 添加TextView
     */
    public void initTextView() {
        tvArr = new TextView[arr.length];
        for (int i = 0; i < tvArr.length; i++) {
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lp.rightMargin = i * StringUtils.sp2px(context, groupSize);
            tvArr[i] = new TextView(context);
            tvArr[i].setTextSize(groupSize);
            tvArr[i].setTextColor(groupColor);
            tvArr[i].setTypeface(typeface);
            tvArr[i].setText(addWrap(arr[i]));
            tvArr[i].setLayoutParams(lp);
            this.addView(tvArr[i]);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightSpecMode == MeasureSpec.AT_MOST) {
            maxHeight = 400;
        } else {
            maxHeight = heightMeasureSpec;
        }
        setContent(groupContent, groupSentence);
    }
}
