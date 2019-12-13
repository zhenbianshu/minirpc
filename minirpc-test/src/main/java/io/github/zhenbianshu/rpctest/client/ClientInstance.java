package io.github.zhenbianshu.rpctest.client;

import io.github.zhenbianshu.rpctest.api.HelloService;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author zbs
 * @date 2019/9/5
 */
public class ClientInstance {
    public static void main(String[] args) {
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:client.xml");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        String response = helloService.say("zbs");
        System.out.println(response);
    }
}
