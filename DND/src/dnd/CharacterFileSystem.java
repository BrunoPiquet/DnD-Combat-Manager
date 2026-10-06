package dnd;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.swing.*;

public class CharacterFileSystem extends JFrame{
	
	private String saveFolderPath = "SavedCharacters";
	static JPanel listPanel = new JPanel(new GridLayout(15,1));
	JComboBox<String> savableCharactersBox = new JComboBox<String>();
	JButton saveButton = new JButton("Save");
	
	public CharacterFileSystem(){
		super("Character File System");
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(1,2));
		
		
		this.add(listPanel);
		
		JPanel savePanel = new JPanel(new GridLayout(5,1,5,5));
		
		setSavableCharacters(DnD_Combat_Manager.getCharacterNameList());
		
		savePanel.add(new JPanel());
		savePanel.add(savableCharactersBox);
		savePanel.add(new JPanel());
		savePanel.add(saveButton);
		savePanel.add(new JPanel());

		this.add(savePanel);
		
		
		saveButton.addActionListener(listener);
		buildListPanel();
		
		this.setVisible(true);
	}
	
	private ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == saveButton) {
				listPanel.removeAll();
				DnD_Combat_Manager.saveCharacter(savableCharactersBox.getSelectedItem().toString());
				buildListPanel();
			}

		}
	};
	
	private void buildListPanel() {
		listPanel.removeAll();
		
		listPanel.add(new JLabel("Saved Characters", SwingConstants.CENTER));
		File directory = new File(saveFolderPath);
		
		for(var file : directory.listFiles()) {
			String fileName = file.toString();
			String characterName = fileName.substring(fileName.indexOf('\\')+1, fileName.indexOf('.'));
			
			JPanel panel = new JPanel(new GridLayout(1,3,5,5));
			panel.add(new JLabel(characterName, SwingConstants.CENTER));
			
			JButton deleteButton = new JButton("Delete");
			JButton loadButton = new JButton("Load");
			
			panel.add(loadButton);
			panel.add(deleteButton);
			
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					if(ev.getSource() == deleteButton) {
						Path path = Paths.get(fileName);
						try {
							Files.delete(path);
							buildListPanel();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}});
			
			loadButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					if(ev.getSource() == loadButton) {
						DnD_Combat_Manager.loadCharacter(fileName);
					}
				}});

			
			listPanel.add(panel);
		}
		listPanel.repaint();
		listPanel.revalidate();
	}
	
	private void setSavableCharacters(ArrayList<String> list) {
		for(String item : list) {
			savableCharactersBox.addItem(item);
		}
	}
	
}
