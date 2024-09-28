import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GasStation {
    public static void main(String[] args) {
        int totalSimulationTime = 4 * 60; // 4 hours in minutes
        int carServicingTime = 6; // Average time to refuel a vehicle in minutes
        int[] maintenanceTimes = {10, 15, 5, 13}; // Technical maintenance times for each device

        ExecutorService executor = Executors.newFixedThreadPool(4);

        GasStationDevice device1 = new GasStationDevice(1, carServicingTime, maintenanceTimes[0], totalSimulationTime);
        GasStationDevice device2 = new GasStationDevice(2, carServicingTime, maintenanceTimes[1], totalSimulationTime);
        GasStationDevice device3 = new GasStationDevice(3, carServicingTime, maintenanceTimes[2], totalSimulationTime);
        GasStationDevice device4 = new GasStationDevice(4, carServicingTime, maintenanceTimes[3], totalSimulationTime);

        executor.execute(device1);
        executor.execute(device2);
        executor.execute(device3);
        executor.execute(device4);

        try {
            Thread.sleep(totalSimulationTime * 60 * 1000);

            executor.shutdownNow();

            int totalCarsServiced = device1.getCarsServiced() + device2.getCarsServiced() +
                    device3.getCarsServiced() + device4.getCarsServiced();

            System.out.println("Total cars serviced in 4 hours: " + totalCarsServiced);
            System.out.println("Cars serviced by Device 1: " + device1.getCarsServiced());
            System.out.println("Cars serviced by Device 2: " + device2.getCarsServiced());
            System.out.println("Cars serviced by Device 3: " + device3.getCarsServiced());
            System.out.println("Cars serviced by Device 4: " + device4.getCarsServiced());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class GasStationDevice implements Runnable {
    private int deviceNumber;
    private int carServicingTime;
    private int maintenanceTime;
    private int totalSimulationTime;
    private int carsServiced;

    public GasStationDevice(int deviceNumber, int carServicingTime, int maintenanceTime, int totalSimulationTime) {
        this.deviceNumber = deviceNumber;
        this.carServicingTime = carServicingTime;
        this.maintenanceTime = maintenanceTime;
        this.totalSimulationTime = totalSimulationTime;
    }

    public int getCarsServiced() {
        return carsServiced;
    }

    @Override
    public void run() {
        while (totalSimulationTime > 0) {
            if (totalSimulationTime >= carServicingTime) {
                carsServiced++;
                totalSimulationTime -= carServicingTime;
            }

            if (totalSimulationTime >= maintenanceTime) {
                try {
                    Thread.sleep(maintenanceTime * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalSimulationTime -= maintenanceTime;
            }
        }
    }
}
