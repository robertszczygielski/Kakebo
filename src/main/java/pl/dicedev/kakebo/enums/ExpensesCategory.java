package pl.dicedev.kakebo.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ExpensesCategory {

    FOR_LIVE(0),
    EDUCATION(1),
    CULTURE(1),
    ENTERTAINMENT(2),
    OTHER(3);

    private final Integer importance;

    ExpensesCategory(Integer importance) {
        this.importance = importance;
    }

    public Integer getImportance() {
        return this.importance;
    }

}
