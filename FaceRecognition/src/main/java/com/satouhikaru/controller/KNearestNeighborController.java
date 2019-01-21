package com.satouhikaru.controller;

import com.satouhikaru.model.KNearestNeighborClassifier;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class KNearestNeighborController extends Controller {

	KNearestNeighborClassifier kNN;
	
	@Override
	public String buildModel(String trainSet, String testSet) {
		String parameter = String.format("-K 5 -W 0 -A \"weka.core.neighboursearch."
				+ "LinearNNSearch -A \\\"weka.core.EuclideanDistance -R first-last\\\"\"");
		try {
			kNN = new KNearestNeighborClassifier(parameter);
			kNN.build(trainSet);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return kNN.evaluate(testSet);
	}

}