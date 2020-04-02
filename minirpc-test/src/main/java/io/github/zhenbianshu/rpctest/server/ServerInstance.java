package io.github.zhenbianshu.rpctest.server;

import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author zbs
 * @date 2019/10/8
 */
public class ServerInstance {
    public static void main(String[] args) {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("classpath:server.xml");
        System.out.println("Server Started");
    }
}
