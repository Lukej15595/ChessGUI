// Luke Jacobs
// Chess main object
// Chess
public class Chess
{
	private piece[][] board;

	public Chess()
	{
		board = new piece[8][8];
		boardSetup();
	}

	public void boardSetup()//sets up board and places pieces down for regular chess setup
	{
		//int pieTrack = 0;
		board[0][0] = new Rook("black",0,0);
		board[0][1] = new Knight("black",0,1);
		board[0][2] = new Bishop("black",0,2);
		board[0][3] = new Queen("black",0,3);
		board[0][4] = new King("black",0,4);
		board[0][5] = new Bishop("black",0,5);
		board[0][6] = new Knight("black",0,6);
		board[0][7] = new Rook("black",0,7);
		board[7][0] = new Rook("white",7,0);
		board[7][1] = new Knight("white",7,1);
		board[7][2] = new Bishop("white",7,2);
		board[7][3] = new Queen("white",7,3);
		board[7][4] = new King("white",7,4);
		board[7][5] = new Bishop("white",7,5);
		board[7][6] = new Knight("white",7,6);
		board[7][7] = new Rook("white",7,7);

		for(int c = 0;c < board[1].length;c++)
		{
			board[1][c] = new Pawn("black",1,c);
		}
		for(int c = 0;c < board[6].length;c++)
		{
			board[6][c] = new Pawn("white",6,c);
		}

		for(int i = 0;i < board.length;i++)
		{
			for(int c = 0;c < board[i].length;c++)
			{
				if(i >= 2 && i <=5)
				{
					board[i][c] = null;
				}
			}
		}
	}
	public String getBoard()// helper
	{
		String print = "";
		for(piece b[]: board)
		{
			for(piece y: b)
			{
				if(y == null)
				{
					print += "null\t";
				}
				else if(y.getName().equals("Pawn"))
				{
					print += "Pawn\t";
				}
				else if(y.getName().equals("Knight"))
				{
					print+= "Knight\t";
				}
				else if(y.getName().equals("King"))
				{
					print += "King\t";
				}
				else if(y.getName().equals("Bishop"))
				{
					print += "Bishop\t";
				}
				else if(y.getName().equals("Rook"))
				{
					print += "Rook\t";
				}
				else if(y.getName().equals("Queen"))
				{
					print+= "Queen\t";
				}
			}
			print += "\n";
		}
		return print;
	}
	public piece getPiece(int r,int c)//returns piece when selected by the button
	{
		if(board[r][c] == null)
		{
			return null;
		}
		else
		{
			return board[r][c];
		}
	}
	public void updatePiece(int prevr,int prevc,int r, int c)// switches the pieces after a movement is made
	{
		/*if(board[r][c] != null)
		{
			piece hold = board[r][c];
			board[r][c] = board[prevr][prevc];
			board[prevr][prevc] = hold;
		}
		else
		{*/
			board[r][c] = board[prevr][prevc];
			board[prevr][prevc] = null;
		//}
	}

