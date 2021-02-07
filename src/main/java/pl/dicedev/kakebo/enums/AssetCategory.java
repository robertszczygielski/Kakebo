package pl.dicedev.kakebo.enums;

public enum AssetCategory {

    SALARY("reliable"),
    BONUS("extra"),
    LOAN_RETURNED("extra"),
    RENT("reliable"),
    OTHER("do not know");

    private final String typeOfIncome;

    AssetCategory(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
    }

}
