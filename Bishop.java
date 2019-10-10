// Luke jacobs
// Bishop
// Chess
public class Bishop implements piece
{
	private String name;
	private String side;
	private int firstR;
	private int firstC;

	public Bishop()
	{
		name = "Bishop";
	}

	public Bishop(String side)
	{
		name = "Bishop";
		this.side = side;
	}

	public Bishop(String side,int r,int c)
	{
		name = "Bishop";
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