package com.common.util;


import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: com.api.common
 * @Description: MD5
 * @Author: tianjian
 * @CreateDate: 2019/1/29
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/29
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class MD5Util {

    private static String key;

    public static String getMD5Pass(String password) throws Exception {

        if(StringUtils.isEmpty(key)) {
            throw new Exception("key is not null");
        }

        String value = Hashing.hmacMd5((key).getBytes()).hashBytes(password.getBytes()).toString();

        return Hashing.hmacMd5((key).getBytes()).hashBytes(value.getBytes()).toString();
    }

    public static void setKey(String key) {
        MD5Util.key = key;
    }

    public static String Md5FileCode(File file, String MD5_KEY) throws IOException {

        HashFunction hashFunction = Hashing.hmacMd5(MD5_KEY.getBytes());

        HashCode hashCode = Files.asByteSource(file).hash(hashFunction);

        return hashCode.toString();
    }
}