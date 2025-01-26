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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class main extends JFrame{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArrayList<String> searchList = new ArrayList<>();

	public main(){
        setSize(new Dimension(1000, 600));   
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
        
        //Action listener searchButton 
        searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = search.getText();
				if(!text.isEmpty()) {
					String replaced = text.replaceAll(" ", "%20");
					try {
						System.out.println(connectionAPI.obtainActor(replaced, searchList));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});

        this.add(leftPanel);
        this.add(rightPanel);
        setVisible(true);
       

    }

   public static void main(String[] args) {
       new main();
   }

}