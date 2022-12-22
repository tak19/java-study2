package com.douzone.paint.text;

import com.douzone.paint_i.Drawable;

public class GraphicTest implements Drawable {
	private String text;
	
	public GraphicTest(String text) {
		this.text = text;
	}
	
	@Override
	public void draw() {
		System.out.println("텍스트 '"+ text + "'를 그렸습니다");
	}

}
