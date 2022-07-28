import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * BookListWindow
 */
public class BookListWindow extends JFrame implements ActionListener {

    //======== Top ========
    private JPanel topPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton clearButton;

    //======== Middle ========
    private JScrollPane titleListScrollPane;
    private JList<String> bookTitleList;

    //======== Bottom ========
    private JPanel bottomPanel;
    private JButton addButton;
    private JButton detailButton;
    private JButton removeButton;

    //======== Data ========
    private BookStorage bookStorage;
    private BookArrayModel bookListModel;

    public BookListWindow(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
        bookListModel = new BookArrayModel(bookStorage.getAll());
        initComponents();
    }


    public void resetToAll() {
        bookListModel.setBookArray(bookStorage.getAll());
        searchTextField.setText("");
        bookTitleList.updateUI();
    }


    public BookStorage getBookStorage() {
        return bookStorage;
    }


    private void initComponents() {
        Container contentPane = getContentPane();
        this.setTitle("Book Management");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== Top ========
        topPanel = new JPanel();
        searchTextField = new JTextField();
        searchButton = new JButton("SEARCH");
        clearButton = new JButton("CLEAR");

        searchButton.addActionListener(this);
        clearButton.addActionListener(this);

        {
            // Set the layout for topPanel and add the buttons.
            searchTextField.setColumns(10); //Textfield setup
            topPanel.setLayout(new FlowLayout()); // sets layout for top panel
            topPanel.add(searchTextField); //add searchtextfield
            topPanel.add(searchButton); //add searchButton
            topPanel.add(clearButton); //add clearButton
        }

        //======== Middle ========
        titleListScrollPane = new JScrollPane();
        bookTitleList = new JList<>();

        {

            bookTitleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
            bookTitleList.setModel(bookListModel); 
        }

        titleListScrollPane.setViewportView(bookTitleList);

        //======== Bottom ========
        bottomPanel = new JPanel();
        addButton = new JButton("ADD");
        detailButton = new JButton("DETAIL");
        removeButton = new JButton("REMOVE");

        addButton.addActionListener(this);
        detailButton.addActionListener(this);
        removeButton.addActionListener(this);

        {

            bottomPanel.setLayout(new FlowLayout()); //setsLayout of the bottom panel
            bottomPanel.add(addButton); //adds addButton
            bottomPanel.add(detailButton); //adds detailButton
            bottomPanel.add(removeButton); //adds removeButton
        }

        contentPane.setLayout(new BorderLayout());
        {

            contentPane.add(topPanel,BorderLayout.NORTH); //adds location/layout of the button
            contentPane.add(titleListScrollPane,BorderLayout.CENTER); //adds location/layout of the button
            contentPane.add(bottomPanel,BorderLayout.SOUTH);//adds location/layout of the button
        }

        pack();
        setLocationRelativeTo(getOwner());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == searchButton) {
           // Action for the SEARCH button
           bookListModel.setBookArray(bookStorage.titleSearch(searchTextField.getText())); //sets array from bookStorage
           bookTitleList.updateUI();
       } else if (e.getSource() == clearButton) {
           // Action for the CLEAR button
           resetToAll(); //resets everything
       } else if (e.getSource() == addButton) {
           // Action for the ADD button
           AddBookDialog abd = new AddBookDialog(this); //adds books to system
           abd.setVisible(true);
       } else if (e.getSource() == detailButton) {
           // Action for the DETAIL button
           UpdateBookDialog ubd = new UpdateBookDialog(this); //Updates books and adds new info
           ubd.showBook(bookStorage.getByTitle(bookTitleList.getSelectedValue()));
           ubd.setVisible(true);
       } else if (e.getSource() == removeButton) {
           // Action for the REMOVE button
           if (!bookTitleList.isSelectionEmpty()) {
               bookStorage.remove(bookTitleList.getSelectedValue()); //removes book from bookTitleList
               JOptionPane.showMessageDialog(this, "Remove Successful!");
               resetToAll();
           }
       }
    }

    public static void main(String[] args) {
        BookStorage bookStore = new BookStorage();
        bookStore.initBooks();
        BookListWindow bookListWindow = new BookListWindow(bookStore);
        bookListWindow.setVisible(true);
    }
}

