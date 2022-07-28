import javax.swing.AbstractListModel;

/**
 * A book array model used by {@link javax.swing.JList}.
 */
public class BookArrayModel extends AbstractListModel<String> {
    private Book[] bookArray;

    public BookArrayModel(Book[] bookArray) {
        this.bookArray = bookArray; //sets bookArray
    }

    public void setBookArray(Book[] bookArray) {
        this.bookArray = bookArray; //calls constructor bookArray
    }

    @Override
    public int getSize() {
        return bookArray.length; //bookArray.length
    }

    @Override
    public String getElementAt(int index) {
        return bookArray[index].getTitle(); //returns bookArray  from getTitle
    }
}
