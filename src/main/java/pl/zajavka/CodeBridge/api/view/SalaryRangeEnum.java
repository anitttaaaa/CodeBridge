package pl.zajavka.CodeBridge.api.view;

public enum SalaryRangeEnum {

    RANGE_0_3000("0zł - 3000zł"),
    RANGE_3000_5000("3000zł - 5000zł"),
    RANGE_5000_8000("5000zł - 8000zł"),
    RANGE_8000_12000("8000zł - 12000zł"),
    RANGE_12000_15000("12000zł - 15000zł"),
    RANGE_15000_18000("15000zł - 18000zł"),
    RANGE_18000_25000("18000zł - 25000zł"),
    RANGE_25000_X("25000zł + ");


    private final String range;

    SalaryRangeEnum(String range) {
        this.range = range;
    }

    public String getRange() {
        return range;
    }

}
