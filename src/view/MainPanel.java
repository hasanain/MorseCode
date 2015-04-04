package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.MorseCodeTranslator;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8668618688820184387L;

	private JTextArea inputArea, outputArea;
	private JPanel inputPanel, outputPanel;
	private MorseCodeTranslator mtr;

	public MainPanel() {
		makeModel();
		makeInputPanel();
		makeOutputPanel();
		this.setLayout(new BorderLayout(5, 5));
		this.add(inputPanel, BorderLayout.NORTH);
		this.add(outputPanel, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(700, 330));
	}

	private void makeOutputPanel() {
		outputPanel = new JPanel(new BorderLayout());
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setSize(new Dimension(100, 150));
		outputArea.setPreferredSize(new Dimension(100, 150));
		outputArea.setLineWrap(true);
		outputPanel.add(new JLabel("Translated output: "), BorderLayout.NORTH);
		outputPanel.add(outputArea, BorderLayout.SOUTH);
	}

	private void makeInputPanel() {
		inputPanel = new JPanel(new BorderLayout());
		inputArea = new JTextArea();
		inputArea.addKeyListener(new TranslatorController());
		inputArea.setSize(new Dimension(100, 150));
		inputArea.setPreferredSize(new Dimension(100, 150));
		inputArea.setLineWrap(true);
		inputPanel.add(new JLabel("Input:"), BorderLayout.NORTH);
		inputPanel.add(inputArea, BorderLayout.SOUTH);
	}

	private void makeModel() {
		mtr = MorseCodeTranslator.getInstance();
	}

	private class TranslatorController implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {
			outputArea.setText(mtr.translate(inputArea.getText()));

		}

	}

	public void openFile(File file, JFrame frame) {
		try {
			Scanner scan = new Scanner(file);
			StringBuilder input = new StringBuilder();
			while (scan.hasNextLine()) {
				input.append(scan.nextLine());
			}
			inputArea.setText(input.toString());
			outputArea.setText(mtr.translate(inputArea.getText()));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame, "File not found!");
		}

	}
}