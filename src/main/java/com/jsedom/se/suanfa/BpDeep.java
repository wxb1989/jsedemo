package com.jsedom.se.suanfa;

import java.util.Arrays;
import java.util.Random;

/*
 * �����������������ʵ�� 
 */
public class BpDeep {
	
    public double[][] layer;//���������ڵ�  
    public double[][] layerErr;//��������ڵ����  
    public double[][][] layer_weight;//����ڵ�Ȩ��  
    public double[][][] layer_weight_delta;//����ڵ�Ȩ�ض���  
    public double mobp;//����ϵ��  
    public double rate;//ѧϰϵ��  
    
    public BpDeep(int[] layernum, double rate, double mobp){  
        this.mobp = mobp;  
        this.rate = rate;  
        layer = new double[layernum.length][];  
        layerErr = new double[layernum.length][];  
        layer_weight = new double[layernum.length][][];  
        layer_weight_delta = new double[layernum.length][][];  
        Random random = new Random();  
        for(int l=0;l<layernum.length;l++){  
            layer[l]=new double[layernum[l]];  
            layerErr[l]=new double[layernum[l]];  
            if(l+1<layernum.length){  
                layer_weight[l]=new double[layernum[l]+1][layernum[l+1]];  
                layer_weight_delta[l]=new double[layernum[l]+1][layernum[l+1]];  
                for(int j=0;j<layernum[l]+1;j++)  
                    for(int i=0;i<layernum[l+1];i++)  
                        layer_weight[l][j][i]=random.nextDouble();//�����ʼ��Ȩ��  
            }     
        }  
    }  
    //�����ǰ�������  
    public double[] computeOut(double[] in){  
        for(int l=1;l<layer.length;l++){  
            for(int j=0;j<layer[l].length;j++){  
                double z=layer_weight[l-1][layer[l-1].length][j];  
                for(int i=0;i<layer[l-1].length;i++){  
                    layer[l-1][i]=l==1?in[i]:layer[l-1][i];  
                    z+=layer_weight[l-1][i][j]*layer[l-1][i];  
                }  
                layer[l][j]=1/(1+Math.exp(-z));  
            }  
        }  
        return layer[layer.length-1];  
    }  
    //��㷴��������޸�Ȩ��  
    public void updateWeight(double[] tar){  
        int l=layer.length-1;  
        for(int j=0;j<layerErr[l].length;j++)  
            layerErr[l][j]=layer[l][j]*(1-layer[l][j])*(tar[j]-layer[l][j]);  
  
        while(l-->0){  
            for(int j=0;j<layerErr[l].length;j++){  
                double z = 0.0;  
                for(int i=0;i<layerErr[l+1].length;i++){  
                    z=z+l>0?layerErr[l+1][i]*layer_weight[l][j][i]:0;  
                    layer_weight_delta[l][j][i]= mobp*layer_weight_delta[l][j][i]+rate*layerErr[l+1][i]*layer[l][j];//�����㶯������  
                    layer_weight[l][j][i]+=layer_weight_delta[l][j][i];//������Ȩ�ص���  
                    if(j==layerErr[l].length-1){  
                        layer_weight_delta[l][j+1][i]= mobp*layer_weight_delta[l][j+1][i]+rate*layerErr[l+1][i];//�ؾද������  
                        layer_weight[l][j+1][i]+=layer_weight_delta[l][j+1][i];//�ؾ�Ȩ�ص���  
                    }  
                }  
                layerErr[l][j]=z*layer[l][j]*(1-layer[l][j]);//��¼���  
            }  
        }  
    }  
  
    public void train(double[] in, double[] tar){  
        double[] out = computeOut(in);  
        updateWeight(tar);  
    }  
    
    public static void main(String[] args){  
        //��ʼ��������Ļ�������  
        //��һ��������һ���������飬��ʾ������Ĳ�����ÿ��ڵ���������{3,10,10,10,10,2}��ʾ�������3���ڵ㣬�������2���ڵ㣬�м���4�������㣬ÿ��10���ڵ�  
        //�ڶ���������ѧϰ�����������������Ƕ���ϵ��  
        BpDeep bp = new BpDeep(new int[]{2,10,2}, 0.15, 0.8);  
  
        //�����������ݣ���Ӧ�����4����ά��������  
        double[][] data = new double[][]{{1,2},{2,2},{1,1},{2,1}};  
        //����Ŀ�����ݣ���Ӧ4���������ݵķ���  
        double[][] target = new double[][]{{1,0},{0,1},{0,1},{1,0}};  
  
        //����ѵ��5000��  
        for(int n=0;n<5000;n++)  
            for(int i=0;i<data.length;i++)  
                bp.train(data[i], target[i]);  
  
        //����ѵ�������������������  
        for(int j=0;j<data.length;j++){  
            double[] result = bp.computeOut(data[j]);  
            System.out.println(Arrays.toString(data[j])+":"+Arrays.toString(result));  
        }  
  
        //����ѵ�������Ԥ��һ�������ݵķ���  
        double[] x = new double[]{3,1};  
        double[] result = bp.computeOut(x);  
        System.out.println(Arrays.toString(x)+":"+Arrays.toString(result));  
    }  
}
