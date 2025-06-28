package com.app.invoice.utils;

import com.app.invoice.repo.InvoiceRepository;
import org.springframework.stereotype.Component;

@Component
public class InvoiceNumberGenerator {

    private final InvoiceRepository invoiceRepository;

    public InvoiceNumberGenerator(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public String generate() {
        String lastInvoiceNumber = invoiceRepository.findLastInvoiceNumber();
        int nextNumber = 1;

        if (lastInvoiceNumber != null && lastInvoiceNumber.startsWith("INV-")) {
            try {
                String numericPart = lastInvoiceNumber.substring(4); // e.g., "0005"
                nextNumber = Integer.parseInt(numericPart) + 1;
            } catch (NumberFormatException ignored) {
                // fallback to 1
            }
        }

        return "INV-" + String.format("%04d", nextNumber);
    }


}
