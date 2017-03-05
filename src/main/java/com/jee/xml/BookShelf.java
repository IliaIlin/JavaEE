package com.jee.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class BookShelf {

    @XmlAttribute(name = "number", required = true)
    private int number;

    @XmlElement(name="book")
    private List<Book> books;

    public BookShelf()
    {
    }

    public BookShelf(int number, List<Book> books)
    {
        this.number = number;
        this.books = books;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    @Override
    public String toString()
    {
        return "BookShelf{" +
                "number=" + number +
                ", books=" + books +
                '}';
    }
}
