package ThreeDimension;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mrozk on 02.08.14.
 */
public class ThreeDimensionNew {

    public int binWidth = 10;

    public int binHeight = 5;

    public int binDepth = 3;

    public static void main( String[] args ){
        ThreeDimensionNew th = new ThreeDimensionNew();
        String desigion = "n";
        do{
            th.proccess();
            System.out.println("Попробовать еще: (y/n)");
            Scanner userInput = new Scanner(System.in);
            desigion = userInput.next();
        }while (desigion.equals("y")  );

    }

    public void proccess(){

        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<Item> notPacked = new ArrayList<Item>();
        ArrayList<Bin> binsArray = new ArrayList<Bin>();

        // Вводим параметры контенера и коробок
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter box capacity(example 10:5:3):");
        String capacityBox = userInput.next();

        String[] result = capacityBox.split(":");

        binWidth = Integer.parseInt( result[0] );
        binHeight = Integer.parseInt( result[1] );
        binDepth = Integer.parseInt( result[2] );

        System.out.println("Enter items capacity(example 10:5:2;5:3:1;)");
        capacityBox = userInput.next();

        result = capacityBox.split(";");
        String[] itemParams;
        for (int x = 0; x < result.length; x++  ){
            itemParams = result[x].split(":");
            itemList.add(new Item( Integer.parseInt(itemParams[0] ),
                                   Integer.parseInt( itemParams[1]),
                                   Integer.parseInt( itemParams[2])
                                  )
                        );
        }
        // Вводим параметры контенера  и коробок

       // System.out.println( userName );


        // itemList.add(new Item( 10, 5, 2 ));
        // itemList.add(new Item( 5, 3, 1 ));
        /*
        itemList.add(new Item( 40, 40, 40 ));
        itemList.add(new Item( 30, 30, 30 ));
        itemList.add(new Item( 30, 30, 30 ));
        itemList.add(new Item( 30, 30, 30 ));
        itemList.add(new Item( 10, 10, 10 ));
        itemList.add(new Item( 10, 10, 10 ));
        itemList.add(new Item( 10, 10, 10 ));
        */
        //itemList.add(new Item( 10, 5, 3 ));


        notPacked.addAll( itemList );
        /*
        itemList.get(1).pivot = new Position(0,1,0);
        itemList.get(2).pivot = new Position(0,11,0);
        th.isSidesCross( itemList.get(1), itemList.get(2) );
        */
        do{
            System.out.println("Cycle a rotation");
            ArrayList<Item> toPack = new ArrayList<Item>();
            toPack.addAll( notPacked );
            notPacked.clear();

            Position pivot = new Position( 0, 0, 0 );
            Bin currentBin = new Bin( binWidth, binHeight, binDepth );
            toPack.get(0).pivot = pivot;

            int rotation = 0;
            if( isItemFit( pivot, toPack.get(0), currentBin ) ){
                packItem(pivot, toPack.get(0), currentBin);
            }else{
                do{
                    System.out.println("Cycle of size rotation");
                    rotation++;
                    toPack.get(0).rotationType = rotation;
                }while ( !isItemFit( pivot, toPack.get(0), currentBin ) && (rotation < 5 )  );
            }

            for( int i=1; i <  toPack.size(); i++ )
            {
                System.out.println("Cycle b");
                Item currentItem = toPack.get(i);
                boolean fitted=false;
                for(int p = 0; p < 3; p++) {
                    System.out.println("Cycle c p = " + Integer.toString(p));
                    int k = 0;
                    while (k < currentBin.items.size() && !fitted) {

                        System.out.println("Cycle d");

                        Item binItem = currentBin.items.get(k);
                        Position currentPivot = choozePivot(binItem, p);

                        currentItem.pivot = currentPivot;
                        if (isItemFit(currentPivot, currentItem, currentBin)) {
                            packItem(currentPivot, currentItem, currentBin);
                            fitted = true;
                        }else{

                            rotation = 0;

                            do{
                                System.out.println("Cycle of size rotation");
                                rotation++;
                                currentItem.rotationType = rotation;
                            }while ( !isItemFit( currentPivot, currentItem, currentBin ) && (rotation < 5 )  );

                            if( isItemFit( currentPivot, currentItem, currentBin ) ){
                                packItem(currentPivot, currentItem, currentBin);
                                fitted = true;
                            }else{
                                currentItem.rotationType = 0;
                            }
                        }
                        k++;
                    }
                }
                if( !fitted ){
                    System.out.println( "add not packed item");
                    notPacked.add( currentItem );
                }
            }


            binsArray.add(currentBin);
        }
        while ( notPacked.size() > 0 );
        if( binsArray.size() > 0 ){
            for (int i = 0;i < binsArray.size();i++){
                System.out.println( " Box items  -  " + binsArray.get(i).toString() );
            }
        }
    }

