package cn.daxiaobiao.core.util;

import cn.daxiaobiao.core.bEnum.BidTypeEnum;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by cheng on 2015/11/20.
 */
public class TypeRegexUtil {

    private final static Pattern ZHAOB = Pattern.compile(".*招标.*");
    private final static Pattern ZHONGB = Pattern.compile(".*中标.*");
    private final static Pattern BIANGEN = Pattern.compile(".*(变更|更正).*");
    private final static Pattern NEWS = Pattern.compile(".*(新闻|资讯).*");

    /**
     * 类型字符串 转化成 类型枚举
     * @param typeStr
     * @return
     */
    public static BidTypeEnum getBidTypeEnum(String typeStr){

        if(StringUtils.isEmpty(typeStr)){
            return BidTypeEnum.OTHER;
        }
        typeStr= typeStr.trim();
        if (ZHAOB.matcher(typeStr).find()){
            return BidTypeEnum.ZHAOBIAO;
        }else if(ZHONGB.matcher(typeStr).find()){
            return BidTypeEnum.ZHONGBIAO;
        }else if (BIANGEN.matcher(typeStr).find()){
            return BidTypeEnum.BIANGEN;
        } else if (NEWS.matcher(typeStr).find()){
            return BidTypeEnum.NEW;
        } else {
            return BidTypeEnum.OTHER;
        }
    }
}
