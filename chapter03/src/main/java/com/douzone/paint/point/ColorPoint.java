package com.douzone.paint.point;

public class ColorPoint extends Point {
	public ColorPoint() {
		//기본 생성자가 없고, 매개변수를 가지는 생성자만 정의했기때문에 super()를 명시적으로 호출하고 매개변수를 전달하면 문제없음
		//super(10,10);
	}
	String color;
	
	public ColorPoint(int x, int y, String color) {
		super(x,y);
		//setX(x);
		//setX(x);
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		
		System.out.println("점(x=" + getX() +", "+ "y=" + super.getY() +", color="+ color + ")을 그렸습니다");
		
	}
	

}
