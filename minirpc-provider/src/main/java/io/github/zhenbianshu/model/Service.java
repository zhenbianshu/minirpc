package io.github.zhenbianshu.model;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author zbs
 * @date 2019/9/9
 */
@Data
@Builder
public class Service {
    private Integer id;
    private Object classObject;
    private Method method;
}
