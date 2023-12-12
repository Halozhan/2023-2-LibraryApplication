public class Borrower implements Comparable<Borrower> {
    String name;
    Loan loan;

    public Borrower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { 
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void display() {
        System.out.println("Name: " + name);
        if (loan != null) {
            System.out.println("Book: " + loan.getBook().getTitle());
        }
    }

    // 이용자가 대출 가능한지 확인
    public boolean checkAvailableForLoan() {
        return true;
    }

    public void linkLoan(Loan loan) {
        setLoan(loan);
    }

    public Loan findLoan() {
        return loan;
    }

    public void unlinkLoan() {
        setLoan(null);
    }

    @Override
    public int compareTo(Borrower o) {
        return this.name.compareTo(o.getName());
    }
}
