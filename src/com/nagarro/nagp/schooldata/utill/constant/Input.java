package com.nagarro.nagp.schooldata.utill.constant;

import java.util.Scanner;

public class Input {
	
	private Scanner instance;
	private static Input singleton = null;

	private Input() {
		instance = new Scanner(System.in);
	}

	public static Input getInstance() {
		if (singleton == null) {
			singleton = new Input();
		}
		return singleton;
	}

	public int takeMenuInput(int length) {
		try {
			int temp = Integer.parseInt(instance.nextLine());
			if (temp > length || temp < 1) {
				throw new Exception("Invalid Choice");
			}
			return temp;
		} catch (Exception ex) {
			System.out.println("please enter an valid menu option from 1 to " + length);
			return takeMenuInput(length);
		}
	}

	public String takeStringInput() {
		return instance.nextLine();
	}

	public int takeIntegerInput() {
		try {
			return Integer.parseInt(instance.nextLine());
		} catch (Exception e) {
			System.out.println("Enter an integer value");
			return takeIntegerInput();
		}

	}

	public float takeFloatInput() {
		try {
			return Float.parseFloat(instance.nextLine());
		} catch (Exception e) {
			System.out.println("Enter an float value");
			return takeFloatInput();
		}
	}

}
