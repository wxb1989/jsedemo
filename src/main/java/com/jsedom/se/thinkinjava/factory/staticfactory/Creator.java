/**
 * 工厂类
 * Created by Administrator on 2017/5/23.
 */
package com.jsedom.se.thinkinjava.factory.staticfactory;

/**
 * @author Administrator
 */
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
        if ("A".equals(productName)) {
            return new ProductA();
        }else if ("B".equals(productName)) {
            return new ProductB();
        }else {
            return null;
        }
    }
}
