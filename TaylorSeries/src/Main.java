import java.lang.Math;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Maciej Adamczyk Taylor series of sin(x). Comparing my approximation
 *         with the default approach in Math library.
 *
 */
public class Main {
	public static double taylor(double x, String inputFormat) {
		if (inputFormat.equalsIgnoreCase("degrees")) { // converting to radians
			x *= Math.PI / 180d;
		}
		double res = 0;
		// 1st approach
//		if (x > Math.PI) { // to be only in the interval <-PI, PI>
//			while (x > Math.PI)
//				x -= (2.0 * Math.PI);
//		} else if (x < (-1) * Math.PI) { // to be only in the interval <-PI, PI>
//			while (x < (-1) * Math.PI)
//				x += (2.0 * Math.PI);
//		}
//		// we can diminish the value of x even more if we want. It will make the
//		// numbers in Taylor series
//		// smaller. Explanation: x=1/4 x^2=1/16, x=3/4 x^2=9/16.
//		if (x <= (Math.PI / 2)) // to be only in the interval (-PI/2, PI/2)
//			x = -Math.PI - x;
//		else if (x >= Math.PI / 2) // to be only in the interval (-PI/2, PI/2)
//			x = Math.PI - x;

		x %= (2 * Math.PI); // 2nd approach, using the "%" symbol, then in (-2PI, 2PI) interval

		if (x > Math.PI) {
			x = x - 2 * Math.PI;
			if (x < -Math.PI / 2)
				x = -Math.PI - x;
		} else if (x < -Math.PI) {
			x = x + 2 * Math.PI;
			if (x > Math.PI / 2)
				x = Math.PI - x;
		}

		// x%=(2*Math.PI); // 3rd approach, then in (-PI, PI) interval, but we would
		// have to handle the change of symbol

		res = x;
		double div = 3;
		int iterations = 5; // the bigger number, the bigger accuracy.
		// However, it is not always true. It means more multiplications. Because the
		// value of
		// Pi is not ideal, the result loses some accuracy while being multiplied a lot
		// of times.
		// Even the default library of sin(x) is not 100% percent accurate. It is
		// impossible, because
		// the value of PI in general can't be "ideally" calculated.
		double formula = x;
		String sign = "-"; // for the Taylor formula
		while (iterations > 0) { // Taylor formula, the smarter way of calculating
			formula = formula * (Math.pow(x, 2) / ((div - 1) * div));
			switch (sign) {
			case "-":
				res -= formula;
				sign = "+";
				break;
			case "+":
				res += formula;
				sign = "-";
				break;
			}
			div += 2;
			iterations--;
		}
		return res;
	}

	public static void main(String[] args) { // Taylor series of sin(x)
		Locale.setDefault(new Locale("en"));

		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to the sin(x) converter! Please enter a format of the sin(x) function.\n"
				+ "Write \"degrees\", \"radians\": ");
		String inputFormat;
		while (true) {
			inputFormat = scan.next();
			if (inputFormat.equals("degrees") || inputFormat.equals("radians"))
				break;
			System.out.println("Wrong format. Write \"degrees\", \"radians\": ");
		}

		String inputValue;
		while (true) {
			Pattern pattern = Pattern.compile("(^-?\\d+$)|(^-?\\d+\\.+\\d+$)");
			System.out.println("Enter a value of " + inputFormat + ": ");
			inputValue = scan.next();
			Matcher matcher = pattern.matcher(inputValue);
			if (matcher.matches()) {
				break;
			}
			System.out.println("A wrong value of " + inputFormat + ". Please try again (type in a number).");
		}
		scan.close();

		double value = Double.parseDouble(inputValue);
		System.out.println("My approximation:    " + taylor(value, inputFormat));
		// test
		if (inputFormat.equalsIgnoreCase("degrees"))
			System.out.println("Their approximation: " + Math.sin(value * Math.PI / 180d));
		else if (inputFormat.equalsIgnoreCase("radians")) {
			System.out.println("Their approximation: " + Math.sin(value));
		}

		// Conclusion (after this exercise): It is very difficult to find the perfect
		// approximation of every angle.
	}

}