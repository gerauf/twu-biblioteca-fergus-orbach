package com.twu.biblioteca;

/**
 * Created by fergyo on 23/12/2016.
 */
public abstract class Library_Item {
    private boolean checkedIn = true;

    @Override
    public abstract String toString();

    abstract String getName();

    void checkoutItem() {
        checkedIn = false;
    }

    void returnItem() {
        checkedIn = true;
    }

    boolean isCheckedIn() {
        return checkedIn;
    }
}
