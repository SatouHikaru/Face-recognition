package com.satouhikaru.controller;

import com.satouhikaru.model.ArtificialNeuralNetworkClassifier;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class ArtificialNeuralNetworkController extends Controller {

	ArtificialNeuralNetworkClassifier ann;
	
	@Override
	public String buildModel(String trainSet, String testSet) {
		ann = new ArtificialNeuralNetworkClassifier();
		ann.build(trainSet);
		return ann.evaluate(testSet);
	}

}