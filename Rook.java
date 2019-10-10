// Luke jacobs
// Rook
// Chess
public class Rook implements piece
{
	private String name;
	private String side;
	private int firstR;
	private int firstC;

	public Rook()
	{
		name = "Rook";
	}

	public Rook(String side)
	{
		name = "Rook";
		this.side = side;
	}

	public Rook(String side,int r,int c)
	{
		name = "Rook";
		this.side = side;
		firstR = r;
		firstC = c;
	}

	public String getSide()
	{
		return side;
	}

	public String getName()
	{
		return name;
	}

	public boolean hasMoved(int r,int c)
	{
		if(firstR == r)
			if(firstC == c)
				return false;
		return true;
	}
}