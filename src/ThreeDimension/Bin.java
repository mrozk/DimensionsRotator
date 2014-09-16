package ThreeDimension;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mrozk on 22.07.14.
 */
public class Bin implements Serializable {
    public int width = 0 ;

    public int height  = 0;

    public int depth  = 0;

    public ArrayList<Item> items = new ArrayList<Item>();

    public Bin(int width,int height,int depth){
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    public String toString() {
        String resultString = "";
        resultString += "capacity of bin " + width + "X" + height + "X" + depth + "; ";
        if( items.size() > 0 ){
            for( int i = 0; i < items.size(); i++ ){
                resultString += "item " + items.get(i).toString() + ", pivot - " + items.get(i).pivot.toString() + ": ";
            }
        }
        return resultString;
    }
}
