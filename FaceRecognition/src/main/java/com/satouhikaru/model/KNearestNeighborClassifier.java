package com.satouhikaru.model;

import java.text.DecimalFormat;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class KNearestNeighborClassifier extends AbstractClassifier {

	private IBk kNN;

	/**
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @param modelOptions
	 * @throws Exception
	 */
	public KNearestNeighborClassifier(String modelOptions) throws Exception {
		super(modelOptions);
	}

	@Override
	public void build(String fileName) {
		try {
			setTrainSet(fileName);
			trainSet.setClassIndex(trainSet.numAttributes() - 1);
			kNN = new IBk();
			kNN.setOptions(modelOptions);
			kNN.buildClassifier(trainSet);
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
			evaluation.evaluateModel(kNN, testSet);
			result = evaluation.pctCorrect();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		return decimalFormat.format(result) + "%";
	}

}