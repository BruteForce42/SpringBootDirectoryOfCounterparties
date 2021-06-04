package com.zatsepin.util;

import com.zatsepin.TestConfig;
import com.zatsepin.entity.Counterparty;
import com.zatsepin.service.CounterpartyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(TestConfig.class)
class CounterpartyValidatorTest {

    @Autowired
    CounterpartyService counterpartyService;
    @Autowired
    CounterpartyValidator counterpartyValidator;

    String[] CORRECT_INN_10 = {"5504081248", "5503248039", "5401381810"};
    String[] CORRECT_INN_12 = {"027504801118", "032600487935", "781905043556"};
    String[] INCORRECT_INN = {"5504081?48", "X3260048795", "781905", "781905043556123"};
    String[] CORRECT_KPP = {"550401001", "550701001"};
    String[] INCORRECT_KPP = {"5#0401001", "5133320401001", "54013"};
    String[] CORRECT_ACCOUNT_NUMBER = {"40702810310430001226", "40702810445000093711"};
    String[] INCORRECT_ACCOUNT_NUMBER = {"40702810445000093711", "40702810310430001226"};
    String[] CORRECT_BIC_FOR_ACCOUNT_NUMBER = {"044525411", "045209673"};
    String[] INCORRECT_BIC = {"5#0401001", "5133320401001", "54013", "554525823"};

    @Test
    void isInvalidNameReturnTrueBecauseAddingDuplicateName() {
        Counterparty counterparty = Counterparty.builder()
                .name("Водоканал")
                .inn("5504097128")
                .kpp("550401001")
                .accountNumber("40702810045370100747")
                .bic("045209673")
                .comment("Водоснабжение и канализация")
                .build();
        counterpartyService.save(counterparty);
        assertTrue(counterpartyValidator.isInvalidName("Водоканал"));
    }

    @Test
    void isInvalidNameReturnFalseBecauseAddingUniqueName() {
        Counterparty counterparty = Counterparty.builder()
                .name("Магнит")
                .inn("5401381810")
                .kpp("550401001")
                .accountNumber("40702810338320002761")
                .bic("046577964")
                .comment("Вывоз бытового мусора")
                .build();
        counterpartyService.save(counterparty);
        assertFalse(counterpartyValidator.isInvalidName("Газпром"));
    }

    @Test
    void isInvalidInnReturnFalseBecauseInnHasLength10Or12AndCorrect() {
        assertFalse(counterpartyValidator.isInvalidInn(CORRECT_INN_10[0]));
        assertFalse(counterpartyValidator.isInvalidInn(CORRECT_INN_10[1]));
        assertFalse(counterpartyValidator.isInvalidInn(CORRECT_INN_10[2]));
        assertFalse(counterpartyValidator.isInvalidInn(CORRECT_INN_12[0]));
        assertFalse(counterpartyValidator.isInvalidInn(CORRECT_INN_12[1]));
        assertFalse(counterpartyValidator.isInvalidInn(CORRECT_INN_12[2]));
    }

    @Test
    void isInvalidInnReturnTrueBecauseInnIncorrect() {
        assertTrue(counterpartyValidator.isInvalidInn(INCORRECT_INN[0]));
        assertTrue(counterpartyValidator.isInvalidInn(INCORRECT_INN[1]));
        assertTrue(counterpartyValidator.isInvalidInn(INCORRECT_INN[2]));
        assertTrue(counterpartyValidator.isInvalidInn(INCORRECT_INN[3]));
    }

    @Test
    void isInvalidKppReturnTrueBecauseKppHasLengthNot9AndContainsNotNumbers() {
        assertTrue(counterpartyValidator.isInvalidKpp(INCORRECT_KPP[0]));
        assertTrue(counterpartyValidator.isInvalidKpp(INCORRECT_KPP[1]));
        assertTrue(counterpartyValidator.isInvalidKpp(INCORRECT_KPP[2]));
    }

    @Test
    void isInvalidKppReturnFalseBecauseKppHasLength9AndContainsNumbers() {
        assertFalse(counterpartyValidator.isInvalidKpp(CORRECT_KPP[0]));
        assertFalse(counterpartyValidator.isInvalidKpp(CORRECT_KPP[1]));
    }

    @Test
    void isInvalidAccountNumberReturnTrueBecauseAccountNumberIncorrectForBic() {
        assertTrue(counterpartyValidator.isInvalidAccountNumber(INCORRECT_ACCOUNT_NUMBER[0], CORRECT_BIC_FOR_ACCOUNT_NUMBER[0]));
        assertTrue(counterpartyValidator.isInvalidAccountNumber(INCORRECT_ACCOUNT_NUMBER[0], CORRECT_BIC_FOR_ACCOUNT_NUMBER[0]));
    }

    @Test
    void isInvalidAccountNumberReturnFalseBecauseAccountNumberCorrectForBic() {
        assertFalse(counterpartyValidator.isInvalidAccountNumber(CORRECT_ACCOUNT_NUMBER[0], CORRECT_BIC_FOR_ACCOUNT_NUMBER[0]));
        assertFalse(counterpartyValidator.isInvalidAccountNumber(CORRECT_ACCOUNT_NUMBER[1], CORRECT_BIC_FOR_ACCOUNT_NUMBER[1]));
    }

    @Test
    void isInvalidBicReturnTrueBecauseBicHasLengthNot9AndContainsNotNumbersAndIncorrect() {
        assertTrue(counterpartyValidator.isInvalidBic(INCORRECT_BIC[0]));
        assertTrue(counterpartyValidator.isInvalidBic(INCORRECT_BIC[1]));
        assertTrue(counterpartyValidator.isInvalidBic(INCORRECT_BIC[2]));
        assertTrue(counterpartyValidator.isInvalidBic(INCORRECT_BIC[3]));
    }

    @Test
    void isInvalidBicReturnFalseBecauseBicHasLength9AndContainsNumbersAndCorrect() {
        assertFalse(counterpartyValidator.isInvalidBic(CORRECT_BIC_FOR_ACCOUNT_NUMBER[0]));
        assertFalse(counterpartyValidator.isInvalidBic(CORRECT_BIC_FOR_ACCOUNT_NUMBER[1]));
    }
}