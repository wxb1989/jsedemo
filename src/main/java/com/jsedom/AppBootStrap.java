package com.jsedom;

import org.apache.log4j.Logger;

public class AppBootStrap {
    private static Logger logger = Logger.getLogger(AppBootStrap.class);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        System.out.println("运行成功。。");
//        logger.info("运行成功。。");
        String s = "abcdef";
        resStr(s,0,s.length()-1);
        int [] midArray={1,10,2,5,15,17};


    }


    public int  midSearch(int [] array){
        int a =0 ;
        int low = 0;
        int hight = array.length-1;
        while (low <= hight){
            int mid = hight-low >>> 1;

        }


        return a ;
    }

    /**
     * 递归倒序字符串
     * @param s
     * @param frist
     * @param last
     * @return
     */
    public static String resStr(String s , int frist,int last){
        char []array = s.toCharArray();
        if (frist >= last) {
                System.out.println(s.toString());
                return  s;
            }else{
                char temp =array[frist];
                array[frist]=array[last] ;
                array[last]= temp;
                String res = new String (array) ;
                return   resStr( res ,  frist+1, last-1);
            }
        }
}
