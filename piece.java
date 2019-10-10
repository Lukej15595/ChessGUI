// Luke jacobs
// Piece interface
// Chess
public interface piece
{
	//public boolean allowedMovement(piece[][] board,int rpos, int lpos, int moves,String direction);//determines whether the move is valid based on the type of piece

	public String getName();

	public String getSide();
	//public boolean getSide(/*String piece,String pieceTwo*/); // side %2 == 0 than it is white's second click (white turn), if not than it is black's

	public boolean hasMoved(int r,int c);
}

