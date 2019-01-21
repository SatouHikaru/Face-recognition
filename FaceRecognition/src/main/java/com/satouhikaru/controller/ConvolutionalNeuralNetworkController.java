package com.satouhikaru.controller;

import java.io.IOException;

import com.satouhikaru.model.ConvolutionalNeuralNetworkClassifier;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class ConvolutionalNeuralNetworkController extends Controller {

	ConvolutionalNeuralNetworkClassifier cnn;
	
	@Override
	public String buildModel(String trainSet, String testSet, String[] parameters) {
		for (int i = 1; i < 3; i++) {
			parameters[i] = parameters[i].replace('x', '-');
		}
		
		String parameter = String.format("-lr 0.0 -wp 1.0E-8 -mi 1000 -bs 0 -th 0 -hl "
				+ "%s -di 0.2 -dh 0.5 -iw 0",
				parameters[0] + "-" + parameters[1] + "-" + parameters[2]);
		try {
			cnn = new ConvolutionalNeuralNetworkClassifier(parameter);
			cnn.build(trainSet);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return cnn.evaluate(testSet);
	}
	
	/**
	 * Create ARFF file from loaded image
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-12-2018
	 *
	 * @param imageURL
	 */
	public void createFile(String image) {
		try {
			cnn.createFile(image, null);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Predict class from loaded image
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-12-2018
	 *
	 * @param imageURL
	 * @return
	 */
	public String predictClass(String imageURL) {
		String label = "";
		try {
			label = cnn.predictClass(imageURL);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return label;
	}
	
}