// Luke jacobs
// Chess GUI
// Final Project

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class ChessGUI extends JFrame
{
	private JPanel Buttons;
	private JButton[][] buttons;
	private ImageIcon wRook, wKnight, wBishop, wKing, wQueen, wPawn;
	private ImageIcon bRook, bKnight, bBishop, bKing, bQueen, bPawn;
	private ButtonListener listener;
	private JTextArea jtaOutput;
	private boolean secondClick;
	private boolean side;
	private int turnCounter; // determines whether it is white or blacks turn based on mod 2
	private int prevr;
	private int prevc;
	private Chess ch;


	public ChessGUI()
	{
		super("Chess");
		Container cp = getContentPane();
		Buttons = new JPanel();
		GridLayout buttonLayout = new GridLayout(8,8); // chessboard
		Buttons.setLayout(buttonLayout); // sets grid of buttons
		cp.setLayout(new BorderLayout());
		buttons = new JButton[8][8];
		secondClick = false;;
		turnCounter = 1;
		prevr = 0;
		prevc = 0;
		ch = new Chess();
		ImageIcon wRook = new ImageIcon("wRook.png");
		ImageIcon wKnight = new ImageIcon("wKnight.png");
		ImageIcon wBishop = new ImageIcon("wBishop.png");
		ImageIcon wKing = new ImageIcon("wKing.png");
		ImageIcon wQueen = new ImageIcon("wQueen.png");
		ImageIcon wPawn = new ImageIcon("wPawn.png");
		ImageIcon bRook = new ImageIcon("bRook.png");
		ImageIcon bKnight = new ImageIcon("bKnight.png");
		ImageIcon bBishop = new ImageIcon("bBishop.png");
		ImageIcon bKing = new ImageIcon("bKing.png");
		ImageIcon bQueen = new ImageIcon("bQueen.png");
		ImageIcon bPawn = new ImageIcon("bPawn.png");
		buttons[0][0] = new JButton(bRook);
		buttons[0][1] = new JButton(bKnight);
		buttons[0][2] = new JButton(bBishop);
		buttons[0][3] = new JButton(bQueen);
		buttons[0][4] = new JButton(bKing);
		buttons[0][5] = new JButton(bBishop);
		buttons[0][6] = new JButton(bKnight);
		buttons[0][7] = new JButton(bRook);
		listener = new ButtonListener();
		for(int c = 0;c < buttons[0].length;c++)//first row
		{
			buttons[0][c].addActionListener(listener);
			Buttons.add(buttons[0][c]);
		}
		for(int c = 0;c < buttons[1].length;c++)//second row (first row of pawns)
		{
			buttons[1][c] = new JButton(bPawn);
			buttons[1][c].addActionListener(listener);
			Buttons.add(buttons[1][c]);
		}
		for(int r = 2;r < buttons.length-2;r++) // instantiates null buttons
		{
			for(int c = 0;c < buttons[r].length;c++)
			{
				buttons[r][c] = new JButton();
				buttons[r][c].addActionListener(listener);
				Buttons.add(buttons[r][c]);
			}
		}
		for(int c = 0;c < buttons[6].length;c++)//first row (first row of pawns)
		{
			buttons[6][c] = new JButton(wPawn);
			buttons[6][c].addActionListener(listener);
			Buttons.add(buttons[6][c]);
		}
		buttons[7][0] = new JButton(wRook);
		buttons[7][1] = new JButton(wKnight);
		buttons[7][2] = new JButton(wBishop);
		buttons[7][3] = new JButton(wQueen);
		buttons[7][4] = new JButton(wKing);
		buttons[7][5] = new JButton(wBishop);
		buttons[7][6] = new JButton(wKnight);
		buttons[7][7] = new JButton(wRook);
		for(int c = 0;c < buttons[7].length;c++)//second row for whites
		{
			buttons[7][c].addActionListener(listener);
			Buttons.add(buttons[7][c]);
		}
		for(int r = 0;r < buttons.length;r++)//sets color of chess tiles
		{
			for(int c = 0;c < buttons[r].length;c++)
			{
				if((r+c)%2==1)
				{
					buttons[r][c].setBackground(new Color(139,69,19));
					//buttons[r][c].setBorderPainted(false);
					buttons[r][c].setOpaque(true);
				}
				else
				{
					buttons[r][c].setBackground(new Color(120,20,40));
					buttons[r][c].setOpaque(true);
				}
			}
		}
		cp.add(Buttons, BorderLayout.CENTER);
		setSize(800,800);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		ChessGUI foo = new ChessGUI();
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			System.out.println(turnCounter);
			for(int i = 0;i < buttons.length;i++)
			{

				for(int c = 0;c < buttons[i].length;c++)
				{
					if(source == buttons[i][c])
					{
						//System.out.print("works");

						if((turnCounter % 2 == 0) && ch.getPiece(i,c) != null && ch.getPiece(i,c).getSide().equals("black") && secondClick == false)//blacks move
						{
							prevr = i;
							prevc = c;
							secondClick = true;
							buttons[i][c].setOpaque(false);
						}
						else if((turnCounter %2 != 0) && ch.getPiece(i,c) != null && ch.getPiece(i,c).getSide().equals("white") && secondClick == false)//whites move
						{
							prevr = i;
							prevc = c;
							secondClick = true;
							buttons[i][c].setOpaque(false);
						}
						else if(secondClick == true)//if piece is moving into space occupied by another piece
						{
							if(buttons[prevr][prevc] == buttons[i][c])
							{
								//System.out.println("is this it");
								secondClick = false;
								buttons[prevr][prevc].setOpaque(true);
							}
							else if(ch.getPiece(prevr,prevc).getName().equals("Pawn"))
							{
								if(ch.allowedMovementPawn(prevr,prevc,i,c,ch.getPiece(prevr,prevc).getSide(),ch.getPiece(prevr,prevc).hasMoved(prevr,prevc)))
								{

									buttons[i][c].setIcon(buttons[prevr][prevc].getIcon());
									buttons[prevr][prevc].setIcon(null);
									buttons[prevr][prevc].setOpaque(true);
									ch.updatePiece(prevr,prevc,i,c);
									secondClick = false;
									turnCounter++;
									if(ch.check("white") == true)//lets u know if a king is in check
									{
										JOptionPane.showMessageDialog(new Frame(),"White King is in check");
									}
									else if(ch.check("black") == true)
									{
										JOptionPane.showMessageDialog(new Frame(),"Black King is in check");
									}
								}
							}
							else if(ch.getPiece(prevr,prevc).getName().equals("Knight"))
							{
								if(ch.allowedMovementKnight(prevr,prevc,i,c,ch.getPiece(prevr,prevc).getSide(),ch.getPiece(prevr,prevc).hasMoved(prevr,prevc)))
								{

									buttons[i][c].setIcon(buttons[prevr][prevc].getIcon());
									buttons[prevr][prevc].setIcon(null);
									buttons[prevr][prevc].setOpaque(true);
									ch.updatePiece(prevr,prevc,i,c);
									secondClick = false;
									turnCounter++;
									if(ch.check("white") == true)//lets u know if a king is in check
									{
										JOptionPane.showMessageDialog(new Frame(),"White King is in check");
									}
									else if(ch.check("black") == true)
									{
										JOptionPane.showMessageDialog(new Frame(),"Black King is in check");
									}

								}
							}
							else if(ch.getPiece(prevr,prevc).getName().equals("Rook"))
							{
								if(ch.allowedMovementRook(prevr,prevc,i,c,ch.getPiece(prevr,prevc).getSide(),ch.getPiece(prevr,prevc).hasMoved(prevr,prevc)))
								{

									buttons[i][c].setIcon(buttons[prevr][prevc].getIcon());
									buttons[prevr][prevc].setIcon(null);
									buttons[prevr][prevc].setOpaque(true);
									ch.updatePiece(prevr,prevc,i,c);
									secondClick = false;
									turnCounter++;
									if(ch.check("white") == true)//lets u know if a king is in check
									{
										JOptionPane.showMessageDialog(new Frame(),"White King is in check");
									}
									else if(ch.check("black") == true)
									{
										JOptionPane.showMessageDialog(new Frame(),"Black King is in check");
									}
								}
							}
							else if(ch.getPiece(prevr,prevc).getName().equals("Bishop"))
							{
								if(ch.allowedMovementBishop(prevr,prevc,i,c,ch.getPiece(prevr,prevc).getSide(),ch.getPiece(prevr,prevc).hasMoved(prevr,prevc)))
								{

									buttons[i][c].setIcon(buttons[prevr][prevc].getIcon());
									buttons[prevr][prevc].setIcon(null);
									buttons[prevr][prevc].setOpaque(true);
									ch.updatePiece(prevr,prevc,i,c);
									secondClick = false;
									turnCounter++;
									if(ch.check("white") == true)//lets u know if a king is in check
									{
										JOptionPane.showMessageDialog(new Frame(),"White King is in check");
									}
									else if(ch.check("black") == true)
									{
										JOptionPane.showMessageDialog(new Frame(),"Black King is in check");
									}
								}
							}
							else if(ch.getPiece(prevr,prevc).getName().equals("Queen"))
							{
								if(ch.allowedMovementQueen(prevr,prevc,i,c,ch.getPiece(prevr,prevc).getSide(),ch.getPiece(prevr,prevc).hasMoved(prevr,prevc)))
								{

									buttons[i][c].setIcon(buttons[prevr][prevc].getIcon());
									buttons[prevr][prevc].setIcon(null);
									buttons[prevr][prevc].setOpaque(true);
									ch.updatePiece(prevr,prevc,i,c);
									secondClick = false;
									turnCounter++;
									if(ch.check("white") == true)//lets u know if a king is in check
									{
										JOptionPane.showMessageDialog(new Frame(),"White King is in check");
									}
									else if(ch.check("black") == true)
									{
										JOptionPane.showMessageDialog(new Frame(),"Black King is in check");
									}
								}
							}
							else if(ch.getPiece(prevr,prevc).getName().equals("King"))
							{
								if(ch.allowedMovementKing(prevr,prevc,i,c,ch.getPiece(prevr,prevc).getSide(),ch.getPiece(prevr,prevc).hasMoved(prevr,prevc)))
								{
									buttons[i][c].setIcon(buttons[prevr][prevc].getIcon());
									buttons[prevr][prevc].setIcon(null);
									buttons[prevr][prevc].setOpaque(true);
									ch.updatePiece(prevr,prevc,i,c);
									secondClick = false;
									turnCounter++;
								}
								else if(c == 6 &&(i == 7 || i == 0))//seeing if the king can castle
								{
									if(ch.getPiece(prevr,prevc).getSide().equals("white"))
									{
										if(ch.canCastle("white",ch.getPiece(prevr,prevc).hasMoved(prevr,prevc)))
										{
											buttons[i][c].setIcon(buttons[prevr][prevc].getIcon());
											buttons[prevr][prevc].setIcon(null);
											buttons[prevr][prevc].setOpaque(true);
											ch.updatePiece(prevr,prevc,i,c);
											buttons[7][5].setIcon(buttons[7][7].getIcon());
											buttons[7][7].setIcon(null);
											buttons[7][7].setOpaque(true);
											ch.updatePiece(7,7,7,5);
											secondClick = false;
											turnCounter++;
										}
									}
									else if(ch.getPiece(prevr,prevc).getSide().equals("black"))
									{
										if(ch.canCastle("black",ch.getPiece(prevr,prevc).hasMoved(prevr,prevc)))
										{
											buttons[i][c].setIcon(buttons[prevr][prevc].getIcon());
											buttons[prevr][prevc].setIcon(null);
											buttons[prevr][prevc].setOpaque(true);
											ch.updatePiece(prevr,prevc,i,c);
											buttons[0][5].setIcon(buttons[0][7].getIcon());
											buttons[0][7].setIcon(null);
											buttons[0][7].setOpaque(true);
											ch.updatePiece(0,7,0,5);
											secondClick = false;
											turnCounter++;
										}
									}
								}
							}

							System.out.println(turnCounter);
							System.out.println(ch.getBoard());
						}
					}
				}
			}
		}
	}
}