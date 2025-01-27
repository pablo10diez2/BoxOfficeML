package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

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
        
        JButton saveButton = new JButton("SAVE FILM");

        northLeftPanel.add(saveButton);
        leftPanel.add(northLeftPanel, BorderLayout.NORTH);

        JPanel centerLeftPanel = new JPanel();
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

        JScrollPane southLeftPanelScroll = new JScrollPane(southLeftPanel);

        leftPanel.add(southLeftPanelScroll, BorderLayout.SOUTH);


        //---------------------RightPanel-------------------
        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(new EmptyBorder(20,20,20,20));

        JTextField search = new JTextField(10);
        
        JButton searchButton = new JButton("Search");
        
        JPanel rightNorthPanel = new JPanel();
        rightNorthPanel.setLayout(new FlowLayout());
        
        rightNorthPanel.add(search);
        rightNorthPanel.add(searchButton);
        
        rightPanel.add(rightNorthPanel, BorderLayout.NORTH);
        
        JPanel rightSouthPanel = new JPanel();
        rightSouthPanel.setPreferredSize(new Dimension(0, 150));
        
       
        
        rightPanel.add(rightSouthPanel, BorderLayout.SOUTH);
        
        
        ActorModel actorModel = new ActorModel();
        
        JTable table = new JTable(actorModel);
        
        table.getColumnModel().getColumn(0).setMinWidth(0);
     	table.getColumnModel().getColumn(0).setMaxWidth(0);
     	table.getColumnModel().getColumn(0).setPreferredWidth(0);
     	
     	table.getColumnModel().getColumn(1).setMinWidth(70);
     	table.getColumnModel().getColumn(1).setMaxWidth(70);
     	table.getColumnModel().getColumn(1).setPreferredWidth(70);
     	
     	table.setRowHeight(100);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        
        TableCellRenderer cellRenderer = (table2, value, isSelected, hasFocus, row, column) -> {
  			table2.setBackground(new Color(70,130,180));
		      
      		if(column == 4) {
      			JLabel desc2 = new JLabel("Click to see");			
      			desc2.setHorizontalAlignment(JLabel.CENTER);
      			
      			desc2.setFont(new Font("Arial", Font.BOLD, 13));
      			
      			desc2.setOpaque(true);
      			desc2.setBackground(new Color(255, 255, 255));
      			desc2.setBackground(new Color(255, 255, 255));
          		
        		if(isSelected) {
        			rightSouthPanel.removeAll();
        			
        			String appears = "Appears in: " + value.toString();
        		    JTextArea filmsArea = new JTextArea(appears);
        		        
        		    filmsArea.setWrapStyleWord(true);
        		    filmsArea.setLineWrap(true);
        		    filmsArea.setEditable(false);
        		    filmsArea.setFont(new Font("Serif", Font.BOLD, 18));
        		    filmsArea.setPreferredSize(new Dimension(600, 200));
        		        
        		    rightSouthPanel.add(filmsArea);
        		    rightSouthPanel.repaint();
        		    rightSouthPanel.revalidate();
        		}
        		
          		return desc2;
      		}
      		
      		if(column == 1) {
      			if(!(value.toString() == ("No"))) {
      				URL url = null;
					try {
						url = new URL(value.toString());
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
      				
      				ImageIcon imageIcon = new ImageIcon(url);

      	            Image scaledImage = imageIcon.getImage().getScaledInstance(70, 100, Image.SCALE_SMOOTH);
      	            imageIcon = new ImageIcon(scaledImage);

      	            JLabel imageLabel = new JLabel(imageIcon);
      	            
      	            return imageLabel;
      			}
      			else {
      				JLabel label = new JLabel("No photo");
      				return label;
      			}
      		}
      		else {
      			JLabel result = new JLabel(value.toString());			
      			result.setHorizontalAlignment(JLabel.CENTER);
      			
      			result.setFont(new Font("Arial", Font.BOLD, 13));
      			
      			result.setOpaque(true);
      			result.setBackground(new Color(255, 255, 255));
      			result.setBackground(new Color(255, 255, 255));
      			
      			return result;
      		}
    		
      	};
        
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
        
        table.setDefaultRenderer(Object.class, cellRenderer);
        
     table.addMouseListener(new java.awt.event.MouseAdapter() {
         @Override
         public void mouseClicked(java.awt.event.MouseEvent e) {
             int row = table.rowAtPoint(e.getPoint());
             int column = table.columnAtPoint(e.getPoint());

             if (column == 5) {
                 String choice = showRoleDialog();
                 if (!choice.equals("None")) {
                     
                 }
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
		
		private String[] nombreDatos = {"id", "Picture", "Name", "Department", "Proyects", ""}; 
		
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
            case 5: return "Click to add";
            default: return null;
	        }

   	
   }

   }
   
   public static String showRoleDialog() {
       String[] options = {"Director", "Actor"};
       
       int choice = JOptionPane.showOptionDialog(
           null,
           "Do you want to add as a Director or as an Actor?",
           "Choose Role",
           JOptionPane.DEFAULT_OPTION,
           JOptionPane.QUESTION_MESSAGE,
           null,
           options,
           options[0]
       );

       if (choice == 0) {
           return "Director";
       } else if (choice == 1) {
           return "Actor";
       } else {
           return "None";
       }
   }
   
}