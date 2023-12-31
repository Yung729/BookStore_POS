/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.time.Year;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Yung
 */
public class Author {

    private String name;
    private int yearOfBirth;
    protected boolean arrive;
    protected static final double DISCOUNT_RATE = 0.5;
    private final int currentYear = Year.now().getValue();

    Author() {
    }

    
    
    Author(String name, int yearOfBirth, boolean arrive) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.arrive = arrive;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setArrive(boolean arrive) {
        this.arrive = arrive;
    }

    //getter
    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public boolean checkArrive() {
        return arrive;
    }

    //method
    public void displayAuthorDetail(Author author) {
        System.out.println("  Author Name           : " + author.name);
        System.out.println("  Author Age            : " + author.yearOfBirth);
        System.out.println("  Author status         : " + convertBooleanToString(author.arrive));
        System.out.println("====================================");
    }

    public static void updateDiscount(int mode, String authorSearch, ArrayList<Book> bookArray) {

        for (Book latestBook : bookArray) {
            if (latestBook.author.getName().equals(authorSearch)) {
                if (mode == 1) {
                    double newSoldPrice = latestBook.getSoldPrice() - (latestBook.getSoldPrice() * DISCOUNT_RATE);

                    latestBook.setSoldPrice(newSoldPrice);

                } else if (mode == 2) {

                    double newSoldPrice = (latestBook.getSoldPrice() / DISCOUNT_RATE);

                    latestBook.setSoldPrice(newSoldPrice);
                }
            }

        }

    }

    public static void updateDiscount(Book book, int mode) {
        if (mode == 1) {
            double newSoldPrice = book.getSoldPrice() - (book.getSoldPrice() * DISCOUNT_RATE);

            book.setSoldPrice(newSoldPrice);

        } else if (mode == 2) {

            double newSoldPrice = (book.getSoldPrice() / DISCOUNT_RATE);

            book.setSoldPrice(newSoldPrice);
        }

    }

    //validation
    public boolean validAuthorName(String name) {
        if (name.length() < 5) {
            return false;
        }

        for (int i = 0; i < name.length(); i++) {

            if (!(Character.isLetter(name.charAt(i)))) {

                if (Character.isWhitespace(name.charAt(i))) {
                    continue;
                }
                return false;
            }

        }

        return true;
    }

    public boolean validAuthorYearOfBirth(int yOB) {
        if (yOB <= 1800) {
            return false;
        } else if (yOB > currentYear) {
            return false;
        }
        return true;
    }

    // overloading method of editBook 
    public static void editAuthor(ArrayList<Book> bookArray, String authorSearch, String newAuthorName) {
        for (Book latestBook : bookArray) {
            if (latestBook.author.getName().equals(authorSearch)) {
                latestBook.author.setName(newAuthorName);
            }

        }
    }

    public static void editAuthor(ArrayList<Book> bookArray, String authorSearch, int newAge) {
        for (Book latestBook : bookArray) {
            if (latestBook.author.getName().equals(authorSearch)) {
                latestBook.author.setYearOfBirth(newAge);
            }
        }
    }

    public static void editAuthor(ArrayList<Book> bookArray, String authorSearch, boolean arrive) {
        for (Book latestBook : bookArray) {
            if (latestBook.author.getName().equals(authorSearch)) {
                latestBook.author.setArrive(arrive);
            }
        }

    }

    public String convertBooleanToString(boolean arrive) {
        if (arrive) {
            return Assignment.GREEN + "Alive" + Assignment.RESET;
        } else {
            return Assignment.RED + "Pass Away" + Assignment.RESET;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return String.format("%-15s    %-10d  %s", name, yearOfBirth, convertBooleanToString(arrive));
    }

}
