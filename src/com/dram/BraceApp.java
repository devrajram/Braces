package com.dram;

/**
 * This class provides an utility to check if a given string has balanced braces.
 * @author devrajram
 *
 */
public class BraceApp {

	static class CharStack {
		int top;
		int size;
		char[] chars;

		public CharStack(int size) {
			this.top = -1;
			this.size = size;
			this.chars = new char[size];

		}

		public void push(char c) {
			if (top > size) {
				System.err.println("The Stack is full. Something went wrong.");
				return;
			}
			chars[++top] = c;// add to array
		}

		public char pop() {
			if (top == -1) {
				System.err.println("The Stack is empty. Something went wrong.");
				return '\0';
			}
			return chars[top--]; // remove an item
		}

		public boolean isEmpty() {
			return top == -1;
		}

	}

	public static void main(String[] args) {

		String input = "ABCD";

		boolean balanced = checkBracesBalance(input);

		if (balanced) {
			System.out.println("Balanced.");
		} else {
			System.out.println("Not Balanced.");
		}
	}

	/**
	 * This is a utility method to check if a given string has balanced braces. If a string contains brace(s) and it has balanced opening and closing
	 * braces then the string is called balanced. Also, if input string does not have any brace then also it is called balanced. For example:
	 * {([])} - Balanced
	 * {()[]} - Balanced
	 * {{{([[]])}}} - Balanced
	 * ABCD - Balanced
	 * {[()} - Not Balanced
	 * {([]} - Not Balanced
	 * 
	 * @param input string 
	 * @return true if the string a balanced, false, otherwise
	 */
	public static boolean checkBracesBalance(String input) {
		//Before creating required resources, lets check if the input string has any braces.
		if(!bracesFound(input)) {
			return true;
		}
		
		CharStack stack = new CharStack(input.length());
		char[] chars = input.toCharArray();
		boolean balanced = true;
		for (char c : chars) {
			if (openingBrace(c)) {
				stack.push(c);
			} else if (closingBrace(c)) {
				char openingBrace = stack.pop();
				if (!pairBraces(openingBrace, c)) {
					balanced = false;
					break;
				}
			}
		}
		return balanced;
	}

	private static boolean bracesFound(String input) {
		if(input.contains("{")||input.contains("}")||input.contains("(")
				||input.contains(")")||input.contains("[")||input.contains("]")){
			return true;
		}
		return false;
	}

	private static boolean pairBraces(char openingBrace, char closingBrace) {
		switch (closingBrace) {
		case '}':
			return openingBrace == '{';
		case ')':
			return openingBrace == '(';
		case ']':
			return openingBrace == '[';

		default:
			return false;

		}
	}

	private static boolean openingBrace(char inputChar) {
		if (inputChar == '[' || inputChar == '{' || inputChar == '(')
			return true;
		return false;
	}

	private static boolean closingBrace(char inputChar) {
		if (inputChar == ']' || inputChar == '}' || inputChar == ')')
			return true;
		return false;
	}
}
