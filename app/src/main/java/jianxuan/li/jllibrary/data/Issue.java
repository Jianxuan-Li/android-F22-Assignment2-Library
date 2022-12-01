package jianxuan.li.jllibrary.data;

public class Issue {
    /*
    static final String[] COLS_ISSUE = {
            "issueId INTEGER PRIMARY KEY AUTOINCREMENT",
            "bookId INTEGER",
            "customerName Text",
            "customerEmail TEXT",
            "qtyIssued INTEGER",
            "dateofIssue TEXT"};
     */
    private int issueId;
    private int bookId;
    private String customerName;
    private String customerEmail;
    private int qtyIssued;
    private String dateofIssue;

    // getters and setters
    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getQtyIssued() {
        return qtyIssued;
    }

    public void setQtyIssued(int qtyIssued) {
        this.qtyIssued = qtyIssued;
    }

    public String getDateOfIssue() {
        return dateofIssue;
    }

    public void setDateOfIssue(String dateofIssue) {
        this.dateofIssue = dateofIssue;
    }
}
