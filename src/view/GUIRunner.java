package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text files", "txt", "md", "html", "java", "tex");
		fc.setFileFilter(filter);
		openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					mainPanel.openFile(file, frame);
				}

			}
		});
		fileMenu.add(openButton);
		fileMenu.add(saveButton);
		menu.add(fileMenu);
		mainPanel = new MainPanel();
		frame.add(menu, BorderLayout.NORTH);
		frame.add(mainPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new GUIRunner();
	}
}
