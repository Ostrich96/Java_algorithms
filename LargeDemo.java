public class LargeDemo{
    public static int LargeDemo(int x, int y){
        if(x>y){
            return x;
        }
        return y;
    }
    public static void main(String[] args){
        System.out.println(LargeDemo(-5,10));
    }
}