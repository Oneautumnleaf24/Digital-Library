
/**
 * A collection of {@link Book}.
 */
public class BookStorage {

    private Book[] books = new Book[100];

    public BookStorage() {

    }


    public void initBooks() {
        books[0] = new Book("Adventures in Coding: A journey", "Reki Keiwata ", 1000, Book.BookCategory.Programming);
        books[1] = new Book("Coding and You: An Idiots guide", "Kirby Daly", 250, Book.BookCategory.Programming);
        books[2] = new Book("Programmer: From Beginner to Master", "Richie Larsen", 566, Book.BookCategory.Programming);
        books[3] = new Book("Coding for Dummies", "Freya Hudson", 760, Book.BookCategory.Design);
        books[4] = new Book("Programming and Math", "John Bentley", 110, Book.BookCategory.Programming);
        books[5] = new Book("Agile Software Craft", "Sumaiya Marin", 249, Book.BookCategory.Design);
        books[6] = new Book("Basic Java", "Miruna Mcloughlin", 928, Book.BookCategory.Programming);
        books[7] = new Book("A story for the ages", "Saira Lynn", 900, Book.BookCategory.Database);
        books[8] = new Book("Stress and Coping", "Teejay Holding", 600, Book.BookCategory.Design);
        books[9] = new Book("Pneumonia and Cures", "Edward Rose", 890, Book.BookCategory.Design);
        books[10] = new Book("Ottoman Harems: Power and Wives", "William Turner", 580, Book.BookCategory.Programming);
        books[11] = new Book("The fall of the Roman Empire in the 15th century", "Jack Williams", 824, Book.BookCategory.Programming);
        books[12] = new Book("Politics: A game of Deception and Keys", "Christine Pittman", 208, Book.BookCategory.Database);
        books[13] = new Book("Rules for Rulers: Why leaders are corrupt", "Lynn	Tucker", 200, Book.BookCategory.Database);
        books[14] = new Book("The Everdistant Utopia", "Gareth Vysoren", 500, Book.BookCategory.Programming);
        books[15] = new Book("Myths of Ireland: Cu Chulainn", "Ana	Wise", 830, Book.BookCategory.Design);
        books[16] = new Book("Hercules: The Man, The Myth, The Legend", "Penny	Gutierrez", 810, Book.BookCategory.Programming);
        books[17] = new Book("Ancient civilizations: Religions", "Arthur Shaw", 910, Book.BookCategory.Database);
        books[18] = new Book("Old Ways and Old paths", "Dorothy	A. Cobb", 727, Book.BookCategory.Design);
        books[19] = new Book("The history of Tal'Dorei", "Matilda Merceria", 422, Book.BookCategory.Database);
        books[20] = new Book("The tragedy of Medea", "Cecil	Harvey", 299, Book.BookCategory.Design);
   }

    public void update(Book book) { //update book code
        for(int i = 0; i < books.length ; i++) //set up conditions for update
            if(books[i] != null && books[i].getTitle().equals(book.getTitle()))
            {
                Book tempBook = getByTitle(book.getTitle()); // code for tempBook
                tempBook.setAuthors(book.getAuthors()); //sets Authors from book class
                tempBook.setCategory(book.getCategory()); //sets category from book class
                tempBook.setPages(book.getPages());
                break;
            }
    }


    public void remove(String bookTitle) {
        for(int i = 0; i < books.length ; i++) //int i requirements
            if(books[i] != null && books[i].getTitle().equals(bookTitle)) //sets up conditions for nullifying/removing book
            {
                for(int j = i; j < books.length-1 ; j++)
                    if(books[j+1] != null)
                        books[j] = books[j+1];
                    else 
                        books[j] = null; //removes book
                break;
            }
    }


    public void add(Book book) { //class for adding book
        for(int i = 0; i < books.length; i++)
            if(books[i] == null)
            {
                books[i] = book;
                break;
            }
    }


    public Book getByTitle(String title) {
        for(int i = 0; i < books.length ; i++)
            if(books[i] != null && books[i].getTitle().equals(title))
                return books[i];
        return null;
    }


    public Book[] titleSearch(String keyword) {
        int length = 0 , index = 0;
        Book []searchList;
        for(Book b : books)
            if(b != null && b.getTitle().contains(keyword))
                length++;
        searchList = new Book[length];
        for(Book b : books)
            if(b != null && b.getTitle().contains(keyword))
            {
                searchList[index] = b;
                index++;
            }
        if(length > 0)
            return sortByTitle(searchList);
                
        return new Book[0];
    }

    public Book[] getAll() {
        
        return sortByTitle(books); //returns books
    }


    private Book[] sortByTitle(Book[] bookArray) {
        int length = 0 , index = 0; 
        for(int i = 0; i < bookArray.length; i++)
        {
            for(int j = i+1; j < bookArray.length; j++)
                if(bookArray[i] != null && bookArray[j] != null && bookArray[i].getTitle().compareTo(bookArray[j].getTitle()) > 0)
                {
                    Book temp = bookArray[i];
                    bookArray[i] = bookArray[j];
                    bookArray[j] = temp;
                }
            if(bookArray[i] != null)
                length++;
        }
        Book []bookList = new Book[length];
        for(int i = 0; i < bookArray.length; i++)
            if(bookArray[i] != null)
            {
                bookList[index] = bookArray[i]; //if statement for bookList and bookArray
                index++;
            }
        
        return bookList; //returns book list
    }

}
