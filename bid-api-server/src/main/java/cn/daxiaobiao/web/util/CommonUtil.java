package cn.daxiaobiao.web.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cheng on 2016/2/26.
 */
public class CommonUtil {
    public static boolean isPhone(String phone){

        if (StringUtils.isEmpty(phone) || phone.length() != 11){
            return false;
        }
        String regExp = "^[1][3-9][0-9]{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        if (m.find()){
            return true;
        }
        return false;
    }

    public static void main(String args[]){
        System.out.println("isPhone: "+isPhone("133456789101"));
    }
}
