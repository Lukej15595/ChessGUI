// Luke jacobs
// Knight
// Chess
public class Knight implements piece
{
	private String name;
	private String side;
	private int firstR;
	private int firstC;

	public Knight()
	{
		name = "Knight";
	}

	public Knight(String side)
	{
		name = "Knight";
		this.side = side;
	}

	public Knight(String side,int r,int c)
	{
		name = "Knight";
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