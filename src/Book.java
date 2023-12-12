public class Book {
    String title;
    String author;
    long ISBN;
    Loan loan;

    public Book(String title, String author, long ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.author = author;
        }
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        if (ISBN > 0) {
            this.ISBN = ISBN;
        }
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
        if (loan != null) {
            System.out.println("Borrower: " + loan.getBorrower().getName());
        }
    }

    public boolean checkBookForLoan() {
        return loan == null;
    }

    public boolean checkBookOnLoan() {
        return loan != null;
    }

    // 대출 가능한 이용자인지 확인하다.
    public boolean checkAvailableForLoan() {
        return true;
    }

    public void linkLoan(Loan loan) {
        setLoan(loan);
    }

    public Loan findLoan() {
        return getLoan();
    }

    public void unlinkLoan() {
        setLoan(null);
    }
}
