package pl.dicedev.kakebo.enums;

public enum AssetCategory {

    salary("reliable"),
    bonus("extra"),
    loan_returned("extra"),
    rent("reliable");

    private final String typeOfIncome;

    AssetCategory(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
    }

}
