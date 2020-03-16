package com.github.jconrad.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanCopier {
//    private static Mapper mapper = null;

    static {
//        mapper = new DozerBeanMapper(); // can reuse, share globally
    }

    private BeanCopier() {
    }

//    /**
//     * null值不做移动
//     * @param <T>					     目标对象类型
//     * @param sourceObject		                源对象
//     * @param destinationObject		     目标对象
//     */
//    public static <T> T copyIgnoreNull(Object sourceObject, T destination) {
//        if (sourceObject == null) {
//            return null;
//        }
//        copy(sourceObject, destination);
//        return destination;
//    }
//
//    /**
//     *
//     * @param <T>                     目标对象类型
//     * @param sourceObject		                源对象
//     * @param destinationObjectClass  目标对象类
//     * @return                        目标对象
//     */
//    public static <T> T copy(Object sourceObject, Class<T> destinationObjectClass) {
//        if (sourceObject == null) {
//            return null;
//        }
//        return mapper.map(sourceObject, destinationObjectClass);
//    }
//
//    /**
//     *
//     * @param <T>					     目标对象类型
//     * @param sourceObject		                源对象
//     * @param destinationObject		     目标对象
//     */
//    public static <T> T copy(Object sourceObject, T destination) {
//        if (sourceObject == null) {
//            return null;
//        }
//        mapper.map(sourceObject, destination);
//        return destination;
//    }

//    /***
//     * 把一个List内的所有对象转换为目标对象
//     * @param <FROM>
//     * @param <TO>
//     * @param sourceObjects            源对象
//     * @param destinationClass         目标类型
//     * @return
//     */
//    public static <FROM, TO> List<TO> copy(List<FROM> sourceObjects, Class<TO> destinationClass) {
//        if (sourceObjects == null) {
//            return null;
//        }
//        List<TO> destinations = new ArrayList<TO>();
//        for (FROM from : sourceObjects) {
//            destinations.add(copy(from, destinationClass));
//        }
//        return destinations;
//    }

    /**
     * 类属性拷贝
     * @param from
     * @param to
     * @param ignoreProperties	需要忽略的属性
     */
    public static void copyProperties(Object from, Object to, String... ignoreProperties) {
        BeanUtils.copyProperties(from, to, ignoreProperties);
    }
}
