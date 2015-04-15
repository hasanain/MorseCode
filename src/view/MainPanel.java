package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import model.EnglishToMorseTranslator;
import model.MorseToEnglishTranslator;
import model.Translator;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8668618688820184387L;

	private JTextArea inputArea, outputArea;
	private JPanel inputPanel, outputPanel;
	private JScrollPane inputPane, outputPane;
	private SwingWorker<String, String> worker;
	
	//TODO 3: Talk about etm (English to Morse) and mte (Morse to English) Translators
	private Translator etm, mte;

	public MainPanel() {
		makeModel();
		makeInputPanel();
		makeOutputPanel();
		worker = new MySwingWorker();
		this.setLayout(new BorderLayout(5, 5));
		this.add(inputPanel, BorderLayout.NORTH);
		this.add(outputPanel, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(700, 330));
	}

	private void makeOutputPanel() {
		outputPanel = new JPanel(new BorderLayout());
		outputArea = new JTextArea();
		outputArea.setEditable(false);

		outputArea.setLineWrap(true);
		outputPanel.add(new JLabel("Translated output: "), BorderLayout.NORTH);
		outputPane = new JScrollPane(outputArea);
		outputPane.setPreferredSize(new Dimension(100, 150));
		outputPanel.add(outputPane, BorderLayout.SOUTH);
	}

	private void makeInputPanel() {
		inputPanel = new JPanel(new BorderLayout());
		inputArea = new JTextArea();

		inputArea.addKeyListener(new TranslatorController());
		inputArea.setLineWrap(true);
		inputPanel.add(new JLabel("Input:"), BorderLayout.NORTH);
		inputPane = new JScrollPane(inputArea);
		inputPane.setPreferredSize(new Dimension(100, 150));
		inputPanel.add(inputPane, BorderLayout.SOUTH);
	}
	private void makeModel() {
		etm = Translator.makeTranslator(Translator.ENGLSIH_TO_MORSE);
		mte = Translator.makeTranslator(Translator.MORSE_TO_ENGLISH);
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
			translate();

		}
	}

	public synchronized void translate() {

		new Thread(new Runnable() {
			public void run() {
				long start = System.currentTimeMillis();

				boolean flag = ((MorseToEnglishTranslator) mte)
						             .isMorse(inputArea.getText());
				outputArea.setText("");
				outputArea.repaint();
				StringBuilder translation = new StringBuilder();
				for (String s : inputArea.getText().split("\n")) {

					if (flag) {
						translation.append(mte.translate(s) + "\n");
					} else {
						translation.append(etm.translate(s) + "\n");
					}
					
				}
				outputArea.setText(translation + "\n");
				// outputArea.repaint();
				System.out.println(System.currentTimeMillis() - start);
			}

		}).start();

	}

	public String getInput() {
		return inputArea.getText();
	}

	public void setInput(String toSet) {
		inputArea.setText(toSet);
	}

	public String getOutput() {
		return outputArea.getText();
	}

	public void setOutput(String toSet) {
		outputArea.setText(toSet);
	}

	private class MySwingWorker extends SwingWorker<String, String> {

		public void process(List<String> chuncks) {
			for (String s : chuncks) {
				outputArea.append(s);
				outputArea.repaint();
			}
		}

		@Override
		protected String doInBackground() throws Exception {
			System.out.println("hello");
			new Thread(new Runnable() {
				public void run() {
					boolean flag = ((MorseToEnglishTranslator) mte)
							.isMorse(inputArea.getText());
					outputArea.setText("");
					outputArea.repaint();
					String translation = "";
					System.out.println("hey");
					for (String s : inputArea.getText().split("\n")) {
						System.out.println(s);

						if (flag) {
							translation = mte.translate(s);
						} else {
							translation = etm.translate(s);
						}
						publish(translation);
					}
				}
			}).start();
			return null;
		}

	}

}
