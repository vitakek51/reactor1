public class reactor {


    public static final float MAX_TEMPERATURE = 100;
    public static final float MIN_TEMPERATURE = -100;
    public static final float DEFAULT_FREQUENCY = 1000;
    public static final float SPED_UP_FREQUENCY = 200;

    private float currentTemperature;
    private float targetTemperature;

    private float incFrequency;
    private float decFrequency;

    private boolean isWorking;

    public reactor(float initialTemperature) {
        currentTemperature = initialTemperature;
        targetTemperature = initialTemperature;
        incFrequency = DEFAULT_FREQUENCY;
        decFrequency = DEFAULT_FREQUENCY;
        isWorking = true;
    }

    public void setTargetTemperature(float targetTemperature) {
        this.targetTemperature = targetTemperature;

        if (targetTemperature >= currentTemperature) {
            incFrequency = SPED_UP_FREQUENCY;
            decFrequency = DEFAULT_FREQUENCY;
        } else {
            decFrequency = SPED_UP_FREQUENCY;
            incFrequency = DEFAULT_FREQUENCY;
        }
    }

    public synchronized void inc() {
        currentTemperature++;
        System.out.println(currentTemperature + "increased");

        if (currentTemperature >= targetTemperature) {
            incFrequency = DEFAULT_FREQUENCY;
        }

        if (currentTemperature >= MAX_TEMPERATURE || currentTemperature <= MIN_TEMPERATURE) {
            isWorking = false;
            System.out.println("Генератор вышел из строя");
        }
    }

    public synchronized void dec() {
        currentTemperature--;
        System.out.println(currentTemperature + "decreased");

        if (currentTemperature <= targetTemperature) {
            decFrequency = DEFAULT_FREQUENCY;
        }

        if (currentTemperature >= MAX_TEMPERATURE || currentTemperature <= MIN_TEMPERATURE) {
            isWorking = false;
            System.out.println("Генератор вышел из строя");
        }
    }

    boolean isWorking() {
        return isWorking;
    }

    public float getIncFrequency() {
        return incFrequency;
    }

    public float getDecFrequency() {
        return decFrequency;
    }
}