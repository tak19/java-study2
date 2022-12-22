package prob6;

public class RectTriangle extends Shape {
	private double width;
	private double height;

	
	public RectTriangle(double width, double height) {
		//super();
		this.width = width;
		this.height = height;
	}

	@Override
	double getArea() {
		return width * height * 0.5;
		
	}

	@Override
	double getPerimeter() {
		double d = this.width + this.height + (Math.sqrt(height*height + width*width) );
		return d;
	}

}
