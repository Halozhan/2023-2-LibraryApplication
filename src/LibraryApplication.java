import java.util.TreeSet;
import java.util.LinkedList;
import java.util.HashSet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Scanner;

public class LibraryApplication {
    TreeSet<Borrower> borrowerCollection = new TreeSet<Borrower>();
    LinkedList<Loan> loanCollection = new LinkedList<Loan>();
    HashSet<Book> bookCollection = new HashSet<Book>();
    
    public static void main(String[] args) {
        LibraryApplication app = new LibraryApplication();
        app.borrowerCollectionRead();
        app.loanCollectionRead();
        app.bookCollectionRead();

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
                    app.borrowerCollectionWrite();
                    app.loanCollectionWrite();
                    app.bookCollectionWrite();
                    return;
            }
        }
    }

    public void borrowerCollectionRead() {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("borrowerCollection.bin");
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
            Object object = ois.readObject();

            borrowerCollection = (TreeSet<Borrower>) object;
        } catch (Exception e) {
            System.out.println("borrowerCollection.bin을 읽는 도중 오류가 발생했습니다.");
        } finally {
            try {
                if (ois != null) ois.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            } catch (Exception e) {
                System.out.println("borrowerCollection.bin을 닫는 도중 오류가 발생했습니다.");
            }
        }
    }

    public void borrowerCollectionWrite() {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("borrowerCollection.bin");
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(borrowerCollection);
        } catch (Exception e) {
            System.out.println("borrowerCollection.bin을 쓰는 도중 오류가 발생했습니다.");
        } finally {
            try {
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            } catch (Exception e) {
                System.out.println("borrowerCollection.bin을 닫는 도중 오류가 발생했습니다.");
            }
        }
    }

    public void loanCollectionRead() {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("loanCollection.bin");
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
            Object object = ois.readObject();

            loanCollection = (LinkedList<Loan>) object;
        } catch (Exception e) {
            System.out.println("loanCollection.bin을 읽는 도중 오류가 발생했습니다.");
        } finally {
            try {
                if (ois != null) ois.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            } catch (Exception e) {
                System.out.println("loanCollection.bin을 닫는 도중 오류가 발생했습니다.");
            }
        }
    }

    public void loanCollectionWrite() {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("loanCollection.bin");
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(loanCollection);
        } catch (Exception e) {
            System.out.println("loanCollection.bin을 쓰는 도중 오류가 발생했습니다.");
        } finally {
            try {
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            } catch (Exception e) {
                System.out.println("loanCollection.bin을 닫는 도중 오류가 발생했습니다.");
            }
        }
    }

    public void bookCollectionRead() {
        FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("bookCollection.bin");
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			Object object = ois.readObject();

            bookCollection = (HashSet<Book>) object;
		} catch (Exception e) {
			System.out.println("bookCollection.bin을 읽는 도중 오류가 발생했습니다.");
		} finally {
			try {
				if (ois != null) ois.close();
				if (bis != null) bis.close();
				if (fis != null) fis.close();
			} catch (Exception e) {
				System.out.println("bookCollection.bin을 닫는 도중 오류가 발생했습니다.");
			}
		}
    }

    public void bookCollectionWrite() {
        FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("bookCollection.bin");
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			oos.writeObject(bookCollection);
		} catch (Exception e) {
			System.out.println("bookCollection.bin을 쓰는 도중 오류가 발생했습니다.");
		} finally {
			try {
				if (bos != null) bos.close();
				if (fos != null) fos.close();
			} catch (Exception e) {
				System.out.println("bookCollection.bin을 닫는 도중 오류가 발생했습니다.");
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
        return true;
    }

    public boolean returnOneBook(long ISBN) {
        return true;
    }
}