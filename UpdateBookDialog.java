public class UpdateBookDialog extends BookPropertyDialog {

    public UpdateBookDialog(BookListWindow owner) {
        super(owner, "Update Book");
        titleTextField.setEnabled(false);
        saveButton.setText("UPDATE");
    }

    public void showBook(Book book) {
        titleTextField.setText(book.getTitle()); //sets text from getTitle through the Book class
        authorsTextField.setText(book.getAuthors()); //sets author from get Authors through the Book class
        pagesTextField.setText(book.getPages()+"");
        if(book.getCategory() == Book.BookCategory.Programming) //category set up for Programming
            categoryComboBox.setSelectedIndex(0);
        else if(book.getCategory() == Book.BookCategory.Database) //category for Database
            categoryComboBox.setSelectedIndex(1);
        else if(book.getCategory() == Book.BookCategory.Design) //category for Design
            categoryComboBox.setSelectedIndex(2);
    }

    @Override
    protected void doSave(Book book) {
        super.bookCollection.update(book); //updates new entries in the library
    }
}
