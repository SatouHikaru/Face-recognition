package com.satouhikaru.model;

import java.text.DecimalFormat;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class NaïveBayesClassifier extends AbstractClassifier {

	NaiveBayes naïveBayes;

	public NaïveBayesClassifier() {
	}
	
	@Override
	public void build(String fileName) {
		try {
			setTrainSet(fileName);
			trainSet.setClassIndex(trainSet.numAttributes() - 1);
			naïveBayes = new NaiveBayes();
			naïveBayes.buildClassifier(trainSet);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public String evaluate(String fileName) {
		double result = 0;
		try {
			setTestSet(fileName);
			testSet.setClassIndex(testSet.numAttributes() - 1);
			Evaluation evaluation = new Evaluation(trainSet);
			evaluation.evaluateModel(naïveBayes, testSet);
			result = evaluation.pctCorrect();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		return decimalFormat.format(result) + "%";
	}
	
}