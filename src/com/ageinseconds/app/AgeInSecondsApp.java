package com.ageinseconds.app;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class AgeInSecondsApp implements Runnable {

	private JFrame mJFrame;
	private static final String APP_NAME = "Age in seconds";
	private static final int WINDOW_WIDTH = 450;
	private static final int WINDOW_HEIGHT = 200;
	private JTextField yearsValue;
	private JTextField secondsValue;
	private JButton calculate;
	private JButton reset;

	@Override
	public void run() {

		mJFrame = new JFrame(APP_NAME);

		mJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		mJFrame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		createComponents(mJFrame.getContentPane());

		mJFrame.pack();

		mJFrame.setVisible(true);

		mJFrame.setLocationRelativeTo(null);

	}

	private void createComponents(Container container) {

		// Create and set GridBagLayout to container
		GridBagLayout mGridBagLayout = new GridBagLayout();
		container.setLayout(mGridBagLayout);

		// Create constraints to control the layout and adjust them
		GridBagConstraints mConstraints = new GridBagConstraints();
		mConstraints.fill = GridBagConstraints.HORIZONTAL;
		mConstraints.insets = new Insets(10, 10, 10, 10);
		mConstraints.weightx = 1;

		// Create units column header and adjust constraints
		JLabel units = new JLabel("Unit");
		mConstraints.gridx = 0;
		mConstraints.gridy = 0;
		container.add(units, mConstraints);

		// Create values column header and adjust constraints
		JLabel values = new JLabel("Value");
		mConstraints.gridx = 1;
		mConstraints.gridy = 0;
		container.add(values, mConstraints);

		// Create year row header and adjust constraints
		JLabel years = new JLabel("Your age in years");
		mConstraints.gridx = 0;
		mConstraints.gridy = 1;
		container.add(years, mConstraints);

		yearsValue = new JTextField();
		mConstraints.gridx = 1;
		mConstraints.gridy = 1;
		container.add(yearsValue, mConstraints);

		// Create second row header and adjust constraints
		JLabel seconds = new JLabel("Your age in seconds");
		mConstraints.gridx = 0;
		mConstraints.gridy = 2;
		container.add(seconds, mConstraints);

		secondsValue = new JTextField();
		mConstraints.gridx = 1;
		mConstraints.gridy = 2;
		container.add(secondsValue, mConstraints);
		
		yearsValue.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				
				if(yearsValue.getText().isEmpty() && secondsValue.getText().isEmpty()) {
					reset.setEnabled(false);
					calculate.setEnabled(false);
				} else {
					reset.setEnabled(true);
					calculate.setEnabled(true);
				}
				
				if(yearsValue.getText().isEmpty()) {
					secondsValue.setEditable(true);
					yearsValue.setEditable(true);
				} else {
					secondsValue.setEditable(false);
					
				}
				
				
			}
		});
		
		secondsValue.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				
				if(yearsValue.getText().isEmpty() && secondsValue.getText().isEmpty()) {
					reset.setEnabled(false);
					calculate.setEnabled(false);
				} else {
					reset.setEnabled(true);
					calculate.setEnabled(true);
				}
				
				if(secondsValue.getText().isEmpty()) {
					secondsValue.setEditable(true);
					yearsValue.setEditable(true);
				} else {
					
					yearsValue.setEditable(false);
				}		
			}
		});

		reset = new JButton("Reset");
		mConstraints.gridx = 0;
		mConstraints.gridy = 3;
		container.add(reset, mConstraints);
		reset.setEnabled(false);

		calculate = new JButton("Calculate");
		mConstraints.gridx = 1;
		mConstraints.gridy = 3;
		container.add(calculate, mConstraints);
		calculate.setEnabled(false);
		
		calculate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(yearsValue.isEditable()) {
					
					calculateValues(readInput(yearsValue.getText()), 0, true);
					
				} else if (secondsValue.isEditable()){
					calculateValues(0, readInput(secondsValue.getText()), false);
				}
				
			}
			
		});
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				yearsValue.setText("");
				secondsValue.setText("");
				yearsValue.setEditable(true);
				secondsValue.setEditable(true);
			}
		});

	}
	
	private double readInput(String input) {
		
		double value = 0;
		
		try {
			value = Double.parseDouble(input);
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mJFrame, "Incorrect entry \n1. Entry must be a (decimal) number \n2. Decimal separtor is \".\" (Point)");
		}
		return value;
	}
	
	private void calculateValues(double years, double seconds, boolean inputInYears) {
		
		if (inputInYears) {
			
            double secondVal = years * 365 * 24 * 60 * 60;
			secondsValue.setText("" + secondVal);
			
		} else {
			
			yearsValue.setText("" + seconds / (365 * 24 * 60 * 60));
			
		}
		
		
	}

}
