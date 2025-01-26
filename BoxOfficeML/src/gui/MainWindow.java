package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import dom.Actor;

public class MainWindow extends JFrame{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArrayList<Actor> searchList = new ArrayList<>();

	public MainWindow(){
        setSize(new Dimension(1500, 800));   
        setLocationRelativeTo(null);

        this.setLayout(new GridLayout(1, 2));

        //----------------------LeftPanel-------------------------------------------
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel northLeftPanel = new JPanel();
        northLeftPanel.setPreferredSize(new Dimension(0, 40));
        northLeftPanel.setBackground(Color.red);
        
        JButton saveButton = new JButton("SAVE FILM");

        northLeftPanel.add(saveButton);
        leftPanel.add(northLeftPanel, BorderLayout.NORTH);

        JPanel centerLeftPanel = new JPanel();
        centerLeftPanel.setBackground(Color.blue);
        centerLeftPanel.setLayout(new FlowLayout());

        JLabel labelName = new JLabel("Film name");

        JTextField filmName = new JTextField(40);

        JLabel labelBudget = new JLabel("Total film budget");

        JTextField filmBudget = new JTextField(20);

        String directorName = "Christopher Nolan";
        JLabel labelDirector = new JLabel("Film director: "+ directorName);
        

        centerLeftPanel.add(labelName);
        centerLeftPanel.add(filmName);
        centerLeftPanel.add(labelBudget);
        centerLeftPanel.add(filmBudget);
        centerLeftPanel.add(labelDirector);

        leftPanel.add(centerLeftPanel, BorderLayout.CENTER);

        JPanel southLeftPanel = new JPanel();
        southLeftPanel.setPreferredSize(new Dimension(0, 300));
        southLeftPanel.setBackground(Color.green);

        JScrollPane southLeftPanelScroll = new JScrollPane(southLeftPanel);

        leftPanel.add(southLeftPanelScroll, BorderLayout.SOUTH);


        //---------------------RightPanel-------------------
        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(new EmptyBorder(40,40,0,40));

        JTextField search = new JTextField(10);
        
        JButton searchButton = new JButton("Search");
        
        JPanel rightNorthPanel = new JPanel();
        rightNorthPanel.setLayout(new FlowLayout());
        
        rightNorthPanel.add(search);
        rightNorthPanel.add(searchButton);
        
        rightPanel.add(rightNorthPanel, BorderLayout.NORTH);
        
        ActorModel actorModel = new ActorModel();
        
        JTable table = new JTable(actorModel);
        
        table.getColumnModel().getColumn(0).setMinWidth(0);
     	table.getColumnModel().getColumn(0).setMaxWidth(0);
     	table.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        
        //Action listener searchButton 
        searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = search.getText();
				if(!text.isEmpty()) {
					String replaced = text.replaceAll(" ", "%20");
					try {
						searchList.clear();
						connectionAPI.obtainActor(replaced, searchList);

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					actorModel.updateData();
				}
				
			}
		});

        this.add(leftPanel);
        this.add(rightPanel);
        setVisible(true);
       

    }

   public static void main(String[] args) {
       new MainWindow();
   }
   
   class ActorModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String[] nombreDatos = {"id", "Picture", "Name", "Department", "Proyects"}; 
		
		public void updateData() {
		    fireTableDataChanged();
		}

		@Override
		public String getColumnName(int column) {
			return nombreDatos[column];
		}


		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			Actor actor = searchList.get(rowIndex);
	        switch (columnIndex) {
	            case 0:
	                actor.setId((int) aValue);
	                break;
	            case 1:
	                actor.setPicture((String) aValue);
	                break;
	            case 3:
	            	actor.setKnownForDepartment((String) aValue);
	            	break;
	            case 2:
	                actor.setName((String) aValue);
	                break;
	            case 4:
	            	actor.setFilms((ArrayList<String>) aValue);
	            	break; 	
	        }
	        fireTableCellUpdated(rowIndex, columnIndex);
		}

		@Override
		public int getRowCount() {
			return searchList.size();
		}

		@Override
		public int getColumnCount() {
			return nombreDatos.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Actor actor = searchList.get(rowIndex);
	        switch (columnIndex) {
	        case 0: return actor.getId();
            case 1: return actor.getPicture();
            case 3: return actor.getKnownForDepartment();
            case 2: return actor.getName();
            case 4: return actor.getFilms().toString();
            default: return null;
	        }

   	
   }

}
}