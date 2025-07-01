package com.app.invoice.exception;

public class DashboardException extends Throwable {
    public DashboardException(String couldNotLoadDashboardData, Exception e) {
        super(couldNotLoadDashboardData, e);
    }
}
