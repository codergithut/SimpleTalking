package com.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ProjectName: com.common.util
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/27
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/27
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class IpUtil {
    public static String getLocalIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }
}
