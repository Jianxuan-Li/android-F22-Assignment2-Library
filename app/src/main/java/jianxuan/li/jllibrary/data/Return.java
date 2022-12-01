package jianxuan.li.jllibrary.data;

public class Return {
    /*
    "returnId INTEGER PRIMARY KEY AUTOINCREMENT",
            "issueId INTEGER",
            "bookId INTEGER",
            "qtyReturned INTEGER",
            "dateofReturn TEXT"
     */
    private int returnId;
    private int issueId;
    private int bookId;
    private int qtyReturned;
    private String dateOfReturn;

    // getters and setters
    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

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

    public int getQtyReturned() {
        return qtyReturned;
    }

    public void setQtyReturned(int qtyReturned) {
        this.qtyReturned = qtyReturned;
    }

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

}