	public boolean allowedMovementPawn(int rPos, int cPos, int rMove, int cMove, String side, boolean moved)//allowed movements for pawns
	{
		if(side == "black")
		{
			if(board[rMove][cMove] == null && rMove-rPos == 1 && cMove-cPos == 0) // moving up into empty space
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove-rPos == 1 && Math.abs(cMove-cPos) == 1) // moving diagonally into occupied space
			{
				return true;
			}
			else if(board[rMove][cMove] == null && moved == false && rMove-rPos == 2 && cMove-cPos == 0) //moving two spaces if never moved before
			{
				return true;
			}
		}
		else if(side == "white")
		{
			if(board[rMove][cMove] == null && rMove-rPos == -1 && cMove-cPos == 0)
			{
				return true;
			}
			else if(board[rMove][cMove] != null && rMove-rPos == -1 && board[rMove][cMove].getSide().equals("black") && Math.abs(cMove-cPos) == 1)
			{
				return true;
			}
			else if(board[rMove][cMove] == null && moved == false && rMove-rPos == -2 && cMove-cPos == 0)
			{
				return true;
			}
		}
		return false;
	}

	public boolean allowedMovementKnight(int rPos, int cPos, int rMove, int cMove, String side, boolean moved)//allowed movement for knights
	{
		if(side == "white")
		{
			if(board[rMove][cMove] == null && Math.abs(rMove-rPos)== 2 && Math.abs(cMove-cPos) == 1)// moving two up/down and one left/right
			{
				return true;
			}
			else if(board[rMove][cMove] == null && Math.abs(cMove-cPos)== 2 && Math.abs(rMove-rPos) == 1)// moving two left/right and one up/down
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && Math.abs(cMove-cPos)== 2 && Math.abs(rMove-rPos) == 1)//taking piece moving two left/right
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && Math.abs(rMove-rPos) == 2 && Math.abs(cMove-cPos) == 1)//taking piece moving two up/down
			{
				return true;
			}
		}
		else if(side == "black")
		{
			if(board[rMove][cMove] == null && Math.abs(rMove-rPos)== 2 && Math.abs(cMove-cPos) == 1)
			{
				return true;
			}
			else if(board[rMove][cMove] == null && Math.abs(cMove-cPos)== 2 && Math.abs(rMove-rPos) == 1)
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && Math.abs(cMove-cPos)== 2 && Math.abs(rMove-rPos) == 1)
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && Math.abs(rMove-rPos) == 2 && Math.abs(cMove-cPos) == 1)
			{
				return true;
			}
		}
		return false;
	}

	public boolean allowedMovementRook(int rPos, int cPos, int rMove, int cMove, String side, boolean moved)//allowed movement for rooks
	{
		if(side == "white")
		{
			if(board[rMove][cMove] == null && cMove == cPos && rPos-rMove > 0)//moving up
			{
				return movingUp(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && cMove == cPos && rPos-rMove >0)//moving up onto occupied space
			{
				return movingUp(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] == null && cMove == cPos && rPos - rMove < 0)//moving down
			{
				return movingDown(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && cMove == cPos && rPos - rMove < 0)//moving down into occupied space
			{
				return movingDown(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] == null && rMove == rPos && cPos - cMove > 0)//moving left
			{
				return movingLeft(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove == rPos && cPos - cMove > 0)//moving left into occupied space
			{
				return movingLeft(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] == null && rMove == rPos && cPos - cMove < 0)//moving right
			{
				return movingRight(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove == rPos && cPos - cMove < 0)
			{
				return movingRight(cPos,cMove,rPos);
			}
		}
		else if(side == "black")
		{
			if(board[rMove][cMove] == null && cMove == cPos && rPos-rMove > 0)//moving up
			{
				return movingUp(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && cMove == cPos && rPos-rMove >0)//moving up onto occupied space
			{
				return movingUp(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] == null && cMove == cPos && rPos - rMove < 0)//moving down
			{
				return movingDown(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && cMove == cPos && rPos - rMove < 0)//moving down into occupied space
			{
				return movingDown(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] == null && rMove == rPos && cPos - cMove > 0)//moving left
			{
				return movingLeft(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove == rPos && cPos - cMove > 0)//moving left into occupied space
			{
				return movingLeft(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] == null && rMove == rPos && cPos - cMove < 0)//moving right
			{
				return movingRight(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove == rPos && cPos - cMove < 0)//moving right into occupied spaces
			{
				return movingRight(cPos,cMove,rPos);
			}
		}
		return false;
	}

	public boolean allowedMovementBishop(int rPos,int cPos,int rMove,int cMove,String side,boolean moved)
	{
		if(side == "white")
		{
			if(board[rMove][cMove] == null && rPos - rMove == cPos - cMove)// moving left up
			{
				return movingLeftUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rPos - rMove == cPos - cMove)//moving left up into occupied piece
			{
				return movingLeftUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rPos - rMove == cMove - cPos)// moving right up
			{
				return movingRightUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rPos - rMove == cMove - cPos)//moving right up into occupied space
			{
				return movingRightUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rMove - rPos == cPos - cMove)//moving left down
			{
				return movingLeftDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove - rPos == cPos - cMove)//moving left down into occupied space
			{
				return movingLeftDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rMove - rPos == cMove - cPos)//moving right down
			{
				return movingRightDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove - rPos == cMove - cPos)//moving right down into occupied space
			{
				return movingRightDownDiag(rPos,rMove,cPos,cMove);
			}
		}
		else if(side == "black")
		{
			if(board[rMove][cMove] == null && rPos - rMove == cPos - cMove)// moving left up
			{
				return movingLeftUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rPos - rMove == cPos - cMove)//moving left up into occupied piece
			{
				return movingLeftUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rPos - rMove == cMove - cPos)// moving right up
			{
				return movingRightUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rPos - rMove == cMove - cPos)//moving right up into occupied space
			{
				return movingRightUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rMove - rPos == cPos - cMove)//moving left down
			{
				return movingLeftDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove - rPos == cPos - cMove)//moving left down into occupied space
			{
				return movingLeftDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rMove - rPos == cMove - cPos)//moving right down
			{
				return movingRightDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove - rPos == cMove - cPos)//moving right down into occupied space
			{
				return movingRightDownDiag(rPos,rMove,cPos,cMove);
			}
		}
		return false;
	}

	public boolean allowedMovementQueen(int rPos,int cPos,int rMove,int cMove,String side,boolean moved)
	{
		if(side == "white")
		{
			if(board[rMove][cMove] == null && rPos - rMove == cPos - cMove)// moving left up
			{
				return movingLeftUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rPos - rMove == cPos - cMove)//moving left up into occupied piece
			{
				return movingLeftUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rPos - rMove == cMove - cPos)// moving right up
			{
				return movingRightUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rPos - rMove == cMove - cPos)//moving right up into occupied space
			{
				return movingRightUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rMove - rPos == cPos - cMove)//moving left down
			{
				return movingLeftDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove - rPos == cPos - cMove)//moving left down into occupied space
			{
				return movingLeftDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rMove - rPos == cMove - cPos)//moving right down
			{
				return movingRightDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove - rPos == cMove - cPos)//moving right down into occupied space
			{
				return movingRightDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && cMove == cPos && rPos-rMove > 0)//moving up
			{
				return movingUp(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && cMove == cPos && rPos-rMove >0)//moving up onto occupied space
			{
				return movingUp(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] == null && cMove == cPos && rPos - rMove < 0)//moving down
			{
				return movingDown(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && cMove == cPos && rPos - rMove < 0)//moving down into occupied space
			{
				return movingDown(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] == null && rMove == rPos && cPos - cMove > 0)//moving left
			{
				return movingLeft(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove == rPos && cPos - cMove > 0)//moving left into occupied space
			{
				return movingLeft(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] == null && rMove == rPos && cPos - cMove < 0)//moving right
			{
				return movingRight(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove == rPos && cPos - cMove < 0)
			{
				return movingRight(cPos,cMove,rPos);
			}
		}
		else if(side == "black")
		{
			if(board[rMove][cMove] == null && rPos - rMove == cPos - cMove)// moving left up
			{
				return movingLeftUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rPos - rMove == cPos - cMove)//moving left up into occupied piece
			{
				return movingLeftUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rPos - rMove == cMove - cPos)// moving right up
			{
				return movingRightUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rPos - rMove == cMove - cPos)//moving right up into occupied space
			{
				return movingRightUpDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rMove - rPos == cPos - cMove)//moving left down
			{
				return movingLeftDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove - rPos == cPos - cMove)//moving left down into occupied space
			{
				return movingLeftDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && rMove - rPos == cMove - cPos)//moving right down
			{
				return movingRightDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove - rPos == cMove - cPos)//moving right down into occupied space
			{
				return movingRightDownDiag(rPos,rMove,cPos,cMove);
			}
			else if(board[rMove][cMove] == null && cMove == cPos && rPos-rMove > 0)//moving up
			{
				return movingUp(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && cMove == cPos && rPos-rMove >0)//moving up onto occupied space
			{
				return movingUp(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] == null && cMove == cPos && rPos - rMove < 0)//moving down
			{
				return movingDown(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && cMove == cPos && rPos - rMove < 0)//moving down into occupied space
			{
				return movingDown(rPos,rMove,cPos);
			}
			else if(board[rMove][cMove] == null && rMove == rPos && cPos - cMove > 0)//moving left
			{
				return movingLeft(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove == rPos && cPos - cMove > 0)//moving left into occupied space
			{
				return movingLeft(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] == null && rMove == rPos && cPos - cMove < 0)//moving right
			{
				return movingRight(cPos,cMove,rPos);
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove == rPos && cPos - cMove < 0)
			{
				return movingRight(cPos,cMove,rPos);
			}
		}
		return false;
	}

	public boolean allowedMovementKing(int rPos,int cPos,int rMove,int cMove,String side,boolean moved)
	{
		if(side == "white")
		{
			if(board[rMove][cMove] == null && Math.abs(rMove-rPos) == 1 && Math.abs(cMove-cPos) == 1)//moving diagonal
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && Math.abs(rMove-rPos) == 1 && Math.abs(cMove-cPos) == 1)
			{
				return true;
			}
			else if(board[rMove][cMove] == null && Math.abs(rMove - rPos) == 1 && cMove - cPos == 0)//moving up or down
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && Math.abs(rMove-rPos) == 1 && cMove-cPos == 0)
			{
				return true;
			}
			else if(board[rMove][cMove] == null && rMove - rPos == 0 && Math.abs(cMove - cPos) == 1)//moving left or right
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("black") && rMove - rPos == 0 && Math.abs(cMove - cPos) == 1)
			{
				return true;
			}
		}
		else if(side == "black")
		{
			if(board[rMove][cMove] == null && Math.abs(rMove-rPos) == 1 && Math.abs(cMove-cPos) == 1)//moving diagonal
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && Math.abs(rMove-rPos) == 1 && Math.abs(cMove-cPos) == 1)
			{
				return true;
			}
			else if(board[rMove][cMove] == null && Math.abs(rMove - rPos) == 1 && cMove - cPos == 0)//moving up or down
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && Math.abs(rMove-rPos) == 1 && cMove-cPos == 0)
			{
				return true;
			}
			else if(board[rMove][cMove] == null && rMove - rPos == 0 && Math.abs(cMove - cPos) == 1)//moving left or right
			{
				return true;
			}
			else if(board[rMove][cMove] != null && board[rMove][cMove].getSide().equals("white") && rMove - rPos == 0 && Math.abs(cMove - cPos) == 1)
			{
				return true;
			}
		}
		return false;
	}

	public boolean movingUp(int rPos,int rMove,int col) // helper method for when pieces move upwards in a straight line
	{
		boolean hold = true;
		for(int r = rPos - 1;r > rMove;r--)
		{
			if(board[r][col] != null)
				hold = false;
		}
		return hold;
	}

	public boolean movingDown(int rPos,int rMove,int col)// helper method for when pieces move downwards in a straight line
	{
		boolean hold = true;
		for(int r = rPos + 1;r < rMove;r++)
		{
			if(board[r][col] != null)
				hold = false;
		}
		return hold;
	}

	public boolean movingLeft(int cPos,int cMove,int row)// helper method for when pieces move left in straight line
	{
		boolean hold = true;
		for(int c = cPos - 1;c > cMove;c--)
		{
			if(board[row][c] != null)
				hold = false;
		}
		return hold;
	}

	public boolean movingRight(int cPos,int cMove,int row)// helper method for when pieces move right in straight line
	{
		boolean hold = true;
		for(int c = cPos + 1;c < cMove;c++)
		{
			if(board[row][c] != null)
				hold = false;
		}
		return hold;
	}

	public boolean movingLeftUpDiag(int rPos,int rMove,int cPos,int cMove)// helper method for when pieces move left and up diag
	{
		boolean hold = true;
		int c = cPos - 1;
		for(int r = rPos - 1;r > rMove;r--)
		{
			if(board[r][c] != null)
				hold = false;
			c--;
		}
		return hold;
	}

	public boolean movingRightUpDiag(int rPos,int rMove,int cPos,int cMove)//helper method for when pieces move right and up diag
	{
		boolean hold = true;
		int c = cPos + 1;
		for(int r = rPos - 1;r > rMove;r--)
		{
			if(board[r][c] != null)
				hold = false;
			c++;
		}
		return hold;
	}

	public boolean movingLeftDownDiag(int rPos,int rMove,int cPos,int cMove)//helper method for when pieces move left and down diag
	{
		boolean hold = true;
		int c = cPos - 1;
		for(int r = rPos + 1;r < rMove;r++)
		{
			if(board[r][c] != null)
				hold = false;
			c--;
		}
		return hold;
	}

	public boolean movingRightDownDiag(int rPos,int rMove,int cPos,int cMove)//helper method for when pieces move right and down diag
	{
		boolean hold = true;
		int c = cPos +1;
		for(int r = rPos + 1;r < rMove;r++)
		{
			if(board[r][c] != null)
				hold = false;
			c++;
		}
		return hold;
	}

	public boolean canCastle(String side,boolean moved)//checks if castling is allowed for kings
	{
		if(side == "white" && moved == false)
		{
			if(board[7][7].getName().equals("Rook"))
			{
				if(board[7][5] == null && board[7][6] == null)
				{
					return true;
				}
			}
		}
		else if(side == "black" && moved == false)
		{
			if(board[0][7].getName().equals("Rook"))
			{
				if(board[0][5] == null && board[0][6] == null)
				{
					return true;
				}
			}
		}
		return false;
	}

	public boolean check(String side)// sees if the king is in chess
	{
		int row = 0;
		int col = 0;

		if(side == "white")//gets the white king
		{
			for(int r = 0;r < board.length;r++)
			{
				for(int c = 0;c < board[r].length;c++)
				{
					if(board[r][c] != null && board[r][c].getName().equals("King") && board[r][c].getSide().equals("white"))
					{
						row = r;
						col = c;
						System.out.println("white king");
					}
				}
			}
			for(int r = 0;r < board.length;r++)//sees if any opposite color piece can take the king
			{
				for(int c = 0;c < board[r].length;c++)
				{
					if(board[r][c] != null && board[r][c].getSide().equals("black"))
					{
						if(board[r][c].getName().equals("Pawn"))
						{
							if(allowedMovementPawn(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;
						}
						if(board[r][c].getName().equals("Knight"))
						{
							if(allowedMovementKnight(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
							{
								System.out.println("Knight");
								return true;
							}
						}
						else if(board[r][c].getName().equals("Bishop"))
						{
							if(allowedMovementBishop(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;

						}
						else if(board[r][c].getName().equals("Rook"))
						{
							if(allowedMovementRook(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;
								//System.out.println("Rook");
						}
						else if(board[r][c].getName().equals("Queen"))
						{
							if(allowedMovementQueen(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;
								//System.out.println("Queen");
						}
					}
				}
			}
		}
		else if(side == "black")//gets the black king
		{
			for(int r = 0;r < board.length;r++)
			{
				for(int c = 0;c < board[r].length;c++)
				{
					if(board[r][c] != null && board[r][c].getName().equals("King") && board[r][c].getSide().equals("black"))
					{
						row = r;
						col = c;
						System.out.println("black king");
					}
				}
			}
			for(int r = 0;r < board.length;r++)//sees if every opposite color piece can take the king
			{
				for(int c = 0;c < board[r].length;c++)
				{
					if(board[r][c] != null && board[r][c].getSide().equals("white"))
					{
						if(board[r][c].getName().equals("Pawn"))
						{
							if(allowedMovementPawn(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;
						}
						if(board[r][c].getName().equals("Knight"))
						{
							if(allowedMovementKnight(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;
								//System.out.println("Knight");
						}
						else if(board[r][c].getName().equals("Bishop"))
						{
							if(allowedMovementBishop(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;
								//System.out.println("Bishop");
						}
						else if(board[r][c].getName().equals("Rook"))
						{
							if(allowedMovementRook(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;
								//System.out.println("Rook");
						}
						else if(board[r][c].getName().equals("Queen"))
						{
							if(allowedMovementQueen(r,c,row,col,board[r][c].getSide(),board[r][c].hasMoved(r,c)))
								return true;
								//System.out.println("Queen");
						}
					}
				}
			}
		}
		return false;
	}

	/*public boolean checkMate(String side)//sees if the king is in checkmate
	{
		for(int r = 0;r < board.length;r++)
		{*/

}