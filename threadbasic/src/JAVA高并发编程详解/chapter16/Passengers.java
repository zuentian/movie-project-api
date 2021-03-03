package JAVA高并发编程详解.chapter16;

public class Passengers extends Thread {
    //机场安检类
    private final FlightSecurity flightSecurity;
    //旅客的身份证
    private final String idCard;
    //旅客的登机牌
    private final String boardingPass;


    public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
        this.flightSecurity = flightSecurity;
        this.idCard = idCard;
        this.boardingPass = boardingPass;
    }

    @Override
    public void run() {
        while (true){
            flightSecurity.pass(boardingPass,idCard);
        }
    }

    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity,"A12345","AF33321").start();
        new Passengers(flightSecurity,"B12345","BF33321").start();
        new Passengers(flightSecurity,"C12345","CF33321").start();
        new Passengers(flightSecurity,"D12345","DF33321").start();
        new Passengers(flightSecurity,"E12345","EF33321").start();
        new Passengers(flightSecurity,"F12345","FF33321").start();

    }
}
