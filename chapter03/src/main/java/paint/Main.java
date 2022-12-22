package paint;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		Point point1 = new Point(10,10);
		//point.setX(10);
		//point.setY(10);
		//point.show();
		
		//drawPoint(point1);
		draw(point1);
		point1.show(false);
		//point.show(true);	
		//point.disapear();
		
		Point point2 = new ColorPoint(20,20,"red");
		
		point2.setX(20);
		point2.setY(20);
		((ColorPoint)point2).setColor("빨간색");
		//drawPoint(point2);
		draw(point2);

		Rect rect = new Rect();
		draw(rect);
		//drawShape(rect);
		//drawRect(rect);
		
		Triangle triangle = new Triangle();
		draw(triangle);
		//drawShape(triangle);
		//drawTriangle(triangle);
		
		Circle circle = new Circle();
		draw(circle);
		//drawShape(circle);
	}
	
	public static void draw(Drawable drawable) {
		drawable.draw();
	}
	
//	public static void drawPoint(Point point) {
//		point.show();
//	}
//	
//	public static void drawShape(Shape shape) {
//		shape.draw();
//	}
//	public static void drawColorPoint(ColorPoint colorPoint) {
//		//colorPoint.show(false);
//		colorPoint.show();
//	}
//	public static void drawRect(Rect rect) {
//		rect.draw();
//	}
//	public static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
//	public static void drawCircle(Circle circle) {
//		circle.draw();
//	}

	
}
