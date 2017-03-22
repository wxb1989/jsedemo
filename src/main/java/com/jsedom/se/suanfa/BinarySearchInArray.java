package com.jsedom.se.suanfa;

/**
 * 递归分治算法学习之二维二分查找
 *
 * @author Sking
 *         <p>
 *         问题描述：
 *         存在一个二维数组T[m][n]，每一行元素从左到右递增，
 *         每一列元素从上到下递增,现在需要查找元素X（必在二维
 *         数组中）在数组中的位置，要求时间复杂度不超过m+n.
 *         Created by Administrator on 2017/3/4.
 */
public class BinarySearchInArray {
    /**
     * 二维二分搜索的实现
     *
     * @param array 待查找的二维数组
     * @param value 待查找的元素
     * @param m1    数组左上角横坐标
     * @param n1    数组左上角纵坐标
     * @param m2    数组右下角横坐标
     * @param n2    数组右下角纵坐标
     * @return 待查找元素在二维数组中的位置索引，存在长度为2的数组中
     * 未找到则返回null。
     */
  static  int[] binarySearchInArray(int[][] array, int value, int m1, int n1, int m2,
                              int n2) {
        //(beginX,beginY)表示数组左上角坐标
        int beginX = m1, beginY = n1;
        //(endX,endY)表示数组右下角坐标
        int endX = m2, endY = n2;
        int[] leftResult = new int[2];//递归查找得到的左下角搜索结果
        int[] rightResult = new int[2];//递归查找得到的右上角搜索结果
        int i = (m1 + m2) / 2, j = (n1 + n2) / 2;//不是对角阵
        if (value < array[m1][n1] || value > array[m2][n2])
            return null;
        if (value == array[m1][n1])
            return new int[]{m1, n1};
        if (value == array[m2][n2])
            return new int[]{m2, n2};
        //子矩阵对角线方向上的二分查找，确定递归子矩阵
        while ((i != m1 || j != n1) && (i != m2 || j != n2)) {
            if (value == array[i][j])
                return new int[]{i, j};
            else if (value < array[i][j]) {
                m2 = i;
                n2 = j;
                i = (i + m1) / 2;
                j = (j + n1) / 2;
            } else {
                m1 = i;
                n1 = j;
                i = (i + m2) / 2;
                j = (j + n2) / 2;
            }
        }//如果找到则返回，否则对左下角和右上角矩阵进行递归查找
        if (i < endX)//右上角递归查找
            leftResult = binarySearchInArray(array, value, i + 1, beginY, endX, j);
        if (j < endY)//左下角递归查找
            rightResult = binarySearchInArray(array, value, beginX, j + 1, i, endY);
        if (leftResult != null)
            return leftResult;
        if (rightResult != null)
            return rightResult;
        return null;
    }

    private static int[][] sample = new int[][] { { 1, 2, 8, 9 },
            { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };

    public static void printSample() {
        for (int i = 0; i < sample.length; i++) {
            for (int j = 0; j < sample[i].length; j++) {
                System.out.print(sample[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean getValuefromMatrix(int[][] sample, int rows,
                                             int columns, int num) {
        boolean found = false;
        if (sample != null && rows > 0 && columns > 0) {
            int row = 0;
            int column = columns - 1;
            while (row < rows && column >= 0) {
                int tempValue = sample[row][column];
                if (num > tempValue) {
                    ++row;
                } else if (num < tempValue) {
                    --column;
                } else {
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public static void main(String[] args) {
        printSample();
        System.out.println(getValuefromMatrix(sample, 4, 4, 8));
        int a = 5; int b = 10;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a+ "   " + b);
    }
}
