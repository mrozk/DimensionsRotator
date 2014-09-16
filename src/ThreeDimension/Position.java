package ThreeDimension;

/**
 * Created by mrozk on 22.07.14.
 */
public class Position {
    public int x;

    public int y;

    public int z;

    public Position(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public String toString() {
        return "x=" + x  + ",y=" + y + ",z=" + z;
    }

}
