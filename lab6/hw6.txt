import java.util.HashMap;
import java.util.Map;

public class Banks<Bank, Currency> {
    private Map<Bank, Map<Currency, Double>> bankData;

    public Banks(Map<Bank, Map<Currency, Double>> data) {
        bankData = data;
    }

    public void findLargestChange() {
        Map.Entry<Bank, Currency> maxIncrease = null;
        Map.Entry<Bank, Currency> maxDecrease = null;
        Double maxIncreaseValue = Double.MIN_VALUE;
        Double maxDecreaseValue = Double.MAX_VALUE;
        Map<Currency, Double> firstBankData = new HashMap<>();
        for (Map.Entry<Bank, Map<Currency, Double>> bankEntry : bankData.entrySet()) {
            Bank bank = bankEntry.getKey();
            Map<Currency, Double> currencyRates = bankEntry.getValue();
            if (firstBankData.isEmpty()) {
                firstBankData = currencyRates;
                continue;
            }
            for (Map.Entry<Currency, Double> currencyEntry : currencyRates.entrySet()) {
                Currency currency = currencyEntry.getKey();
                Double rate = currencyEntry.getValue();
                Double firstBankRate = firstBankData.get(currency);
                Double change = rate - firstBankRate;
                if (change > maxIncreaseValue) {
                    maxIncreaseValue = change;
                    maxIncrease = Map.entry(bank, currency);
                }
                if (change < maxDecreaseValue) {
                    maxDecreaseValue = change;
                    maxDecrease = Map.entry(bank, currency);
                }
            }
        }
        System.out.println("Өссөн валют : ");
        System.out.println("Банк : " + maxIncrease.getKey());
        System.out.println("Валют : " + maxIncrease.getValue());
        System.out.println("Өөрчлөлт : " + maxIncreaseValue);

        System.out.println("\nБуурсан валют : ");
        System.out.println("Банк : " + maxDecrease.getKey());
        System.out.println("Валют : " + maxDecrease.getValue());
        System.out.println("Өөрчлөлт : " + maxDecreaseValue);
    }

    public static void main(String[] args) {
        String[] currencies = {
                "Доллар", "Евро", "Иен",
                "Фунт", "Рубль", "Юань", "Вон"
        };

        Map<String, Map<String, Double>> bankData = new HashMap<>();
        bankData.put("TDB", new HashMap<>());
        bankData.put("Golomt Bank", new HashMap<>());
        bankData.put("Khan Bank", new HashMap<>());
        bankData.put("State Bank", new HashMap<>());
        bankData.put("Bogd Bank", new HashMap<>());

        bankData.get("TDB").put("Доллар", 0.0);
        bankData.get("TDB").put("Евро", 4.0);
        bankData.get("TDB").put("Иен", -0.09);
        bankData.get("TDB").put("Фунт", 4.0);
        bankData.get("TDB").put("Рубль", 0.0);
        bankData.get("TDB").put("Юань", -0.5);
        bankData.get("TDB").put("Вон", 0.0);

        bankData.get("Golomt Bank").put("Доллар", 0.0);
        bankData.get("Golomt Bank").put("Евро", 6.0);
        bankData.get("Golomt Bank").put("Иен", 0.08);
        bankData.get("Golomt Bank").put("Фунт", 5.0);
        bankData.get("Golomt Bank").put("Рубль", 0.0);
        bankData.get("Golomt Bank").put("Юань", -0.5);
        bankData.get("Golomt Bank").put("Вон", -0.003);

        bankData.get("Khan Bank").put("Доллар", 0.0);
        bankData.get("Khan Bank").put("Евро", 0.0);
        bankData.get("Khan Bank").put("Иен", 0.0);
        bankData.get("Khan Bank").put("Фунт", 0.0);
        bankData.get("Khan Bank").put("Рубль", 0.0);
        bankData.get("Khan Bank").put("Юань", 0.0);
        bankData.get("Khan Bank").put("Вон", 0.0);

        bankData.get("State Bank").put("Доллар", 0.0);
        bankData.get("State Bank").put("Евро", 5.0);
        bankData.get("State Bank").put("Иен", -0.08);
        bankData.get("State Bank").put("Фунт", 6.0);
        bankData.get("State Bank").put("Рубль", -1.0);
        bankData.get("State Bank").put("Юань", 0.0);
        bankData.get("State Bank").put("Вон", -0.02);

        bankData.get("Bogd Bank").put("Доллар", -1.0);
        bankData.get("Bogd Bank").put("Евро", 7.0);
        bankData.get("Bogd Bank").put("Иен", 0.0);
        bankData.get("Bogd Bank").put("Фунт", 9.1);
        bankData.get("Bogd Bank").put("Рубль", 0.0);
        bankData.get("Bogd Bank").put("Юань", -0.1);
        bankData.get("Bogd Bank").put("Вон", 0.0);

        Banks<String, String> analysis = new Banks<>(bankData);
        analysis.findLargestChange();
    }
}
