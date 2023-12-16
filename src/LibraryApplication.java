import java.util.TreeSet;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class LibraryApplication {
    TreeSet<Borrower> borrowerCollection = new TreeSet<Borrower>();
    LinkedList<Loan> loanCollection = new LinkedList<Loan>();
    HashSet<Book> bookCollection = new HashSet<Book>();
    
    public static void main(String[] args) {
        LibraryApplication app = new LibraryApplication();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Welcome to the library!");
            System.out.println("1. Register one borrower");
            System.out.println("2. Register one book");
            System.out.println("3. Display books for loan");
            System.out.println("4. Display books on loan");
            System.out.println("5. Borrow one book");
            System.out.println("6. Return one book");
            System.out.println("7. Exit");

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter borrower name");
                    String name = scanner.next();
                    if (app.registerOneBorrower(name)) {
                        System.out.println("Borrower registered");
                    } else {
                        System.out.println("Borrower not registered");
                    }
                    break;
                case 2:
                    System.out.println("Enter book title");
                    String title = scanner.next();
                    System.out.println("Enter book author");
                    String author = scanner.next();
                    System.out.println("Enter book ISBN");
                    long ISBN = scanner.nextLong();
                    if (app.registerOneBook(title, author, ISBN)) {
                        System.out.println("Book registered");
                    } else {
                        System.out.println("Book not registered");
                    }
                    break;
                case 3:
                    app.displayBooksForLoan();
                    break;
                case 4:
                    app.displayBooksOnLoan();
                    break;
                case 5:
                    System.out.println("Enter book ISBN");
                    ISBN = scanner.nextLong();
                    System.out.println("Enter borrower name");
                    name = scanner.next();
                    if (app.borrowOneBook(ISBN, name)) {
                        System.out.println("Book borrowed");
                    } else {
                        System.out.println("Book not borrowed");
                    }
                    break;
                case 6:
                    System.out.println("Enter book ISBN");
                    ISBN = scanner.nextLong();
                    if (app.returnOneBook(ISBN)) {
                        System.out.println("Book returned");
                    } else {
                        System.out.println("Book not returned");
                    }
                    break;
                case 7:
                    System.out.println("Bye");
                    scanner.close();
                    return;
            }
        }
    }

    public boolean registerOneBorrower(String name) {
        boolean isAdded = borrowerCollection.add(new Borrower(name));
        return isAdded;
    }

    public boolean registerOneBook(String title, String author, long ISBN) {
        boolean isAdded = bookCollection.add(new Book(title, author, ISBN));
        return isAdded;
    }

    public void displayBooksForLoan() {
        if (bookCollection.isEmpty()) {
            System.out.println("책이 한 권도 없습니다.");
            return;
        }
        
        Iterator<Book> iter = bookCollection.iterator();
        boolean atLeastOneOrMoreForLoan = false;
        while (iter.hasNext()) {
            Book book = iter.next();
            boolean isBookForLoan = book.checkBookForLoan();
            if (isBookForLoan) {
                book.display();
                atLeastOneOrMoreForLoan = true;
            }
        }
        
        if (!atLeastOneOrMoreForLoan) {
            System.out.println("모든 책이 대출 중입니다.");
        }
        return;
    }

    public void displayBooksOnLoan() {
        if (bookCollection.isEmpty()) {
            System.out.println("책이 한 권도 없습니다.");
            return;
        }
        
        Iterator<Book> iter = bookCollection.iterator();
        boolean atLeastOneMoreOnLoan = false;
        while (iter.hasNext()) {
            Book book = iter.next();
            boolean isBookOnLoan = book.checkBookOnLoan();
            if (isBookOnLoan) {
                book.display();
                atLeastOneMoreOnLoan = true;
            }
        }

        if (!atLeastOneMoreOnLoan) {
            System.out.println("대출 중인 책이 없습니다.");
        }
        return;
    }

    public boolean borrowOneBook(long ISBN, String name) {
        Iterator<Book> bookIter = bookCollection.iterator();
        Book book = null;
        while (bookIter.hasNext()) {
            book = bookIter.next();
            if (ISBN == book.getISBN()) {
                break;
            }
        }
        if (book == null) {
            System.out.println("책을 찾을 수 없습니다.");
            return false;
        }

        Iterator<Borrower> borrowerIter = borrowerCollection.iterator();
        Borrower borrower = null;
        while (borrowerIter.hasNext()) {
            borrower = borrowerIter.next();
            if (name.equals(borrower.getName())) {
                break;
            }
        }
        if (borrower == null) {
            System.out.println("이용자를 찾을 수 없습니다.");
            return false;
        }

        book.display();
        borrower.display();

        if (!book.checkAvailableForLoan()) {
            System.out.println("해당 책은 대출 불가능합니다.");
            return false;
        }

        if (!borrower.checkAvailableForLoan()) {
            System.out.println("해당 이용자는 대출 불가능합니다.");
            return false;
        }

        Loan loan = new Loan(book, borrower);
        book.linkLoan(loan);
        borrower.linkLoan(loan);
        loan.saveLoanDate();
        loan.saveReturnDueDate();

        boolean isAdded = loanCollection.add(loan);

        loan.display();
        return isAdded;
    }

    public boolean returnOneBook(long ISBN) {
        Iterator<Book> bookIter = bookCollection.iterator();
        Book book = null;
        while (bookIter.hasNext()) {
            book = bookIter.next();
            if (ISBN == book.getISBN()) {
                break;
            }
        }
        if (book == null) {
            System.out.println("책을 찾을 수 없습니다.");
            return false;
        }
        
        Loan loan = book.findLoan();
        loan.unlink();
        loan.saveReturnedDate();

        loan.display();
        return true;
    }
}