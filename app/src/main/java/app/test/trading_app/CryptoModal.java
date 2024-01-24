package app.test.trading_app;

public class CryptoModal {

    private String id;
    private String name;
    private String symbol;
    private String price;
    private String performanceHour;
    private String performanceDay;
    private String performanceMonth;
    private String performanceYear;
    private  String img;

    public CryptoModal(
            String id,
            String name,
            String symbol,
            String price,
            String performanceHour,
            String performanceDay,
            String performanceMonth,
            String performanceYear,
            String img
    ) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.performanceHour = performanceHour;
        this.performanceDay = performanceDay;
        this.performanceMonth = performanceMonth;
        this.performanceYear = performanceYear;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public String getPerformanceHour() {
        return performanceHour;
    }

    public String getPerformanceDay() {
        return performanceDay;
    }

    public String getPerformanceMonth() {
        return performanceMonth;
    }

    public String getPerformanceYear() {
        return performanceYear;
}

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
