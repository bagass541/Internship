public class Main {
    public static void main(String[] args) {
        String a = "1111";
        String b = "1111";

        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        int indA = a.length() - 1;
        int indB = b.length() - 1;
        int remains = 0;
        StringBuilder res = new StringBuilder();

        while (indA >= 0 || indB >= 0 || remains != 0) {
            int sum = 0;
            if (indA >= 0) {
                sum += a.charAt(indA) - '0';
                indA--;
            }

            if (indB >= 0) {
                sum += b.charAt(indB) - '0';
                indB--;
            }

            sum += remains;
            res.append(sum % 2);
            remains = sum / 2;
        }

        return res.reverse().toString();
    }
}