package com;

/**
 * @ProjectName: com
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/7
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/7
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;

import static sun.misc.Version.print;

public class MD5Test {

    public static final String MD5_KEY = "file_md5_key";

    public static void main(String[] args) throws Exception {
        print();
        File file = new File(
                "D:/TenementSystem-dev.zip");
        HashFunction  hashFunction = Hashing.hmacMd5("MD5_KEY".getBytes());

        HashCode hashCode = Files.asByteSource(file).hash(hashFunction);
        System.out.println(hashCode.toString());
    }

}
