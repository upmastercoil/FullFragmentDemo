package tech.android.tcmp13.fullfragmentdemo.equipments.model;

/**
 * Created by tcmp13-t on 12/21/2016.
 */
public class Equipment {

    private String description;
    private String manufacturer;

    public Equipment(String description, String manufacturer) {
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipment equipment = (Equipment) o;

        if (!description.equals(equipment.description)) return false;
        return manufacturer.equals(equipment.manufacturer);

    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + manufacturer.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return description + " by: " + manufacturer;
    }
}
