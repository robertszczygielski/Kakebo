package pl.dicedev.kakebo.enums;

public enum ExpensesCategory {

    for_live(0),
    education(1),
    culture(1),
    entertainment(2),
    other(3);

    private final Integer importance;

    ExpensesCategory(Integer importance) {
        this.importance = importance;
    }

    public Integer getImportance() {
        return this.importance;
    }

}
