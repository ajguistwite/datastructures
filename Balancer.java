//Andrew Guistwite
//Balancer

import java.util.*;

public class Balancer {
	private static Scanner in = null;
	private static int lines;
	private static String stuff;
	private static boolean Balanced;

	public static void main(String[] args) {
		char current;
		Stack<Character> stack = new Stack<Character>();
		in = new Scanner(System.in);

		lines = in.nextInt();
		in.nextLine();

		for (int i = 0; i < lines; i++) {
			stuff = in.nextLine();
			for (int j = 0; j < stuff.length(); j++) {
				// process input.charAt(j)
				current = stuff.charAt(j);
				if (current == '{' || current == '(' || current == '[') {
					stack.push(current);
				}

				if (current == '}' || current == ')' || current == ']') {
					if (stack.isEmpty())
						Balanced = false;

					char last = stack.peek();
					if ((current == '}' && last == '{') || (current == ')' && last == '(')
							|| (current == ']' && last == '['))
						stack.pop();
					else
						Balanced = false;
				}

			}

			if (stack.isEmpty())
				Balanced = true;
			else
				Balanced = false;

		}
		{

			if (Balanced) {
				System.out.print("GOOD");
			} else {
				System.out.print("BAD");
			}
		}
	}

	public static boolean isBalanced() {
		if (stuff.isEmpty())
			return true;

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < stuff.length(); i++) {
			char current = stuff.charAt(i);
			if (current == '{' || current == '(' || current == '[') {
				stack.push(current);
			}

			if (current == '}' || current == ')' || current == ']') {
				if (stack.isEmpty())
					return false;

				char last = stack.peek();
				if ((current == '}' && last == '{') || (current == ')' && last == '(')
						|| (current == ']' && last == '['))
					stack.pop();
				else
					return false;
			}

		}

		return stack.isEmpty();
	}

}