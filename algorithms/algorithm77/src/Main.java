public class Main {
    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }

    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        int left = 1;
        int right = Integer.MAX_VALUE - 1;

        while (true) {
            int mid = (left + right) / 2;

            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                }

                left = mid + 1;
            }
        }
    }
}