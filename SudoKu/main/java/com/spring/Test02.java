package com.spring;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/6 21:20
 */
/*
* 获取泛型信息类！
* */
public class Test02 {

    public void test01(Map<String,Person> map,List<String> list){
        System.out.println("1");
    }

    public List<String> test02(){
        System.out.println("2");
        return  null;
    }


    public static void main(String[] args) {
        try {

            Method test01 = Test02.class.getMethod("test01", Map.class, List.class);

//            test01.setAccessible(true);//关闭安全检测

            Type[] genericParameterTypes = test01.getGenericParameterTypes();//获取泛型的参数类型
            for (Type genericParameterType : genericParameterTypes) {
                System.out.println("获取泛型的参数类型:  "+genericParameterType);
                if(genericParameterType instanceof ParameterizedType){
                    Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                    for (Type actualTypeArgument : actualTypeArguments) {
                        System.out.println(actualTypeArgument);
                    }
                }
            }

            System.out.println("========!!!!!!!!!++++++++========");
            //Generic泛型！！！！
            Method method = Test02.class.getMethod("test02", null);
            Type genericReturnType = method.getGenericReturnType();
//            method.get
            System.out.println(genericReturnType);
            if (genericReturnType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }

        } catch (NoSuchMethodException e) {
            System.out.println("错误");
        }
    }
}

/*


通过反射获取类的信息：
1、getMethod()获取方法
2、getField()获取字段属性

大致流程：
        1、通过反射获取到类的信息，并getMethod指定获取其方法
        2、
        3、

*/