    public boolean isSidesCross( Item item1, Item item2 ){
        Item rotationItem1;

        rotationItem1 = returnRotateAnalog(item1);
        //rotationItem2 = returnRotateAnalog(item2);
        //int x1 = item1.pivot.x;
        int x2 = rotationItem1.pivot.x + rotationItem1.width;

        int x3 = item2.pivot.x;
        //int x4 = item2.pivot.x + item2.width;
        /*
        if( (x3 <= x2) ){
            System.out.println("Side x is crossing");
        }else{
            System.out.println("x not crossing");
        }
        */
        //int y1 = item1.pivot.y;
        int y2 = rotationItem1.pivot.y + rotationItem1.height;

        int y3 = item2.pivot.y;
        //int y4 = item2.pivot.y + item2.height;
        /*
        if( y3 <= y2 ){
            System.out.println("Side y is crossing");
        }else{
            System.out.println("y not crossing");
        }
        */
        //int z1 = item1.pivot.z;
        int z2 = rotationItem1.pivot.z + rotationItem1.depth;

        int z3 = item2.pivot.z;
        //int z4 = item2.pivot.z + item2.depth;
        /*
        if( z3 <= z2 ){
            System.out.println("Side z is crossing");
        }else{
            System.out.println("z not crossing");
        }
        */
        if( (x3 < x2) && ( (y3 < y2) ) && (z3 < z2) ){
            return  true;
        }
        return false;
    }

    public Item returnRotateAnalog( Item item ){
        Item rotationItem;
        Position rotationPointItem;

        switch (item.rotationType){
            case 0:
                rotationItem = new Item(item.width,item.height,item.depth);
                rotationPointItem = new Position( item.pivot.x, item.pivot.y, item.pivot.z );
                break;
            case 1:
                rotationItem = new Item(item.height, item.width, item.depth);
                rotationPointItem = new Position( item.pivot.y, item.pivot.x, item.pivot.z );
                break;
            case 2:
                rotationItem = new Item(item.depth, item.height, item.width);
                rotationPointItem = new Position( item.pivot.z, item.pivot.y, item.pivot.x );
                break;
            case 3:
                rotationItem = new Item(item.height,item.depth, item.width);
                rotationPointItem = new Position( item.pivot.y, item.pivot.z, item.pivot.x );
                break;
            case 4:
                rotationItem = new Item(item.width,item.depth, item.height);
                rotationPointItem = new Position( item.pivot.x, item.pivot.z, item.pivot.y );
                break;
            case 5:
                rotationItem = new Item(item.depth, item.width,item.height);
                rotationPointItem = new Position( item.pivot.z, item.pivot.x, item.pivot.y );
                break;
            default:
                rotationItem = new Item(item.width,item.height,item.depth);
                rotationPointItem = new Position( item.pivot.x, item.pivot.y, item.pivot.z );
        }
        rotationItem.pivot = rotationPointItem;
        return rotationItem;
    }

    public Position choozePivot( Item item , int p ){
         System.out.println("Choozing pivot ...");
         Item rotationItem = returnRotateAnalog(item);

         switch (p){
             case 0:
                 return new Position( rotationItem.pivot.x + rotationItem.width, rotationItem.pivot.y, rotationItem.pivot.z );
             case 1:
                 return new Position( rotationItem.pivot.x, rotationItem.pivot.y , rotationItem.pivot.z + rotationItem.depth );
             case 2:
                 return new Position( rotationItem.pivot.x, rotationItem.pivot.y + rotationItem.height, rotationItem.pivot.z );
         }
        return null;
    }
    public void packItem( Position pivot, Item item, Bin bin ){
        item.pivot = pivot;
        bin.items.add(item);
        System.out.println("packing item" + item.toString() + " to pivot" + pivot.toString());
    }

    public boolean isItemFit( Position pivot, Item item, Bin currentBin){

        boolean crossFail = false;

        Item rotationItem = returnRotateAnalog(item);


        System.out.println("Comparing items pivot = " + pivot.toString()
                + " item = " + rotationItem.toString());


        for(int i = 0; i < currentBin.items.size(); i++){
            if( isSidesCross( currentBin.items.get(i), item ) ){
                crossFail = true;
                break;
            }
        }
        rotationItem.pivot = null;


        if( ((binWidth - pivot.x) >= rotationItem.width) && ((binHeight - pivot.y) >= rotationItem.height)
                    && ((binDepth - pivot.z) >= rotationItem.depth) && (!crossFail) ){
            System.out.println(" Item is fit ");
            return true;
        }


        return false;
    }
}
