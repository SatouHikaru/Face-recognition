package com.satouhikaru.controller;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Pham Nguyen Ha Quang
 * @since  03-11-2018
 */
public class Controller {

	private JFileChooser fileChooser = new JFileChooser();;
	
	/**
	 * Load a dataset
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @return dataset location and name
	 */
	public String[] loadDataset() {
		FileFilter fileFilter = new FileNameExtensionFilter("ARFF file", "arff");
		fileChooser.setFileFilter(fileFilter);
		int selected = fileChooser.showOpenDialog(null);
		String location = "", fileName = "";
		if (selected == JFileChooser.APPROVE_OPTION) {
			location = fileChooser.getSelectedFile().toString();
			fileName = fileChooser.getName(fileChooser.getSelectedFile());
		}
		
		return new String[] { location, fileName };
	}
	
	/**
	 * Resize an image
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-12-2018
	 *
	 * @param imagePath
	 * @return image's image file
	 */
	public ImageIcon resizeImage(String imagePath, JLabel jlabel) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image img = imageIcon.getImage();
        Image imageSize = img.getScaledInstance(jlabel.getWidth(), jlabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(imageSize);
        return image;
    }
	
	/**
	 * Load an image
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-12-2018
	 *
	 * @return image location and name
	 */
	public String[] loadImage() {
		FileFilter fileFilter = new FileNameExtensionFilter("Image file", "jpg", "png");
		fileChooser.setFileFilter(fileFilter);
		int selected = fileChooser.showOpenDialog(null);
		String location = "", fileName = "";
		if (selected == JFileChooser.APPROVE_OPTION) {
			location = fileChooser.getSelectedFile().toString();
			fileName = fileChooser.getName(fileChooser.getSelectedFile());
		}
		
		return new String[] { location, fileName };
	}
	
	/**
	 * Build model
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @param trainSet
	 * @param testSet
	 * @return 
	 */
	public String buildModel(String trainSet, String testSet) {
		return null;
	}
	
	/**
	 * Build model
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  03-11-2018
	 *
	 * @param trainSet
	 * @param testSet
	 * @param parameters
	 * @return 
	 */
	public String buildModel(String trainSet, String testSet, String[] parameters) {
		return null;
	}
	
}