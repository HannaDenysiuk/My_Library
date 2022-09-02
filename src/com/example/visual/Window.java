package com.example.visual;



import com.example.library.Book;
import com.example.library.Library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class Window extends JFrame{
    private JPanel panel1;
    private JButton buttonGetAll;
    private JButton buttonAddNewBook;
    private JButton buttonDeleteBook;
    private JButton buttonByAuthor;
    private JButton buttonGetByGenre;
    private JTextField textFieldSearchWord;
    private JTable tableBook;
    private final static String DATAFILE = "library.dat";
    String[] columns;
    ArrayList<Book> books;
    public Window(String title){
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);

        this.columns = new String[] {
            "author",
            "title",
            "genre",
            "publishingHouse",
            "year",
            "pages"};
        this.books = new ArrayList<>();

        buttonGetAll.addActionListener(e -> {dispatchButtons(1);});
        buttonAddNewBook.addActionListener(e -> {dispatchButtons(2);});
        buttonDeleteBook.addActionListener(e -> {dispatchButtons(3);});
        buttonByAuthor.addActionListener(e -> {dispatchButtons(4);});
        buttonGetByGenre.addActionListener(e -> {dispatchButtons(5);});

    }

    private void dispatchButtons(int e) {
        switch (e){
            case 1:{
                try {
                    this.books = Library.getAllBooks(DATAFILE);
                    TableModel booksArray = new DefaultTableModel(arrayConverter(this.books).toArray(new Object[][] {}), columns);
                    tableBook.setModel(booksArray);
                }catch(Exception ex){
                    System.out.println("56" + ex.getMessage());
                    break;
                }
                break;
            }
            case 2:{
                String author = JOptionPane.showInputDialog(this, "Author");
                String title = JOptionPane.showInputDialog(this, "Title");
                String genre = JOptionPane.showInputDialog(this, "Genre");
                String publishingHouse = JOptionPane.showInputDialog(this, "PublishingHouse");
                String yearS = JOptionPane.showInputDialog(this, "Year");
                String pagesS = JOptionPane.showInputDialog(this, "Pages");
                int year = Integer.parseInt(yearS);
                int pages = Integer.parseInt(pagesS);
                Book book = new Book(author, title, genre, publishingHouse, year, pages);
                System.out.println(book);
                this.books.add(book);
                Library.addNewBook(books, DATAFILE);
                DefaultTableModel tableModel = (DefaultTableModel) tableBook.getModel();
                tableModel.addRow(new String[]{author, title, genre, publishingHouse, yearS, pagesS});
                break;
            }
            case 3:{
                try {

                    int index = tableBook.getSelectedRow();
                    DefaultTableModel tableModel = (DefaultTableModel) tableBook.getModel();

                    String author = (String) tableModel.getValueAt(index, 0);
                    String title  = (String) tableModel.getValueAt(index, 1);
                    String genre = (String) tableModel.getValueAt(index, 2);
                    String publishingHouse = (String) tableModel.getValueAt(index, 3);
                    int year = Integer.parseInt((String) tableModel.getValueAt(index, 4));
                    int pages = Integer.parseInt((String)  tableModel.getValueAt(index, 5));

                    Book book = new Book(author, title, genre, publishingHouse, year, pages);
                    Boolean res = books.remove(book);
                    if(res)
                    {
                        Library.addNewBook(books, DATAFILE);
                        tableModel.removeRow(index);
                    }
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
            }
            case 4:{
                try{
                    String author = textFieldSearchWord.getText();
                    this.books = Library.authorsBooks(author, DATAFILE);
                    TableModel booksArray = new DefaultTableModel(arrayConverter(this.books).toArray(new Object[][] {}), columns);
                    tableBook.setModel(booksArray);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
            }
            case 5:{
                try{
                    String genre = textFieldSearchWord.getText();
                    this.books = Library.booksByGenre(genre, DATAFILE);
                    TableModel booksArray = new DefaultTableModel(arrayConverter(this.books).toArray(new Object[][] {}), columns);
                    tableBook.setModel(booksArray);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
            }
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Window window = new Window("My Library");

        try{
            for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels())
            {
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        }catch (Exception e){}
        SwingUtilities.updateComponentTreeUI(window); //we tie the style to the form

        window.pack();
        window.setVisible(true);
    }
    private ArrayList<String[]> arrayConverter(ArrayList<Book> array){
        ArrayList<String[]> books = new ArrayList<String[]>();
        for (Book b: (ArrayList<Book>) array
        ) {
            books.add(new String[]{b.getAuthor(), b.getTitle(), b.getGenre(), b.getPublishingHouse(),
                    String.valueOf(b.getYear()), String.valueOf(b.getPages())});
        }
        return books;
    }
}
