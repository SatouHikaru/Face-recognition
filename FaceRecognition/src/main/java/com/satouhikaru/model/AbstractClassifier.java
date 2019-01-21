package com.satouhikaru.model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.instance.imagefilter.ColorLayoutFilter;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public abstract class AbstractClassifier {

	DataSource dataSource;
	Instances dataset;
	Instances trainSet;
	Instances testSet;
	String[] modelOptions;

	public AbstractClassifier() {
	}

	/**
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 * 
	 * @param modelOptions
	 * @throws Exception
	 */
	public AbstractClassifier(String modelOptions) throws Exception {
		if (modelOptions != null) {
			this.modelOptions = Utils.splitOptions(modelOptions);
		}
	}

	/**
	 * Create ARFF file from loaded image
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-12-2018
	 *
	 * @param image
	 * @param instance
	 * @throws IOException
	 */
	public void createFile(String image, Instances instance) throws IOException {
		FileOutputStream fos = null;
		OutputStreamWriter ows = null;
		BufferedWriter bw = null;
		
		if (image != null) {
			fos = new FileOutputStream("prediction-data.arff");
			ows = new OutputStreamWriter(fos);
			bw = new BufferedWriter(ows);
			
			String line = "";
			for (int i = 0; i < 4; i ++) {
				if (i == 0) {
					bw.append("@relation prediction");
					bw.newLine();
				} else if (i == 3) {
					bw.newLine();
					bw.append("@data");
				} else {
					if (i == 1) {
						line = "filename string";
					} else {
						line = "class {male,female}";
					}
					
					bw.append("@attribute " + line);
				}
				
				bw.newLine();
			}
			
			bw.append(image + ",male");
		} else {
			fos = new FileOutputStream("prediction-data-processed.arff");
			ows = new OutputStreamWriter(fos);
			bw = new BufferedWriter(ows);
			
			bw.append(instance.toString());
		}
		
		bw.close();
		ows.close();
		fos.close();
	}
	
	/**
	 * Preprocessing image by Color Layout Filter
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  14/12/2018
	 *
	 * @param imageURL
	 * @return Instances
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public Instances preprocessingImage(String imageURL) throws Exception {
		DataSource dataSource = new DataSource("prediction-data.arff");
		Instances instance = dataSource.getDataSet();
		
		ColorLayoutFilter clf = new ColorLayoutFilter();
		String[] array = imageURL.split("\\\\");
		StringBuilder url = new StringBuilder();
		for (int i = 0; i < array.length - 1; i++) {
			url.append(array[i]);
			if (i != array.length - 2) {
				url.append("\\");
			}
		}
		
		clf.setImageDirectory(url.toString());
		clf.setInputFormat(instance);
		
		return clf.useFilter(instance, clf);
	}
	
	/**
	 * Load training dataset file
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @param fileName
	 * @throws Exception
	 */
	public void setTrainSet(String fileName) throws Exception {
		DataSource trainSource = new DataSource(fileName);
		trainSet = trainSource.getDataSet();
	}
	
	/**
	 * Load testing dataset file
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @param fileName
	 * @throws Exception
	 */
	public void setTestSet(String fileName) throws Exception {
		DataSource testSource = new DataSource(fileName);
		testSet = testSource.getDataSet();
	}
	
	/**
	 * Train a training dataset
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @param fileName
	 */
	public abstract void build(String fileName);
	
	/**
	 * Evaluate model
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @param fileName
	 * @return 
	 */
	public abstract String evaluate(String fileName);
	
}