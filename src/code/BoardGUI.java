package code;
import javax.swing.*;

import code.Hallway.Cell;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;


public class BoardGUI extends JFrame implements ActionListener{
	Board b = new Board();
	ArrayList<tilePanel> tileList = new ArrayList<tilePanel>();
	JComboBox weaponGuess;
	JComboBox playerGuess;
	JComboBox yourRoom;
	JPanel mainPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JPanel keyPanel = new JPanel();
	JPanel cardPanel = new JPanel();
	JLabel result = new JLabel("Your investigation result:");
	JLabel cardLabel = new JLabel("Your Cards");
	JLabel movesLeft = new JLabel("Moves Left: " + b.players.get(b.playerIndex).dice);
	JLabel playerLabel = new JLabel("Current Player: Miss Scarlet");
	JLabel currentPos = new JLabel("Current position:  row:" +b.players.get(b.playerIndex).coord[0]
			+ " col:" + b.players.get(b.playerIndex).coord[1]);
	
	ImageIcon dicePic = new ImageIcon("Dice3.png");
	

	JButton investigate = new JButton("Investigate");
	JButton endTurn = new JButton("End Turn");
	JButton viewCards = new JButton("View Cards");

	
	public BoardGUI(Board b1){
		super("Clue!");
		b = b1;
		setSize(1800,1200);
		setResizable(true);		
		
		//fills combo boxes
		weaponGuess = new JComboBox<String>();
		weaponGuess.setEditable(true);
		 weaponGuess.addItem("Knife");
		 weaponGuess.addItem("Candlestick");
		 weaponGuess.addItem("LeadPipe");
		 weaponGuess.addItem("Wrench");
		 weaponGuess.addItem("Revolver");
		playerGuess = new JComboBox<String>();
		playerGuess.setEditable(true);
		for(Player p:b.players){
			playerGuess.addItem(p.person.toString());
		}
		yourRoom = new JComboBox<String>();
		if(getPlayer().place!=null){
			yourRoom.addItem(getPlayer().place.type.toString());
		}
		else yourRoom.addItem("No Room!");
		
		//sets bounds and colors for components
		result.setBounds(1000,200,700,500);
		result.setBackground(Color.darkGray);
		result.setFont(new Font("Serif", Font.BOLD, 18));
		result.setForeground(Color.white);
		weaponGuess.setBounds(1000,700,200,25);
		playerGuess.setBounds(1250,700,200,25);
		yourRoom.setBounds(1500,700,200,25);
		cardLabel.setBounds(350,25,300,50);
		cardLabel.setFont(new Font("Serif", Font.BOLD, 18));
		cardLabel.setForeground(Color.white);
		playerLabel.setBounds(1000,100,300,50);
		playerLabel.setFont(new Font("Serif", Font.BOLD, 18));
		playerLabel.setForeground(Color.white);
		movesLeft.setBounds(1000,150,300,50);
		movesLeft.setFont(new Font("Serif", Font.BOLD, 18));
		movesLeft.setForeground(Color.white);
		currentPos.setBounds(1000,200,500,50);
		currentPos.setFont(new Font("Serif", Font.BOLD, 18));
		currentPos.setForeground(Color.white);
		cardPanel.setBounds(100,75, 600, 200);
		
		investigate.setBounds(1250, 750,100, 25);
		endTurn.setBounds(1000, 900, 100, 25);
		createkeyPanel();
		
		investigate.setActionCommand("investigate");
		investigate.addActionListener(this);
		
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setPreferredSize(new Dimension(1200,1200));
		mainPanel.add(playerGuess);
		mainPanel.add(weaponGuess);
		mainPanel.add(yourRoom);
		mainPanel.add(result);
		mainPanel.add(cardLabel);
		mainPanel.add(keyPanel);
		mainPanel.add(currentPos);
		mainPanel.add(investigate);
		mainPanel.add(gamePanel);
		mainPanel.add(cardPanel);
		mainPanel.add(movesLeft);
		mainPanel.add(playerLabel);
		mainPanel.add(endTurn);
		mainPanel.setLayout(null);
		setVisible(true);
		
		b.playerIndex=0;
		cardPanel.setLayout(new GridLayout(1,6));
		cardPanel.setPreferredSize(new Dimension(300,300));
		cardPanel.setBackground(Color.orange);
		cardPanel.setVisible(true);
		
		gamePanel.setLayout(new GridLayout(24,24));
		gamePanel.setBounds(100,300,600,600);
		gamePanel.setBackground(Color.black);
		for(int r = 0; r< 24; r++){
			for(int c = 0; c <24;c++){
				Cell cel = b.hl.array[r][c];
				tilePanel t;
				if(cel.door!=null)
				{
					t = new tilePanel(r,c, cel.real, cel.door.rooms);
					t.addActionListener(this);
					tileList.add(t);
				}
				else  
					{
					t = new tilePanel(r,c, cel.real, null);
					t.addActionListener(this);
					tileList.add(t);
					}
				gamePanel.add(t);
			}
		}
		
		updatePlayer();
		gamePanel.setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public tilePanel getTile(int r, int c){
		int index = r * 24 + c;
        return tileList.get(index);
	}
	
	//updates the grid area the player is at
	public void updatePlayer(){
		for(Player p:b.players){
			getTile(p.coord[0],p.coord[1]).setBackground(p.playerColor);
			JTextArea l = new JTextArea(p.person.toString());
			l.setLineWrap(true);
			l.setFont(new Font("Serif", Font.PLAIN, 10));
			getTile(p.coord[0],p.coord[1]).add(l);
		}
	}
	
	//updates card List
	public void updateCardGUI(){
		cardPanel.removeAll();
		for(Card c: getPlayer().cards){
			JLabel temp = new JLabel();
			if(c instanceof WeaponCard){
				WeaponCard w = (WeaponCard) c;
				switch(w.weapon){
				case Knife://scaled version of regular imageIcon
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Knife.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Candlestick:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Candlestick.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case LeadPipe:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Lead Pipe.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Revolver:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Revolver.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Rope:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Rope.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Wrench:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Wrench.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				}
			}
			else if(c instanceof PersonCard){
				PersonCard w = (PersonCard) c;
				switch(w.person){
				case ColonelMustard://scaled version of regular imageIcon
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Colonel Mustard.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case MissScarlett:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/ms scarlet.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case MrsWhite:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/white.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case ProfessorPlum:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Professor Plum.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case ReverendGreen:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/mr green.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case MrsPeacock:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/mrs peacock.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				}
			}
			else{
				RoomCard w = (RoomCard) c;
				switch(w.room){
				case Ballroom://scaled version of regular imageIcon
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Ball room.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Billiard:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Billiard Room.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Conservatory:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Conservatory.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case DiningRoom:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Dining.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Hall:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Hall.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Kitchen:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Kitchen.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Library:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Library.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Lounge:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Lounge.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				case Study:
					temp.setIcon(new ImageIcon(new ImageIcon("src/code/Study.jpg").
							getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH)));
					break;
				}
			}
			cardPanel.add(temp);
		}

	}
	
