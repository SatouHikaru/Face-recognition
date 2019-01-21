package com.satouhikaru;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.satouhikaru.controller.ArtificialNeuralNetworkController;
import com.satouhikaru.controller.Controller;
import com.satouhikaru.controller.ConvolutionalNeuralNetworkController;
import com.satouhikaru.controller.KNearestNeighborController;
import com.satouhikaru.controller.NaïveBayesController;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Pham Nguyen Ha Quang
 * @since  30-11-2018
 */
public class Form extends JFrame {

	private static final long serialVersionUID = 7493590302423506060L;
	
	Controller controller = new Controller();
	ConvolutionalNeuralNetworkController cnn = new ConvolutionalNeuralNetworkController();
	String[] trainingSet = new String[2];
	String[] testSet = new String[2];
	String[] image = new String[2];

	private JPanel contentPane;
	private JTextField txtTraining;
	private JTextField txtTest;
	private JTextField txtFeatureMaps;
	private JTextField txtPatchSize;
	private JTextField txtPooling;
	private JTextField txtNB;
	private JTextField txtKNN;
	private JTextField txtANN;
	private JTextField txtCNN;
	private JTextField txtClass;
	private JTextField txtImageName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Form() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Pictures\\Face-Recognition-icon.png"));
		setTitle("Face Recognition in Data Mining");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1018, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1002, 523);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Classify", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Dataset", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 977, 102);
		panel.add(panel_1);
		
		txtTraining = new JTextField();
		txtTraining.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTraining.setEditable(false);
		txtTraining.setColumns(10);
		txtTraining.setBackground(Color.WHITE);
		txtTraining.setBounds(10, 50, 366, 28);
		panel_1.add(txtTraining);
		
		JButton btnLoadTraining = new JButton("Load");
		btnLoadTraining.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLoadTraining.setBounds(386, 49, 69, 29);
		panel_1.add(btnLoadTraining);
		
		JLabel label = new JLabel("Training set");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(10, 24, 133, 21);
		panel_1.add(label);
		
		txtTest = new JTextField();
		txtTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTest.setEditable(false);
		txtTest.setColumns(10);
		txtTest.setBackground(Color.WHITE);
		txtTest.setBounds(522, 50, 366, 28);
		panel_1.add(txtTest);
		
		JButton btnLoadTest = new JButton("Load");
		btnLoadTest.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLoadTest.setBounds(898, 49, 69, 29);
		panel_1.add(btnLoadTest);
		
		JLabel label_1 = new JLabel("Test set");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(522, 27, 127, 14);
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Convolutional Neural Network", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Convolutional Neural Network", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 168, 977, 80);
		panel.add(panel_2);
		
		JLabel label_2 = new JLabel("Number of feature maps:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_2.setBounds(112, 31, 186, 21);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("Max pooling:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_3.setBounds(665, 31, 94, 21);
		panel_2.add(label_3);
		
		txtFeatureMaps = new JTextField();
		txtFeatureMaps.setEnabled(false);
		txtFeatureMaps.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFeatureMaps.setColumns(10);
		txtFeatureMaps.setBackground(Color.WHITE);
		txtFeatureMaps.setBounds(302, 28, 34, 28);
		panel_2.add(txtFeatureMaps);
		
		JLabel label_4 = new JLabel("Patch size:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBounds(435, 31, 81, 21);
		panel_2.add(label_4);
		
		txtPatchSize = new JTextField();
		txtPatchSize.setEnabled(false);
		txtPatchSize.setHorizontalAlignment(SwingConstants.CENTER);
		txtPatchSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPatchSize.setColumns(10);
		txtPatchSize.setBackground(Color.WHITE);
		txtPatchSize.setBounds(521, 28, 33, 28);
		panel_2.add(txtPatchSize);
		
		txtPooling = new JTextField();
		txtPooling.setEnabled(false);
		txtPooling.setHorizontalAlignment(SwingConstants.CENTER);
		txtPooling.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPooling.setColumns(10);
		txtPooling.setBackground(Color.WHITE);
		txtPooling.setBounds(764, 28, 33, 28);
		panel_2.add(txtPooling);
		
		JButton btnBuild = new JButton("Build model");
		btnBuild.setEnabled(false);
		btnBuild.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuild.setBounds(435, 319, 127, 40);
		panel.add(btnBuild);
		
		JLabel label_5 = new JLabel("Na\u00EFve Bayes");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(88, 397, 95, 21);
		panel.add(label_5);
		
		txtNB = new JTextField();
		txtNB.setHorizontalAlignment(SwingConstants.CENTER);
		txtNB.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtNB.setEditable(false);
		txtNB.setColumns(10);
		txtNB.setBackground(Color.WHITE);
		txtNB.setBounds(52, 427, 165, 54);
		panel.add(txtNB);
		
		JLabel label_6 = new JLabel("k-NN");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(359, 397, 40, 21);
		panel.add(label_6);
		
		txtKNN = new JTextField();
		txtKNN.setHorizontalAlignment(SwingConstants.CENTER);
		txtKNN.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtKNN.setEditable(false);
		txtKNN.setColumns(10);
		txtKNN.setBackground(Color.WHITE);
		txtKNN.setBounds(294, 427, 165, 54);
		panel.add(txtKNN);
		
		JLabel label_7 = new JLabel("ANN");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_7.setBounds(604, 397, 35, 21);
		panel.add(label_7);
		
		txtANN = new JTextField();
		txtANN.setHorizontalAlignment(SwingConstants.CENTER);
		txtANN.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtANN.setEditable(false);
		txtANN.setColumns(10);
		txtANN.setBackground(Color.WHITE);
		txtANN.setBounds(539, 427, 165, 54);
		panel.add(txtANN);
		
		JLabel label_8 = new JLabel("CNN");
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_8.setBounds(850, 397, 35, 21);
		panel.add(label_8);
		
		txtCNN = new JTextField();
		txtCNN.setHorizontalAlignment(SwingConstants.CENTER);
		txtCNN.setForeground(Color.RED);
		txtCNN.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtCNN.setEditable(false);
		txtCNN.setColumns(10);
		txtCNN.setBackground(Color.WHITE);
		txtCNN.setBounds(784, 427, 165, 54);
		panel.add(txtCNN);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Predict", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnLoadImage = new JButton("Load");
		btnLoadImage.setBounds(918, 11, 69, 29);
		panel_3.add(btnLoadImage);
		btnLoadImage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4.setBounds(10, 11, 713, 473);
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImage = new JLabel("");
		panel_4.add(lblImage);
		
		txtClass = new JTextField();
		txtClass.setHorizontalAlignment(SwingConstants.CENTER);
		txtClass.setForeground(Color.RED);
		txtClass.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtClass.setEditable(false);
		txtClass.setColumns(10);
		txtClass.setBackground(Color.WHITE);
		txtClass.setBounds(779, 430, 165, 54);
		panel_3.add(txtClass);
		
		txtImageName = new JTextField();
		txtImageName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtImageName.setEditable(false);
		txtImageName.setColumns(10);
		txtImageName.setBackground(Color.WHITE);
		txtImageName.setBounds(733, 12, 175, 28);
		panel_3.add(txtImageName);
		
		
		btnLoadTraining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trainingSet = controller.loadDataset();
				txtTraining.setText(trainingSet[1]);
				
				if (!txtTraining.getText().equals("") && !txtTest.getText().equals("")) {
					txtFeatureMaps.setEnabled(true);
					txtPatchSize.setEnabled(true);
					txtPooling.setEnabled(true);
					btnBuild.setEnabled(true);
				}
			}
		});
		
		btnLoadTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testSet = controller.loadDataset();
				txtTest.setText(testSet[1]);
				
				if (!txtTraining.getText().equals("") && !txtTest.getText().equals("")) {
					txtFeatureMaps.setEnabled(true);
					txtPatchSize.setEnabled(true);
					txtPooling.setEnabled(true);
					btnBuild.setEnabled(true);
				}
			}
		});
		
		txtPatchSize.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtPatchSize.setText(txtPatchSize.getText() + "x" + txtPatchSize.getText());
			}
		});
		
		txtPooling.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtPooling.setText(txtPooling.getText() + "x" + txtPooling.getText());
			}
		});
		
		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFeatureMaps.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You must choose number of feature maps!",
							"Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				// Build Naïve Bayes model
				Controller naïveBayes = new NaïveBayesController();
				String nbResult = naïveBayes.buildModel(trainingSet[0],
						testSet[0]);
				txtNB.setText(nbResult);
				
				// Build k-NN model
				Controller kNN = new KNearestNeighborController();
				String knnResult = kNN.buildModel(trainingSet[0],
						testSet[0]);
				txtKNN.setText(knnResult);
				
				// Build ANN model
				Controller ann = new ArtificialNeuralNetworkController();
				String annResult = ann.buildModel(trainingSet[0],
						testSet[0]);
				txtANN.setText(annResult);
				
				// Build CNN model
				String cnnResult = cnn.buildModel(trainingSet[0],
						testSet[0], new String[] {
							txtFeatureMaps.getText(),
							txtPatchSize.getText(),
							txtPooling.getText()
						});
				txtCNN.setText(cnnResult);
			}
		});
		
		btnLoadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCNN.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You must evaluate datasets!",
							"Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				image = controller.loadImage();
				txtImageName.setText(image[1]);
				ImageIcon icon = controller.resizeImage(image[0], lblImage);
				lblImage.setIcon(icon);
				cnn.createFile(image[1]);
				txtClass.setText(cnn.predictClass(image[0]));
			}
		});
	}
	
}