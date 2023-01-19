package backjun;

class Casting {
    public static void main(String args[]) {
        Car car = new FireEngine();
        Car car2 = new TestEngine();
        ((FireEngine)car).water();
        FireEngine fe = new FireEngine();
        FireEngine fe2 = null;
        fe.water();
        car = fe; // car =(Car)fe;에서 형변환이 생략된것이며 다형성입니다.
        fe2 = (FireEngine)car; //자손타입 ← 조상타입
        fe2.water();
        car.stop();
        car2.stop();
        
        Car[] car3 = new Car[3];
        car3[0] = new TestEngine();
        car3[0].stop();
        car3[1] = new FireEngine();
        //car3[0].water
        car3[1].stop();
    }
}
class Car {
    String color;
    int door;
    void drive() {// 운전
        System.out.println("drive");
    }
    void stop() {// 멈추는 기능    
        System.out.println("stop");    
}
}
class FireEngine extends Car { // 소방차
    void water() {// 물을 뿌리는 기능
        System.out.println("water");
    }
    @Override
    void stop() {
    	System.out.println("자식(FIREECGINE) 멈춤");
    }
}
class TestEngine extends Car { // 소방차
    @Override
    void stop() {
    	System.out.println("자식(TEST) 멈춤");
    }
}