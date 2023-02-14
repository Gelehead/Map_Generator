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
}
