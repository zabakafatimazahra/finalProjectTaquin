package jeu_taquin;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGUI implements ActionListener {

	JFrame fr;
	JPanel mainPanel;
	JButton [][] button;
	int rows;
	int cols;
	JLabel [][] label;
	int [][] board;
	
	
	public BoardGUI() {
		rows =3;
		cols =3;
		board = new int [rows][cols];
		initGUI();
	}
	public void initGUI() {
		fr = new JFrame("jeu de taquin");
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,3));
		button = new JButton[rows][cols];
		label = new JLabel[rows][cols];
		this.shuffleBoard();
		
		for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                button[i][j] = new JButton();
                String text = i+","+j; // store the index i and j
                
                button[i][j].setText(text);
                button[i][j].setFont(new Font("TimesRoman",Font.PLAIN,0));
                button[i][j].addActionListener(this);
                int val = board[i][j];
                
                String fileName;
                if (val==-1) {
                	val=9;
                	
                }
                fileName = "Pics/" + val + ".png";
            	label[i][j]=new JLabel(new ImageIcon(fileName), JLabel.CENTER);
        
                button[i][j].add(label[i][j]);
                mainPanel.add(button[i][j]);
         
            
            }

		fr.add(mainPanel);
		fr.setVisible(true);
		fr.setSize(500,500);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
	public void shuffleBoard() {
		Random rand = new Random();
		int [] array = new int[9];
		//remplir le tableau
		for (int i =0;i<9;i++) {
			array[i]= i+1;
		}
		array[8] = -1;
		
		for(int i=0;i<9;i++)
        {
            int index =  rand.nextInt(9);
            //replace element at random index with i index element
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
		int count = 0;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                board[i][j] = array[count];
                count = count + 1;
               System.out.print(board[i][j] + "\t");
            }
            System.out.println("");
	}
        
        }
	 public Boolean isWin() {
		int count =1;
    	for (int i =0; i<rows ; i++) {
    		for (int j = 0; j<cols; j++) {
    		if(board[i][j] !=count && board[i][j]!=-1 ) {
    			return false;
    		}
    		count =count+1;
}
	}
    	return true;
	}
	public void displayWinMsg() {
		JFrame frame= new JFrame("Game win");
		JLabel label = new JLabel("you win !", JLabel.CENTER);
		frame.add(label);
		frame.setLayout(new GridLayout(1,1));
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Boolean flag=isWin();
		if (flag==false) {
			
		
		String s = ae.getActionCommand().toString();
		int r = Integer.parseInt(s.split(",")[0]);
		int c = Integer.parseInt(s.split(",")[1]); 
		if (board [r][c] != -1) {
			
	
			if (r+1 <rows && board[r+1][c]==-1) {
				//up
				label[r][c].setIcon(new ImageIcon("Pics/9.png"));
	            String fileName = "Pics/" + board[r][c] + ".png";
	            label[r+1][c].setIcon(new ImageIcon(fileName));
				int temp = board[r][c];
				board [r][c]= board [r+1][c];
				board [r+1][c]= temp;
				
	}
			else if (r-1 >=0 && board[r-1][c]==-1) 
			{
				//for down
				label[r][c].setIcon(new ImageIcon("Pics/9.png"));
	            String fileName = "Pics/" + board[r][c] + ".png";
	            label[r-1][c].setIcon(new ImageIcon(fileName));
				
				int temp = board[r][c];
				board [r][c]= board [r-1][c];
				board [r-1][c]= temp;
}
			else if(c+1<cols && board[r][c+1]==-1) 
			{
				//for right
				label[r][c].setIcon(new ImageIcon("Pics/9.png"));
	            String fileName = "Pics/" + board[r][c] + ".png";
	            label[r][c+1].setIcon(new ImageIcon(fileName));
				int temp = board[r][c];
				board [r][c]= board [r][c+1];
				board [r][c+1]= temp;
			}
			else if (c-1>=0 && board[r][c-1]==-1) 
			{
				//for left
				label[r][c].setIcon(new ImageIcon("Pics/9.png"));
	            String fileName = "Pics/" + board[r][c] + ".png";
	            label[r][c-1].setIcon(new ImageIcon(fileName));
				int temp = board[r][c];
				board [r][c]= board [r][c-1];
				board [r][c-1]= temp;
			}
		}
		flag=isWin();
		if (flag ==true) {
			displayWinMsg();
			
		}
			}
			}
	
		}
