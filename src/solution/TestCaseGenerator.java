package solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestCaseGenerator {
	private static final String INPUT_FILE = "src/input-test-cases/input.txt"; // Path to the input file
	private static final String OUTPUT_FILE = "src/output-test-cases/output.txt"; // Path to the output file
	private static final Random RANDOM = new Random(); // Single instance of Random for generating random numbers

	public static void main(String[] args) {
		int t = generateRandomNumber(1, 10); // Generate a random number of test cases between 1 and 10

		try (BufferedWriter inputWriter = new BufferedWriter(new FileWriter(INPUT_FILE, true));
		     BufferedWriter outputWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {

			inputWriter.write(t + "\n"); // Write the number of test cases to the input file

			for (int i = 0; i < t; i++) {
				generateTestCase(inputWriter, outputWriter); // Generate and write each test case
			}
			System.out.println("Test cases generated successfully!"); // Print a success message
		} catch (IOException e) {
			System.out.println(e.getMessage()); // Handle any IO exceptions
		}
	}

	/**
	 * Generates a single test case, writes the input to the input file, and writes the output to the output file.
	 */
	private static void generateTestCase(BufferedWriter inputWriter, BufferedWriter outputWriter) throws IOException {
		int n = generateRandomNumber(2, 100); // Generate a random string length between 2 and 100
		int x = generateRandomNumber(1, 10);  // Generate a random x value between 1 and 10
		int y = generateRandomNumber(1, 10);  // Generate a random y value between 1 and 10

		inputWriter.write(n + " " + x + " " + y + "\n"); // Write the test case parameters to the input file

		String s = generateBinaryString(n); // Generate a random binary string of length n
		inputWriter.write(s + "\n"); // Write the binary string to the input file

		int cost = binaryStringCost(s, x, y); // Calculate the cost for the binary string
		outputWriter.write(cost + "\n"); // Write the calculated cost to the output file
	}

	/**
	 * Generates a random binary string of a given length.
	 */
	private static String generateBinaryString(int n) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int random = generateRandomNumber(0, 2); // Generate a random binary digit (0 or 1)
			s.append(random);
		}
		return s.toString();
	}

	/**
	 * Generates a random number between the specified lower bound (inclusive) and upper bound (exclusive).
	 */
	private static int generateRandomNumber(int lowerBound, int upperBound) {
		return lowerBound + RANDOM.nextInt(upperBound - lowerBound); // Generate a random number within the bounds
	}

	/**
	 * Calculates the cost for converting the binary string based on the given x and y values.
	 */
	private static int binaryStringCost(String s, int x, int y) {
		int c_1 = 0, c_0 = 0;
		for (char c : s.toCharArray()) {
			if (c == '1') {
				c_1++; // Count the number of 1s
			} else {
				c_0++; // Count the number of 0s
			}
		}
		return (c_1 == 0 || c_0 == 0) ? 0 : Math.min(x, y); // Return 0 if the string has only 1s or only 0s, otherwise return the minimum of x and y
	}
}
