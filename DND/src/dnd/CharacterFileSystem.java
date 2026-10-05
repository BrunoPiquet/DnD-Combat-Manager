package dnd;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import javax.swing.*;

public class CharacterFileSystem extends JFrame{
	
	private String saveFolderPath = "SavedCharacters";
	static JPanel listPanel = new JPanel(new GridLayout(15,1));
	
	public CharacterFileSystem(){
		super("Character File System");
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(1,2));
		
		
		this.add(listPanel);
		this.add(new JPanel());
		
		buildListPanel();
		
		this.setVisible(true);
	}
	
	private void buildListPanel() {
		listPanel.add(new JLabel("Saved Characters", SwingConstants.CENTER));
		File directory = new File(saveFolderPath);
		for(var file : directory.listFiles()) {
			String fileName = file.toString();
			fileName = fileName.substring(fileName.indexOf('\\')+1, fileName.indexOf('.'));
			
			JPanel panel = new JPanel(new GridLayout(1,3,5,5));
			panel.add(new JLabel(fileName, SwingConstants.CENTER));
			panel.add(new JButton("Load"));
			panel.add(new JButton("Delete"));
			
			listPanel.add(panel);
			
		}
	}
	
}
