import java.util.HashSet;

public class Utils {

    public static int abs(int x){ return x >= 0 ? x : -x;}
    public static double abs(double x){ return x >= 0 ? x : -x;}
    public static float abs(float x){ return x >= 0 ? x : -x;}

    public static int pow(int num,int power){
        int result = num;
        if (power == 0){ return 1; }
        else if (power == 1) { return result; }
        else{
            for (int i = 0; i <= power ; i++) {
                result *= num;
            }
        }
        return result;
    }

    // found at https://www.javatpoint.com/java-program-to-find-smallest-number-in-an-array
    // probably inefficient bc O(n**2), will work for small arrays like the neighbours one
    public static int getSmallest(int[] a){  
        int temp;  
        for (int i = 0; i < a.length; i++){  
            for (int j = i + 1; j < a.length; j++){  
                if (a[i] > a[j]){  
                    temp = a[i];  
                    a[i] = a[j];  
                    a[j] = temp;  
                }  
            }  
        }  
    return a[0];  
    }
    public static double getSmallest(double[] a){  
        double temp;  
        for (int i = 0; i < a.length; i++){  
            for (int j = i + 1; j < a.length; j++){  
                if (a[i] > a[j]){  
                    temp = a[i];  
                    a[i] = a[j];  
                    a[j] = temp;  
                }  
            }  
        }  
    return a[0];  
    }

    public static HashSet<Tile> toHashset(Tile[] tileArray){
        HashSet<Tile> result = new HashSet<>(tileArray.length);
        for (int i = 0; i < tileArray.length; i++) {
            result.add(tileArray[i]);
        }
        return result;
    }

    public static boolean contains(Tile[] array, Tile searchedTile){
        for (Tile i : array){
            if ((i != null) && (i.equals(searchedTile))) {return true;}
        }
        return false;
    }

    public static Tile[] concatenate(Tile[] a, Tile[] b){
        if (b == null){return a;}
        Tile[] array = new Tile[a.length + b.length];
        for (int i = 0; i < array.length; i++) {
            if (i>=a.length){
                array[i] = b[i-a.length];
            }
            else{
                array[i] = a[i];
            }
        }
        return array;
    }

    public static Tile[] concatenate(Tile a, Tile[] b){
        if (b == null){return Utils.arrayify(a);}
        if (a == null){return b;}
        Tile[] array = new Tile[b.length + 1];
        array[0] = a;
        for (int i = 0; i < array.length; i++) {array[i+1] = b[i];}
        return array;
    }

    public static Tile[] arrayify(Tile a){
        Tile[] array = {a};
        return array;
    }

    public static double EuclDist(Tile start, Tile end){
        return Math.sqrt(Math.pow((start.getPosx() - end.getPosx()), 2) + Math.pow(start.getPosy() - end.getPosy(), 2));
    }

    public static int ManhDist(Tile start, Tile end){
        return ((Math.abs(start.getPosx() - end.getPosx())) + (Math.abs(start.getPosy() - end.getPosy())));
    }

}
