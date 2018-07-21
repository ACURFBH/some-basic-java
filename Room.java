
public class Room extends MapSite{
	public Wall[] sides;
	public MapSite[] mapsites;
	public int roomNumber;
	protected Room(){
		sides = new Wall[4];//don't need
		mapsites = new MapSite[10];//don't need
	}
	protected Room(int rn){
		roomNumber = rn;
		sides = new Wall[4];//don't need
		mapsites = new MapSite[10];//don't need
	}
	public Wall getSide(int side){
		return sides[side%4];
	}
	public void setSide(int side, Wall w){
		sides[side%4] = w;
	}
}
