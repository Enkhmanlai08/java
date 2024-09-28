import java.util.*;
import java.time.LocalDate;

class IceCreamOrder<T> {
    private LocalDate date;
    private T amt;
    private int zarsantoo;

    public IceCreamOrder(LocalDate date, T amt, int zarsantoo) {
        this.date = date;
        this.amt = amt;
        this.zarsantoo = zarsantoo;
    }

    public LocalDate getDate() {
        return date;
    }

    public T getAmt() {
        return amt;
    }

    public int getZarsantoo() {
        return zarsantoo;
    }
}

public class IceCreamAnalysis<T> {
    public IceCreamAnalysis() {
    }
    //Тухайн өдрийн борлуулалтууд олох функц
    private Map<LocalDate, Integer> OdortZaragdsan(List<IceCreamOrder<T>> orders) {
        //харгалзаа ашиглан тухайн өдрийн борлуулалт олно.
        Map<LocalDate, Integer> totalSalesByDate = new HashMap<>();
        for (IceCreamOrder<T> order : orders) {
            LocalDate date = order.getDate();
            int sales = order.getZarsantoo();
            totalSalesByDate.put(date, totalSalesByDate.getOrDefault(date, 0) + sales);
        }
        return totalSalesByDate;
    }
    //Тухайн өдөртөө хамгийн их борлуулалт олох функц
    private LocalDate OdortMaxZaragdsan(Map<LocalDate, Integer> totalSalesByDate) {
        LocalDate highestSalesDate = null;
        int max = 0;
        for (Map.Entry<LocalDate, Integer> entry : totalSalesByDate.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                highestSalesDate = entry.getKey();
            }
        }
        return highestSalesDate;
    }
    //Нийт хамгийн их зарагдсан амт олох функц
    private T IhAmt(List<IceCreamOrder<T>> orders) {
        //харгалзаа ашиглан амт бүрийн борлуулалт олно.
        Map<T, Integer> flavorOrders = new HashMap<>();
        for (IceCreamOrder<T> order : orders) {
            T flavor = order.getAmt();
            flavorOrders.put(flavor, flavorOrders.getOrDefault(flavor, 0) + order.getZarsantoo());
        }
        int maxOrders = 0;
        T mostOrderedFlavor = null;
        for (Map.Entry<T, Integer> entry : flavorOrders.entrySet()) {
            if (entry.getValue() > maxOrders) {
                maxOrders = entry.getValue();
                mostOrderedFlavor = entry.getKey();
            }
        }
        return mostOrderedFlavor;
    }

    public static void main(String[] args) {
        List<IceCreamOrder<String>> orders = new ArrayList<>();
        //Жишээ өгөгдөл дээр шалгасан байдал.
        orders.add(new IceCreamOrder<>(LocalDate.of(2023, 10, 26), "Vanilla", 11));
        orders.add(new IceCreamOrder<>(LocalDate.of(2023, 10, 26), "Chocolate", 22));
        orders.add(new IceCreamOrder<>(LocalDate.of(2023, 10, 26), "Strawberry", 33));
        orders.add(new IceCreamOrder<>(LocalDate.of(2023, 10, 27), "Vanilla", 44));
        orders.add(new IceCreamOrder<>(LocalDate.of(2023, 10, 27), "Chocolate", 55));
        orders.add(new IceCreamOrder<>(LocalDate.of(2023, 10, 27), "Strawberry", 66));
        IceCreamAnalysis<String> analysis = new IceCreamAnalysis<>();
        Map<LocalDate, Integer> totalSalesByDate = analysis.OdortZaragdsan(orders);
        LocalDate highestSales = analysis.OdortMaxZaragdsan(totalSalesByDate);
        System.out.println("Хамгийн их борлуулалт хийсэн өдөр : " + highestSales);
        System.out.println("Нийт борлуулалт : " + totalSalesByDate.get(highestSales));
        String mostFlavor = analysis.IhAmt(orders);
        System.out.println("Хамгийн их захиалдаг амт : " + mostFlavor);
    }
}
