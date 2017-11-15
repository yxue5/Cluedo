package code;
import javax.swing.*;

import code.Hallway.Cell;

import java.awt.*;
import java.awt.event.*;
public class Game implements ActionListener{
	Board b;
	BoardGUI bGUI;
	JPanel allPanels = new JPanel();
	JPanel startPanel = new JPanel();
	JButton start = new JButton("Start");
	CardLayout cl = new CardLayout();
	
	boolean wonGame = false;
	
	public static void main(String[] args){
		Game g = new Game();
		g.initGame();
	}
	
	public void initGame(){
	b = new Board();
	bGUI = new BoardGUI(b);
	bGUI.b.rollDice();
	
	startPanel.add(start);
	startPanel.setBackground(Color.darkGray);
	startPanel.setVisible(true);
	allPanels.setLayout(cl);
	allPanels.add(startPanel, "1");
	allPanels.add(bGUI.mainPanel, "2");
	allPanels.setVisible(true);
	start.setActionCommand("start");
	start.addActionListener(this);
	
	cl.show(allPanels, "1");
	bGUI.add(allPanels);
	bGUI.setVisible(true);
	bGUI.endTurn.setActionCommand("endTurn");
	bGUI.endTurn.addActionListener(this);
	}
	public void gameLoop(){
		if(wonGame == false){
			if(bGUI.b.playerIndex<b.players.size()-1)
			{
			bGUI.b.playerIndex++;
			}
			else bGUI.b.playerIndex=0;
			bGUI.b.rollDice();
			bGUI.updateCardGUI();
			bGUI.movesLeft.setText("Moves Left: " + bGUI.b.players.get(b.playerIndex).dice);
			bGUI.playerLabel.setText("Current Player: " + bGUI.b.players.get(b.playerIndex).person.toString());
			bGUI.currentPos.setText("Current position:  row:" +b.players.get(b.playerIndex).coord[0]
					+ " col:" + b.players.get(b.playerIndex).coord[1]);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("start")){
			cl.show(allPanels, "2");
		}
		if(e.getActionCommand().equals("endTurn"));{
			gameLoop();
		}
	}
	public void endGame(){
		
	}
}
