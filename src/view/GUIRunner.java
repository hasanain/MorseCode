package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUIRunner {

	private JFrame frame;
	private MainPanel mainPanel;
	private JMenuBar menu;
	JFileChooser fc = new JFileChooser();
	File file;

	public GUIRunner() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openButton = new JMenuItem("Open");
		JMenuItem saveButton = new JMenuItem("Save");
		JMenuItem exitButton = new JMenuItem("Exit");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text files", "txt", "md", "html", "java", "tex");
		fc.setFileFilter(filter);
		openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					openFile(file);
				}

			}
		});
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fc.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fc.showSaveDialog(frame);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fc.getSelectedFile();
				    try {
						FileWriter fw = new FileWriter(fileToSave);
						fw.write("Input:\n");
						fw.write(mainPanel.getInput());
						fw.write("\n");
						fw.write("Translation:\n");
						fw.write(mainPanel.getOutput());
						fw.write("\n");
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   
				    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				}
				
			}
		});
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		fileMenu.add(openButton);
		fileMenu.add(saveButton);
		fileMenu.add(exitButton);
		menu.add(fileMenu);
		mainPanel = new MainPanel();
		frame.add(menu, BorderLayout.NORTH);
		frame.add(mainPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void openFile(File file) {
		try {
			Scanner scan = new Scanner(file);
			StringBuilder input = new StringBuilder();
			while (scan.hasNextLine()) {
				input.append(scan.nextLine());
			}
			mainPanel.setInput("");
			mainPanel.setInput(input.toString());
			mainPanel.translate();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame, "File not found!");
		}

	}
	public static void main(String[] args) {
		new GUIRunner();
	}
}
