// Luke jacobs
// King
// Chess
public class King implements piece
{
	private String name;
	private String side;
	private int firstR;
	private int firstC;
	public King()
	{
		name = "King";
	}

	public King(String side)
	{
		name = "King";
		this.side = side;
	}

	public King(String side,int r,int c)
	{
		name = "King";
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