package prob5;

public class MyStack02 {
	//현재 스택이 들어가 있는 정보를 알기위해 static 변수를 활용할 거임!
	static int index = 0;
	
	private static Object[] buffer; //요따가 스택 구현함
	private int num;
		
	public MyStack02(int num) {
		buffer = new String[num];
		//System.out.println(buffer.length);
	}

	public void push(String s) {
		//현재 인덱스에 값 넣고, 인덱스 값 1 증가
		if(buffer.length <= index) {
			resize();
		}
		
		buffer[index++] = s;
		//System.out.println(buffer[index-1]);
		
	}

	public boolean isEmpty() {
		//인덱스 값이 0보다 작거나 같으면 스택이 메모리가 빈 것
		if(index <= 0) {
			return true;
		}else {
			return false;
		}
		

	}

	public Object pop() {
		//현재 index값에 pop 실행 시 +1 값이 들어가 있기때문에 전위로 -1 감소시킴
		  if(index <= 0) {
			  throw new MyStackException("stack is empty");
		  }
		return buffer[--index];
	}
	
	public void resize() {
		Object[] temp = new Object[buffer.length *2];
		for(int i = 0 ; i < index ; i ++) {
			temp[i] = buffer[i];
		}
		buffer = temp;
		
	}

	
}