package com.common.util;

import java.util.Arrays;

/**
 * @ProjectName: com.common.util
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/19
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/19
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class StringSortUtil {
    /**
     * 将keys进行字典排序 key A,B 和 Key B,A 是等价的
     * @param keys 需要排序的key数组
     * @return
     */
    public static String getKeyByKeys(String[] keys) {
        StringBuffer key_build = new StringBuffer();
        if(keys.length > 0) {
            Arrays.sort(keys);
        } else {
            return "";
        }

        for(String key : keys) {
            key_build.append(key + "-");
        }

        return key_build.toString();



    }

}
