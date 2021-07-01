package com.example.geekbang.classloader;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 参考作业demo
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，
 * 执行 hello 方法，
 * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 */
public class CustomizeClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        final String className = "Hello";
        final String methodName = "hello";
        //创建类加载器
        ClassLoader classLoader = new CustomizeClassLoader();
        //加载类
        Class<?> aClass = classLoader.loadClass(className);
        //查看类有什么方法
        for (Method declaredMethod : aClass.getDeclaredMethods()) {
            System.out.println(aClass.getSimpleName() + "." + declaredMethod.getName());
        }
        //创建对象
        Object instance = aClass.getDeclaredConstructor().newInstance();
        //调用实例方法
        Method method = aClass.getMethod(methodName);
        method.invoke(instance);
    }

    private static void close(Closeable res) {
        if (res != null) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //如果支持包名，需要进行路径转换
        String resourcePath = name.replace(".", "/");
        //文件后缀
        final String suffix = ".xlass";
        //获取输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);

        try {
            //去读数据
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            //转换
            byte[] classBytes = decode(byteArray);
            return this.defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
    }

    /**
     * 解码
     */
    private byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < targetArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

}


