package id.co.ptap.circleaf.core.enums;

public enum ResponseCode implements EnumParam {
    _100("100", "Success"),
    _101("101", "Need Verify"),
    _102("102", "Need Approve"),
    _103("103", "On Process"),
    _104("104", "Transaction Failed  [failed_reason]"),
    _105("105", "Transaction Succeed"),
    _106("106", "Transaction Error, contact Administrator."),
    _201("201", "Invalid ClientID / Nomor Referensi tidak valid"),
    _202("202", "Invalid Authority Setting on (checker / approver)"),
    _203("203", "Invalid Authority Not Found"),
    _301("301", "Invalid Debet Acc Number"),
    _302("302", "Debet and credit acc must be different"),
    _303("303", "Invalid Debet Acc Name"),
    _401("401", "Invalid Credit Acc Number"),
    _402("402", "Transfer from Loan to Loan is not allowed"),
    _403("404", "Invalid Credit Acc Address"),
    _404("404", "Invalid Credit Acc Bank"),
    _406("406", "Invalid Credit Acc Name"),
    _601("601", "Invalid Transfer Amount"),
    _602("602", "Transactions exceeding the corporate limit"),
    _603("603", "Transactions exceeding the daily limit"),
    _701("701", "Max Length for Remark is 500"),
    _702("702", "Transaksi tidak ditemukan"),
    _801("801", "Backdated Transaction is not Allowed"),
    _802("802", "Transaction is not allowed in the given value date"),
    _803("803", "Transaction in that period is not allowed"),
    _999("999", "Got Any Exception ");

    public String value;
    public String text;

    private ResponseCode(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String toString() {
        return this.value.toString();
    }

    public String getText() {
        return this.text;
    }

    public String getValue() {
        return this.value;
    }
}
