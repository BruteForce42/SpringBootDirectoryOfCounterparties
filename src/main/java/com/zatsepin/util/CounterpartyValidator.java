package com.zatsepin.util;

import com.zatsepin.service.CounterpartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CounterpartyValidator {

    private final CounterpartyService counterpartyService;

    private static final int[] COEFFICIENTS_FOR_ACCOUNT_NUMBER = new int[]{7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1};
    private static final int[] COEFFICIENTS_FOR_INN_10 = new int[]{2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
    private static final int[] COEFFICIENTS_FOR_INN_11 = new int[]{7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
    private static final int[] COEFFICIENTS_FOR_INN_12 = new int[]{3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};

    @Autowired
    public CounterpartyValidator(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    public boolean isValidName(String name) {
        return name.length() > 2
                && name.length() < 21
                && counterpartyService.findByName(name) == null;
    }

    boolean isValidInn(String inn) {
        if (inn.matches(".*\\D.*")) {
            return false;
        }
        if (inn.length() == 10) {
            int checkSum = 0;
            for (int i = 0; i < 9; i++) {
                checkSum += Character.getNumericValue(inn.charAt(i)) * COEFFICIENTS_FOR_INN_10[i];
            }
            int checkNumber = checkSum % 11 == 10 ? 0 : checkSum % 11;
            return checkNumber == Character.getNumericValue(inn.charAt(9));
        }
        if (inn.length() == 12) {
            int checkSum1 = 0;
            for (int i = 0; i < 10; i++) {
                checkSum1 += Character.getNumericValue(inn.charAt(i)) * COEFFICIENTS_FOR_INN_11[i];
            }
            int checkNumber1 = checkSum1 % 11 == 10 ? 0 : checkSum1 % 11;
            int checkSum2 = 0;
            for (int i = 0; i < 11; i++) {
                checkSum2 += Character.getNumericValue(inn.charAt(i)) * COEFFICIENTS_FOR_INN_12[i];
            }
            int checkNumber2 = checkSum2 % 11 == 10 ? 0 : checkSum2 % 11;
            return checkNumber1 == Character.getNumericValue(inn.charAt(10)) && checkNumber2 == Character.getNumericValue(inn.charAt(11));
        }
        return false;
    }

    public boolean isValidKpp(String kpp) {
        return !kpp.matches(".*\\D.*")
                && kpp.length() == 9;
    }

    public boolean isValidAccountNumber(String accountNumber, String bic) {
        if (accountNumber.matches(".*\\D.*") || accountNumber.length() != 20) {
            return false;
        }
        if (Character.getNumericValue(bic.charAt(6)) == 0 && Character.getNumericValue(bic.charAt(7)) == 0) {
            String accountNumberSequence = "0" + bic.substring(4, 6) + accountNumber;
            int checkSum = 0;
            for (int i = 0; i < 23; i++) {
                checkSum += Character.getNumericValue(accountNumberSequence.charAt(i)) * COEFFICIENTS_FOR_ACCOUNT_NUMBER[i];
            }
            return checkSum % 10 == 0;
        } else {
            String accountNumberSequence = bic.substring(6) + accountNumber;
            int checkSum = 0;
            for (int i = 0; i < 23; i++) {
                checkSum += Character.getNumericValue(accountNumberSequence.charAt(i)) * COEFFICIENTS_FOR_ACCOUNT_NUMBER[i];
            }
            return checkSum % 10 == 0;
        }
    }

    public boolean isValidBic(String bic) {
        return !bic.matches(".*\\D.*")
                && bic.length() == 9
                && Character.getNumericValue(bic.charAt(0)) == 0
                && Character.getNumericValue(bic.charAt(1)) == 4;
    }
}
