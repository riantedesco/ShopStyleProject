package com.compass.mspayment.util.constants;

public enum StatusOrderPublisherOption {

    PAYMENT_SUCCESSFUL("Pagamento efetuado com sucesso"),
    PAYMENT_NOT_FOUND("Pagamento não encontrado"),
    PAYMENT_INACTIVE("Pagamento inativo"),
    PAYMENT_NOT_INSTALLMENT("Pagamento não possui parcelamento"),
    PAYMENT_AMOUNT_NOT_AVAILABLE("Pagamento com parcelas acima do limite em todos os tipos de parcelamento");

    public final String status;

    private StatusOrderPublisherOption(String status) {
        this.status = status;
    }
}