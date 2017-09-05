package com.jsedom.se.myclassloder;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的类加载器.
 * 当entrust为true时,该类加载完全遵循双亲委托模型,类加载器继承的顺序如下:
 *      bootstrap class loader
 *                ||
 *                ||
 *         ext class loader
 *                ||
 *                ||
 *        system class loader
 *                ||
 *                ||
 *      application class loader
 *
 * 当entrust为false时,该类加载不完全遵循双亲委托模型,类加载器继承的顺序如下:
 *      bootstrap class loader
 *                ||
 *                ||
 *         ext class loader
 *                ||
 *                ||
 *        application class loader
 *                ||
 *                ||
 *        system class loader
 *
 * 注意:如果是niubi-job本身的类,则无论entrust是否为true,都将优先由parent classloader加载.
 *
 * @author Xiaolong Zuo
 * @since 0.9.3
 */
public class MyClassLoader extends URLClassLoader {

    private Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    /**
     * 由于bootstrap级别的类加载器无法被java程序获取到,因此该类加载通常是ext级别的类加载器
     */
    private ClassLoader javaClassLoader;
    private boolean entrust;


    public MyClassLoader( ClassLoader parent, boolean entrust) {
        super(new URL[]{}, parent);
        if(getParent()==null){
            throw new IllegalArgumentException("parent can't be null.");
        }

        ClassLoader classLoader = String.class.getClassLoader();
        if(classLoader==null){
            classLoader=getSystemClassLoader();
            while (classLoader!=null){
                classLoader=classLoader.getParent();
            }
        }
        this.entrust=entrust;
        this.javaClassLoader=classLoader;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = classMap.get(name);
        if(clazz!=null){
            return clazz;
        }
        synchronized (getClassLoadingLock(name)) {



            throw new ClassNotFoundException();
        }

    }


}
