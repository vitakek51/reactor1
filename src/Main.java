
import java.util.Scanner;
public class Main extends Thread {
    float operatorValue;
    reactor nuclear;
    Scanner input;

    public Main() {
        input = new Scanner(System.in);
        operatorValue = input.nextFloat();

        nuclear = new reactor(operatorValue);

        new Thread(inc()).start();
        new Thread(dec()).start();
        new Thread(operatorInput()).start();

    }

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }

    private Runnable inc() {
        return () -> {
            while (nuclear.isWorking()) {
                nuclear.inc();
                try {
                    Thread.sleep((long) nuclear.getIncFrequency());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    private Runnable dec() {
        return () -> {
            while (nuclear.isWorking()) {
                nuclear.dec();
                try {
                    Thread.sleep((long) nuclear.getDecFrequency());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    private Runnable operatorInput() {
        return () -> {
            while (nuclear.isWorking()) {
                if (input.hasNext()) {
                    operatorValue = input.nextFloat();
                    nuclear.setTargetTemperature(operatorValue);
                }
            }
        };
    }

}