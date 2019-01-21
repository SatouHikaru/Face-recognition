package com.satouhikaru.controller;

import com.satouhikaru.model.NaïveBayesClassifier;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class NaïveBayesController extends Controller {

	NaïveBayesClassifier naïveBayes;
	
	@Override
	public String buildModel(String trainSet, String testSet) {
		naïveBayes = new NaïveBayesClassifier();
		naïveBayes.build(trainSet);
		return naïveBayes.evaluate(testSet);
	}

}
