package com.amazon.LSR.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.LSR.model.PairHourPercent;

import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

@Service("kMeanClusteringService")
public class KMeanClusteringServiceImpl implements KMeanClusteringService {

	private PackageService packageService;

	public KMeanClusteringServiceImpl() {
		super();
	}

	@Autowired
	public KMeanClusteringServiceImpl(PackageService packageService) {
		super();
		this.packageService = packageService;
	}

	private List<PairHourPercent> kMeansClustering(List<Double> listTimeDiff, int seedVal, int clusterSize) {

		Map<Integer, Double> mapOfHourPercent = new HashMap<>();

		List<PairHourPercent> listOfHourPercent = new ArrayList<PairHourPercent>();

		Attribute attr1 = new Attribute("attr1");

		ArrayList<Attribute> attrList = new ArrayList<Attribute>();

		attrList.add(attr1);

		Instances dataset = new Instances("data", attrList, 0);

		for (Double d : listTimeDiff) {
			Instance inst = new DenseInstance(1);
			inst.setValue(attr1, d);
			inst.setDataset(dataset);
			dataset.add(inst);
		}

		SimpleKMeans kmeans = new SimpleKMeans();
		try {
			kmeans.setPreserveInstancesOrder(true);
			kmeans.setNumClusters(clusterSize);
			kmeans.setSeed(seedVal);
			kmeans.setDontReplaceMissingValues(true);
			kmeans.buildClusterer(dataset);
			kmeans.setMaxIterations(10);
			Instances instances = kmeans.getClusterCentroids();

			int assignments[] = kmeans.getAssignments();

			for (int assignment : assignments) {
				Long key = Math.round(instances.get(assignment).value(attr1));
				int length = assignments.length;

				if (mapOfHourPercent.containsKey(key.intValue()) == false)

					mapOfHourPercent.put(key.intValue(), (100.0 / length));// putting in percentages and hours in a map

				else

					mapOfHourPercent.replace(key.intValue(), mapOfHourPercent.get(key.intValue()) * 2);

			}

			for (Map.Entry<Integer, Double> entry : mapOfHourPercent.entrySet()) {
				PairHourPercent php = new PairHourPercent();

				php.setHours(entry.getKey());

				php.setPercentage(entry.getValue());

				listOfHourPercent.add(php);
			}

		} catch (Exception e) {
			System.out.println("Exception while generating distribution using K means");
		}

		return listOfHourPercent;

	}

	@Override
	public Map<String,List<PairHourPercent>> getPackTimeDistribution(Map<String,List<Double>> mapOfTimeDiff ,int seedVal, int clusterSize )
	
	{
	 
		Map<String,List<PairHourPercent>>  mapOfTimeDistriution= new HashMap<String, List<PairHourPercent>>();
		
		
		
		for(Map.Entry<String,List<Double>> entry : mapOfTimeDiff.entrySet())
		{
			mapOfTimeDistriution.put(entry.getKey(),kMeansClustering(entry.getValue(), seedVal, clusterSize));
			
		}
		
		
		return mapOfTimeDistriution;
		
	}
	
	
	
	public PackageService getPackageService() {
		return packageService;
	}

	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}
}
