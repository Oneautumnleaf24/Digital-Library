import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public abstract class BookPropertyDialog extends JDialog implements ActionListener {

    protected JLabel titleLabel;
    protected JTextField titleTextField;
    protected JLabel authorsLabel;
    protected JTextField authorsTextField;
    protected JLabel pagesLabel;
    protected JTextField pagesTextField;
    protected JLabel categoryLabel;
    protected JComboBox<String> categoryComboBox;
    protected JButton saveButton;
    protected JButton cancelButton;
    protected BookStorage bookCollection;
    protected BookListWindow bookListWindow;

    public BookPropertyDialog(BookListWindow owner, String title) {
        super(owner);
        this.bookListWindow = owner;
        this.setTitle(title);
        this.bookCollection = owner.getBookStorage();
        initComponents();
    }


    private void initComponents() {
        titleLabel = new JLabel("    Title:");
        titleTextField = new JTextField();
        authorsLabel = new JLabel("    Authors:");
        authorsTextField = new JTextField();
        pagesLabel = new JLabel("    Pages:");
        pagesTextField = new JTextField();
        categoryLabel = new JLabel("    Category:");
        categoryComboBox = new JComboBox<>();
        saveButton = new JButton("SAVE");
        cancelButton = new JButton("CANCEL");

        Container contentPane = this.getContentPane(); //setting up the container
        contentPane.setLayout(new GridLayout(5, 2)); //Gridlayout for the container

        contentPane.add(titleLabel); //adding the title label to show in the JPanel
        contentPane.add(titleTextField); //adding the text field

        contentPane.add(authorsLabel); //creating/adding the authors label in the final code
        contentPane.add(authorsTextField); //creating the authors label

        contentPane.add(pagesLabel); //creating/adding "Pages"
        contentPane.add(pagesTextField); //Creating/Adding "Category"

        contentPane.add(categoryLabel);
        categoryComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                Book.BookCategory.Programming.name(), // attaching/borrowing to the book enum class for Programming
                Book.BookCategory.Database.name(), // attaching/borrowing to the book enum class for Database
                Book.BookCategory.Design.name() //// attaching/borrowing to the book enum class for Design
        }));

        contentPane.add(categoryComboBox); //adds a ComboBox
        contentPane.add(saveButton); //adds saveButtom
        contentPane.add(cancelButton); //adds cancelButton
        saveButton.addActionListener(this); //action listener for saveButton
        cancelButton.addActionListener(this); //action listener for cancelButton

        this.pack();
        this.setLocationRelativeTo(this.getOwner());
        this.setModal(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            // Action for the SAVE button
            // TODO Add your code here...
            // Handle the NumberFormatException caused by Integer.valueOf().
            try{
                Book book = new Book(
                        titleTextField.getText(), //retrieves from the titleTextField
                        authorsTextField.getText(), //retrieves from the authorsTextfield
                        Integer.valueOf(pagesTextField.getText()),
                        Book.BookCategory.valueOf(Objects.requireNonNull(categoryComboBox.getSelectedItem()).toString()));
                doSave(book);
                JOptionPane.showMessageDialog(this, "Save successful!");
                bookListWindow.resetToAll();
                this.dispose();
            }catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "wrong number format for the number of pages");
            } //catches and stops the NumberFormatException error
        } else if (e.getSource() == cancelButton) {
            // Action for the CANCEL button
            this.dispose();
        }
    }


    protected abstract void doSave(Book book);

}

