// Luke jacobs
// Pawn
// Chess
public class Pawn implements piece
{
	private String name;
	private String side;
	private int firstR;
	private int firstC;
	public Pawn()
	{
		name = "Pawn";
	}

	public Pawn(String side)
	{
		name = "Pawn";
		this.side = side;
	}

	public Pawn(String side,int r,int c)
	{
		name = "Pawn";
		this.side = side;
		firstR = r;
		firstC = c;
	}

	public String getName()
	{
		return name;
	}

	public String getSide()
	{
		return side;
	}

	public boolean hasMoved(int r,int c)
	{
		if(firstR==r)
			if(firstC==c)
				return false;
		return true;
	}
}