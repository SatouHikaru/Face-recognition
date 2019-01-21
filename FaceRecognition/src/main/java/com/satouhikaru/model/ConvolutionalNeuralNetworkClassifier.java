package com.satouhikaru.model;

import java.text.DecimalFormat;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.NeuralNetwork;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class ConvolutionalNeuralNetworkClassifier extends AbstractClassifier {

	NeuralNetwork cnn;

	/**
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @param modelOptions
	 * @throws Exception
	 */
	public ConvolutionalNeuralNetworkClassifier(String modelOptions) throws Exception {
		super(modelOptions);
	}

	@Override
	public void build(String fileName) {
		try {
			setTrainSet(fileName);
			trainSet.setClassIndex(trainSet.numAttributes() - 1);
			cnn = new NeuralNetwork();
			cnn.setOptions(modelOptions);
			cnn.buildClassifier(trainSet);
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
			evaluation.evaluateModel(cnn, testSet);
			result = evaluation.pctCorrect();
		} catch (Exception e) {
			System.out.println(e);
		}

		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		return decimalFormat.format(result) + "%";
	}

	/**
	 * Predict class from image data is loaded
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-12-2018
	 *
	 * @param imageURL
	 * @throws Exception 
	 */
	public String predictClass(String imageURL) throws Exception {
		
		// Preprocessing image data
		Instances instance = preprocessingImage(imageURL);
		instance.deleteAttributeAt(0);
		createFile(null, instance);
		
		// Load image data
		dataSource = new DataSource("prediction-data-processed.arff");
		Instances label = dataSource.getDataSet();
		label.setClassIndex(label.numAttributes() - 1);
		
		// Predict class for image
		double predict = cnn.classifyInstance(label.instance(0));
		label.instance(0).setClassValue(predict);
		
		return label.classAttribute().value((int) predict);
	}

}