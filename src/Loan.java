import java.util.Date;

public class Loan {
    Book book;
    Borrower borrower;
    int loanDate;
    int returnDueDate;
    int returnedDate;

    public Loan(Book book, Borrower borrower) {
        this.book = book;
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if (book != null) {
            this.book = book;
        }
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        if (borrower != null) {
            this.borrower = borrower;
        }
    }

    public int getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(int loanDate) {
        if (loanDate > 0) {
            this.loanDate = loanDate;
        }
    }

    public int getReturnDueDate() {
        return returnDueDate;
    }

    public void setReturnedDueDate(int returnDueDate) {
        if (returnDueDate > 0) {
            this.returnDueDate = returnDueDate;
        }
    }

    public int getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(int returnedDate) {
        if (returnedDate > 0) {
            this.returnedDate = returnedDate;
        }
    }

    public void linkBook(Book book) {
        if (book != null) {
            book.setLoan(this);
        }
    }

    public void saveLoanDate() {
        Date now = new Date();
        loanDate = (int) now.getTime();
    }

    public void saveReturnDueDate() {
        returnDueDate = calculateReturnDueDate();
    }

    public int calculateReturnDueDate() {
        Date now = new Date();
        return (int) now.getTime() + 14; // 14 days
    }

    public void display() {
        System.out.println("Book: " + book.getTitle());
        System.out.println("Borrower: " + borrower.getName());
        System.out.println("Loan date: " + loanDate);
        System.out.println("Return due date: " + returnDueDate);
        System.out.println("Returned date: " + returnedDate);
    }

    public void unlink() {
        unlinkBook();
        unlinkBorrower();
    }

    public void unlinkBook() {
        book.unlinkLoan();
    }

    public void unlinkBorrower() {
        borrower.unlinkLoan();
    }

    public void saveReturnedDate() {
        Date now = new Date();
        returnedDate = (int) now.getTime();
    }
}
