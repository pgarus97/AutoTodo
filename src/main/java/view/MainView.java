package view;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatDarkLaf;

import model.TodoExtractor;


public class MainView {

	//main view
		
	private TodoExtractor todoExtractor = new TodoExtractor();
	
	//Declaration of components
	private JFrame frame;
	private JPanel mainPanel;
	private JButton runButton;
	private JButton inputButton;
	private JButton outputButton;
	private JLabel inputLabel;
	private JLabel outputLabel;
	private File inputFile;
	private File outputDir;
	private JLabel searchWordInfo; 
	private JTextField searchWordField;
	
	private static final String INPUT_PATH = "INPUT_PATH";  
	private static final String OUTPUT_PATH = "OUTPUT_PATH";  
	Preferences pref = Preferences.userRoot();


	public MainView() {	

		/*
		 * UI Design
		 */
		
		FlatDarkLaf.setup();
		
		try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		
		/*
		 * GUI implementation
		 */
		
		frame = new JFrame("AutoTodo by Bubzia");	
		initUIComponents();
	    initPaths();
	    
	    //panel
	    initMainPanel();
	    
	    /*
	     * add all components to mainframe
	     */
	   	    
	    frame.add(mainPanel);
	    frame.pack();
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setSize(600,250);
	    frame.setLayout(null); 
	    frame.setVisible(true);
	    
	    /*
	     * button listeners
	     */
	    
	    inputButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){ 
	    		chooseFile(inputLabel);
			}
	    });
	    
	    outputButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){ 
	    		chooseOutputDir(outputLabel);
			}
	    });
	    
	    runButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){
	    		if(inputFile != null && outputDir != null) {
	    			if(inputFile.isFile() && outputDir.isDirectory()) {
			    		boolean success = todoExtractor.cutInput(inputFile, outputDir, createSearchWordList());
			    		if(success) {
			                JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
			                try {
								Desktop.getDesktop().open(outputDir);
							} catch (IOException e1) {
				                JOptionPane.showMessageDialog(null, "Failure! Something went wrong while opening the file...", "Failure: File", JOptionPane.ERROR_MESSAGE);
							}
			    		}else {
			                JOptionPane.showMessageDialog(null, "Failure! Something went wrong...", "Failure", JOptionPane.ERROR_MESSAGE);
			    		}
	    			}else {
		                JOptionPane.showMessageDialog(null, "Failure! The paths are not correctly set...", "Failure: Paths", JOptionPane.ERROR_MESSAGE);
			    	}
	    		}
			}
	    });	    
	}
	
	private void chooseOutputDir(JLabel path) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(pref.get(OUTPUT_PATH, "")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int result = fileChooser.showOpenDialog(frame);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        outputDir = fileChooser.getSelectedFile();
	        path.setText(outputDir.getAbsolutePath());
	        // Save the selected path
	        pref.put(OUTPUT_PATH, outputDir.getAbsolutePath());
	    }
	}
	
    private void chooseFile(JLabel path) {
    	JFileChooser fileChooser = new JFileChooser();
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    	fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File(pref.get(INPUT_PATH, "")));
	    int result = fileChooser.showOpenDialog(frame);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        inputFile = fileChooser.getSelectedFile();
	        path.setText(inputFile.getAbsolutePath());
	        // Save the selected path
	        pref.put(INPUT_PATH, inputFile.getAbsolutePath());
	    }
    }
    
    private String[] createSearchWordList() {
    	return searchWordField.getText().split(",");
    }
    
    private void initPaths() {
	    inputLabel.setText(pref.get(INPUT_PATH, "No Input Selected"));
	    inputFile = new File(pref.get(INPUT_PATH, ""));
	    outputLabel.setText(pref.get(OUTPUT_PATH, "No Output Path Selected"));
	    outputDir = new File(pref.get(OUTPUT_PATH, ""));
    }
    
    private void initMainPanel() {
	    mainPanel.setBounds(10,610 ,400, 20);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	    	    
	    mainPanel.add(inputLabel);
	    mainPanel.add(inputButton);
	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    mainPanel.add(outputLabel);
	    mainPanel.add(outputButton);
	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    mainPanel.add(searchWordInfo);
	    mainPanel.add(searchWordField);
	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    mainPanel.add(runButton);
    }
    
    private void initUIComponents() {
    	mainPanel = new JPanel();
    	runButton = new JButton("Run");
    	inputButton = new JButton("Choose Input Path");
    	outputButton = new JButton("Choose Output Path");
    	inputLabel = new JLabel();
    	outputLabel = new JLabel();
    	searchWordInfo = new JLabel("Enter search words. Separate with ','"); 
    	searchWordField = new JTextField("to do, to-do");
    	// Set preferred size of text field
    	searchWordField.setPreferredSize(new Dimension(150, searchWordField.getPreferredSize().height));
    	searchWordField.setMaximumSize(searchWordField.getPreferredSize());


    	// Set alignment X and Y of text field
    	searchWordField.setAlignmentX(Component.LEFT_ALIGNMENT);
    	searchWordField.setAlignmentY(Component.CENTER_ALIGNMENT);
    }
}
