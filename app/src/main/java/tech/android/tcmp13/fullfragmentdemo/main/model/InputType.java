package tech.android.tcmp13.fullfragmentdemo.main.model;

/**
 * Created by tcmp13-t on 12/21/2016.
 * <p>
 * All the input types allowed in the add dialog
 */
public enum InputType {
    EMPLOYEE(0),
    EQUIPMENT(1);

    private int rawValue;

    InputType(int rawValue) {
        this.rawValue = rawValue;
    }

    public int getRawValue() {
        return rawValue;
    }

    public static InputType fromRawValue(int rawValue) {

        for (InputType inputType : values())
            if (inputType.getRawValue() == rawValue)
                return inputType;
        return null;
    }
}
