// Luke jacobs
// Queen
// Chess
public class Queen implements piece
{
	private String name;
	private String side;
	private int firstR;
	private int firstC;

	public Queen()
	{
		name = "Queen";
	}

	public Queen(String side)
	{
		name = "Queen";
		this.side = side;
	}

	public Queen(String side,int r,int c)
	{
		name = "Queen";
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