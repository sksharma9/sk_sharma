package com.amazon.LSR.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazon.LSR.model.PairHourPercent;

import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Test3 {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Double A[] = new Double[] { 0.45, 1.2, 1.6, 1.9, 4.3, 5.2, 5.3, 5.8, 6.3, 6.7, 7.4, 7.9, 24.56 };

		List<Double> list = Arrays.asList(A);

		Attribute attr1 = new Attribute("attr1");
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();
		attrList.add(attr1);

		Instances dataset = new Instances("test", attrList, 0);

		for (Double d : list) {
			Instance inst = new DenseInstance(1);
			inst.setValue(attr1, d);
			inst.setDataset(dataset);
			dataset.add(inst);
		}

		SimpleKMeans kmeans = new SimpleKMeans();
		try {
			kmeans.setPreserveInstancesOrder(true);
			kmeans.setNumClusters(4);
			kmeans.setSeed(20);
			kmeans.setDontReplaceMissingValues(true);
			kmeans.buildClusterer(dataset);
			kmeans.setMaxIterations(10);
			Instances instances = kmeans.getClusterCentroids();
			int assignments[] = kmeans.getAssignments();

			Double array[] = new Double[4];

			Map<Integer,Double> mapOfHourPercent= new HashMap<>();
			
		//	System.out.println(Math.round(instances.get(0).value(attr1)));
			
			
			  for(int assignment : assignments) {
				  
				  Long key=Math.round(instances.get(assignment).value(attr1));
				
				  int length= assignments.length;
			  if(mapOfHourPercent.containsKey(key.intValue())==false)
			  {	  
				  mapOfHourPercent.put( key.intValue(),(100.0/length));
				   
			  }
			  else
			  {
				  mapOfHourPercent.replace( key.intValue(),mapOfHourPercent.get(key.intValue())+(100.0/length));
			  }
			 }
			 
			  
			/*
			 * for(Map.Entry<Integer, Double> entry:mapOfHourPercent.entrySet()) {
			 * System.out.println(entry.getKey()+ " "+ entry.getValue()); }
			 */
			  
			 	List<PairHourPercent> listOfHourPercent= new ArrayList<PairHourPercent>();
			  
			    for(Map.Entry<Integer, Double> entry:mapOfHourPercent.entrySet())
			     {
		        	PairHourPercent php= new PairHourPercent();
		        	
		        	php.setHours(entry.getKey());
		        	
		        	php.setPercentage(entry.getValue());
		        	
		    	    listOfHourPercent.add(php);			  
			     }
		            
		        
		        for(PairHourPercent p: listOfHourPercent)
		        	
		        {
		        	System.out.println(p.getHours() + " "+ p.getPercentage());
		        }
		           

			 

			for (int i = 0; i < assignments.length; i++) {
				System.out.println(assignments[i]);
			}

			for (int i = 0; i < instances.size(); i++) {
				System.out.println(instances.get(i));
			}

			int x = 0;
			for (int assignment : assignments) {
				System.out.println("data :" + dataset.get(x) + " instance idx: " + x + " centroid value: "
						+ instances.get(assignment));
				x++;
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}

	}

}
