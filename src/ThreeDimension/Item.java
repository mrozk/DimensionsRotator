package ThreeDimension;

import java.io.Serializable;

/**
 * Created by mrozk on 24.07.14.
 */
public class Item implements Serializable {

    public int rotationType = 0;

    public int width = 0 ;

    public int height  = 0;

    public int depth  = 0;

    public Position pivot;

    public Item(int width,int height,int depth){
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return width  + "X" + height + "X" + depth + ", rotation=" + Integer.toString(rotationType);
    }
}