	public Player getPlayer(){
		return b.players.get(b.playerIndex);
	}
	
	public void createkeyPanel(){
		keyPanel.setLayout(new GridLayout(6,1));
		for(Player p:b.players){
			JLabel temp = new JLabel(p.person.toString());
			JPanel charKey = new JPanel();
			charKey.add(temp);
			charKey.setBackground(p.playerColor);
			keyPanel.add(charKey);
		}
		keyPanel.setBounds(800,300,100,600);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="move here"){
			for(tilePanel t:tileList){
				if(t.equals(e.getSource())){
					Player p = b.players.get(b.playerIndex);
					int oldRow = p.coord[0];
					int oldCol = p.coord[1];
					int oldDice = p.dice;
					int[][] moveArray = new int[2][1];
					moveArray[0][0]= t.row;
					moveArray[1][0]=t.col;
					p.move(moveArray);
					if(oldDice>p.dice)
					{
						if(getPlayer().place!=null){
							yourRoom.removeAllItems();
							yourRoom.addItem((getPlayer().place.type.toString()));
						}
						else {
							yourRoom.removeAllItems();
							yourRoom.addItem("No Room!"); //updates if player is still in a room or will be
						}
						
						movesLeft.setText("Moves Left: " + b.players.get(b.playerIndex).dice);
						updatePlayer();
						currentPos.setText("Current position:  row:" +b.players.get(b.playerIndex).coord[0]
								+ " col:" + b.players.get(b.playerIndex).coord[1]);
						b.hl.array[oldRow][oldCol].player= p;
						if(b.hl.array[oldRow][oldCol].door!=null){
							getTile(oldRow, oldCol).setBackground(Color.gray);
						}
						else{
							getTile(oldRow, oldCol).setBackground(Color.yellow);
						}
					}
				}
			}
		}
		
