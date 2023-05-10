import java.util.*;

public class ReverseDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        Stack<Integer> stack = new Stack<>();
        while (number > 0) {
            int digit = number % 10;
            stack.push(digit);
            number /= 10;
        }

        int reversedNumber = 0;
        int multiplier = 1;
        while (!stack.isEmpty()) {
            reversedNumber += stack.pop() * multiplier;
            multiplier *= 10;
        }

        System.out.println("Reversed number: " + reversedNumber);
    }
}
