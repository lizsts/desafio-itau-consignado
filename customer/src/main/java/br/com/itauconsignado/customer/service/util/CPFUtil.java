package br.com.itauconsignado.customer.service.util;

import org.springframework.stereotype.Component;

@Component
public class CPFUtil {
    public boolean validaMascaraCPF(String cpfString) {
        boolean cpfMascara = cpfString.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");

        cpfString = cpfString.replaceAll("[^0-9]", "");

        if (cpfString.length() != 11) {
            return false;
        }

        return cpfMascara;
    }
}
