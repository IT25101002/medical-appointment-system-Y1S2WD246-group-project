package Question_03;

public class Main {
    public static void main(String[] args) {

        Calculator calc = new Calculator();

        // Expression 1: (3 * 4 + 5 * 7)^2
        int part1 = calc.multiply(3,4);
        int part2 = calc.multiply(5,7);
        int sum1 = calc.add(part1, part2);
        int result1 = calc.square(sum1);

        System.out.println("Result 1 = " + result1);

        // Expression 2: (4 + 7)^2 + (8 + 3)^2
        int a = calc.square(calc.add(4,7));
        int b = calc.square(calc.add(8,3));
        int result2 = calc.add(a,b);

        System.out.println("Result 2 = " + result2);
    }
}