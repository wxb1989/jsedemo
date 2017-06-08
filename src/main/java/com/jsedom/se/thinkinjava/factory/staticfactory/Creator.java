/**
 * 工厂类
 * Created by Administrator on 2017/5/23.
 */
package com.jsedom.se.thinkinjava.factory.staticfactory;

public class Creator {
    private Creator(){}

    /**
     * 工厂方法
     * @param productName
     * @return
     */
    public static IProduct createProduct(String productName){
        if (productName == null) {
            return null;
        }
        if (productName.equals("A")) {
            return new ProductA();
        }else if (productName.equals("B")) {
            return new ProductB();
        }else {
            return null;
        }
    }
}
