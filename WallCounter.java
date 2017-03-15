import java.util.Scanner;

public class WallCounter {

	private static Scanner in = null;
	private static int rowSize;
	private static int colSize;
	private static String map = "0123456789ABCDEF";
	private static String stringLine;
	private static int nWalls;
	private static int sWalls;
	private static int eWalls;
	private static int wWalls;
	private static String binaryString;

	public WallCounter() {

	}

	public static void main(String[] args) {
		System.out.println("MAZE: ");

		in = new Scanner(System.in);
		rowSize = in.nextInt();
		colSize = in.nextInt();
		int toInt[];
		toInt = new int[rowSize];

		in.nextLine();

		for (int i = 0; i < rowSize; i++) {
			stringLine = in.nextLine();
			for (int j = 0; j < colSize; j++) {
				char cr = stringLine.charAt(j);
				System.out.println(map.indexOf(cr));

				int x = (int) map.indexOf(cr);
				toInt[i] = x;

				String pad = String.format("%0" + 4 + 'd', 0);
				String s = Integer.toBinaryString(x);
				binaryString = pad.substring(s.length()) + s;

				char wNum = binaryString.charAt(0);
				if (wNum == '1')
					wWalls++;

				char eNum = binaryString.charAt(1);
				if (eNum == '1')
					eWalls++;

				char sNum = binaryString.charAt(2);
				if (sNum == '1')
					sWalls++;

				char nNum = binaryString.charAt(3);
				if (nNum == '1')
					nWalls++;

			}

		}

		System.out.println("NORTH: " + nWalls);
		System.out.println("SOUTH: " + sWalls);
		System.out.println("EAST: " + eWalls);
		System.out.println("WEST: " + wWalls);

	}
}
