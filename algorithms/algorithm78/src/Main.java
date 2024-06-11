public class Main {
    public static void main(String[] args) {
        System.out.println(myPow(2.0d, 5));
    }

    public static double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        double pow = 1;
        while (n != 0) {
            if (n % 2 != 0) {
                pow *= x;
            }

            x *= x;
            n = n / 2;
        }

        return pow;
    }
}