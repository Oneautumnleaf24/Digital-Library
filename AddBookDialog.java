/**
 * The dialog for adding a new book.
 */
public class AddBookDialog extends BookPropertyDialog {

    public AddBookDialog(BookListWindow owner) {
        super(owner, "Add Book");
    }

    @Override
    protected void doSave(Book book) {
        super.bookCollection.add(book); //connection to bookCollection
    }
}
