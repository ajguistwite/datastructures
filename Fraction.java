public class Fraction {
	private int numerator;
	private int denominator;

	//Constructor: creates fraction in simplist form
	public Fraction(int a, int b) {
		numerator = a;
		denominator = b;
		simplify();
	}
	
	//simplifies fraction
	void simplify(){
		int gcd = gcd(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
		}

	public static void main(String[] args) {
		//implememnted on mimir
	}

	//finds greatest common denominator
	public int gcd (int a, int b) {
		if (a % b ==0) { // base case
			return b;
		}
		else {
			return gcd(b, a % b);
		
		} 
	} 
	
	//adds a fraction to another
	public Fraction add(Fraction f){
		int newNumerator = ((f.numerator*denominator) +
	            (numerator*f.denominator));
	    int newDenominator = denominator*f.denominator;
	    Fraction x = new Fraction (newNumerator, newDenominator);
	    return x;
		}
	
	//subtracts a fraction from another
	public Fraction subtract(Fraction f){
		int newNumerator = ((numerator*f.denominator) -
	            (f.numerator*denominator));
	    int newDenominator = denominator*f.denominator;
	    Fraction x = new Fraction (newNumerator, newDenominator);
	    return x;
	}
	
	//multiplies a fraction to another
	public Fraction multiply(Fraction f){
		int newNumerator = numerator*f.numerator;
	    int newDenominator = denominator*f.denominator;
	    Fraction x = new Fraction (newNumerator, newDenominator);
	    return x;
	}
	
	//divides a fraction from another
	public Fraction divide(Fraction f){
		int newNumerator = numerator*f.denominator;
	    int newDenominator = denominator*f.numerator;
	    Fraction x = new Fraction (newNumerator, newDenominator);
	    return x;
	}
	
	//returns numerator
	public int getNumerator(){
		return numerator;
	}
	
	//returns denominator
	public int getDenominator(){
		return denominator;
	}
	
	//prints fraction properly
	public String toString(){
		return (numerator + "/" + denominator);
	}
		
	
	
 }