package mandelbrot;

public class ComplexNumber {

    private double a;
    private double b;

    public ComplexNumber(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public ComplexNumber multiply(ComplexNumber complexNumber) {
        return new ComplexNumber(this.a * complexNumber.a - (this.b * complexNumber.b),
                this.a * complexNumber.b + this.b * complexNumber.a);
    }

    public ComplexNumber add(ComplexNumber complexNumber) {
        return new ComplexNumber(this.a + complexNumber.a, this.b + complexNumber.b);
    }

    public double abs() {
        return Math.sqrt((Math.pow(this.a, 2) + Math.pow(this.b, 2)));
    }

    public ComplexNumber conjugate() {
        return new ComplexNumber(this.a, -this.b);
    }

    public ComplexNumber addByNumber(double num) {
        return new ComplexNumber(this.a + num, this.b);
    }

    public ComplexNumber multiplyByNumber(double num) {
        return new ComplexNumber(this.a * num, this.b * num);
    }

    public ComplexNumber divide(ComplexNumber complexNumber) {
        ComplexNumber num = this.multiply(complexNumber.conjugate());
        double denom = Math.pow(complexNumber.abs(), 2);
        return new ComplexNumber(num.a / denom, num.b / denom);
    }

    @Override
    public String toString() {
        return this.a + (this.b < 0 ? " - " : " + ") + this.b + "i";
    }

}
