package com.satouhikaru.model;

import java.text.DecimalFormat;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class ArtificialNeuralNetworkClassifier extends AbstractClassifier {

	MultilayerPerceptron ann;
	
	public ArtificialNeuralNetworkClassifier() {
	}
	
	@Override
	public void build(String fileName) {
		try {
			setTrainSet(fileName);
			trainSet.setClassIndex(trainSet.numAttributes() - 1);
			ann = new MultilayerPerceptron();
			ann.setOptions(modelOptions);
			ann.buildClassifier(trainSet);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public String evaluate(String fileName) {
		double result = 0;
		try {
			setTestSet(fileName);
			testSet.setClassIndex(testSet.numAttributes() - 1);
			Evaluation evaluation = new Evaluation(trainSet);
			evaluation.evaluateModel(ann, testSet);
			result = evaluation.pctCorrect();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		return decimalFormat.format(result) + "%";
	}
	
}