		//question, if more than one card is guessed wrongly, then how many cards should the player receive?
		if(e.getActionCommand()=="investigate"){
			String room = yourRoom.getSelectedItem().toString();
			String wep = weaponGuess.getSelectedItem().toString();
			String plyr = playerGuess.getSelectedItem().toString();
			boolean foundMatch = false;
			if(room!="No Room!")
			{
	    	for(int j = 0;j<b.players.size();j++)
	    	{
				for(int i = 0; i <b.players.get(j).cards.size();i++){
		    		System.out.println("Card size" + b.players.get(j).cards.size());
					Player p = b.players.get(j);//player whose cards ur checking
					System.out.println("i"+i);
					Card c = p.cards.get(i);
					if(c instanceof WeaponCard){
						WeaponCard w = (WeaponCard) c;
						if(w.weapon.toString()== wep){
							getPlayer().cards.add(c);
							b.players.get(j).cards.remove(c);
							foundMatch = true;
							updateCardGUI();
							result.setText("<html><br>Your Investigation Results: <br>"
									+b.players.get(j).person.toString()+" has disproved your accusation with the card: "
									+w.weapon.toString() + "<br> You have received the card: "+w.weapon.toString());
						}
					}
					if(c instanceof PersonCard){
						PersonCard w = (PersonCard) c;
						if(w.person.toString()== plyr){
							getPlayer().cards.add(c);
							b.players.get(j).cards.remove(c);
							foundMatch = true;
							updateCardGUI();
							result.setText("<html><br>Your Investigation Results: <br>"
									+b.players.get(j).person.toString()+" has disproved your accusation with the card: "
									+w.person.toString() + "<br> You have received the card: "+w.person.toString());
						}
					}
					if(c instanceof RoomCard){
						RoomCard w = (RoomCard) c;
						if(w.room.toString()== "DiningRoom"){
							getPlayer().cards.add(c);
							b.players.get(j).cards.remove(c);
							foundMatch = true;
							updateCardGUI();
							result.setText("<html><br>Your Investigation Results: <br>"
									+b.players.get(j).person.toString()+" has disproved your accusation with the card: "
									+w.room.toString() + "<br> You have received the card: "+w.room.toString());
						}
					}
					if(foundMatch==true) break;
			}
			cardPanel.revalidate();
			cardPanel.repaint();
			if(foundMatch==true) break;
		}
	  }
	else{
		result.setText("<html><br>Your Investigation Results: <br>"
				+ "You must be in a room to conduct an investigation!");
	}	
   }
  }

	
	
	public class tilePanel extends JButton{
		Room r;
		boolean isReal;
		int row, col;
		tilePanel(int r, int c, boolean real, Room ro){
			row = r;
			col = c;
			if(real==true){
				if(ro!=null){
					setBackground(Color.GRAY);
					}
				else {
					setBackground(Color.yellow);
				}
			}
			else 
				{
				setVisible(false);
				}
			this.setActionCommand("move here");
			}
			
		}


	}
