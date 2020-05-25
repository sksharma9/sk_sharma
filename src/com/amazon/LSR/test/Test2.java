package com.amazon.LSR.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Test2 {

	public static void main(String[] args) throws Exception {

		double[] A =   { 0.45, 1.2, 1.6, 1.9, 2.5, 2.8, 4.3, 5.2, 5.3, 5.8, 6.3, 6.7, 7.4, 7.9 };
		
		

		List<Double> list = Arrays.asList(A);

		SimpleKMeans kmeans = new SimpleKMeans();

		kmeans.setSeed(10);

		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(3);

		Attribute a = new Attribute("first");

		ArrayList<Attribute> attrList = new ArrayList<Attribute>();
		attrList.add(a);

		Instances dataset = new Instances("new", attrList, 0);

		
		
		 Instance i= new DenseInstance(1.0, A);  
		

	}

}
