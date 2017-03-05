package com.jee.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="bookcase")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookCase {

    @XmlElement (name = "bookshelf")
    private List<BookShelf> bookShelves;

    public BookCase()
    {
    }

    public BookCase(List<BookShelf> bookShelves)
    {
        this.bookShelves = bookShelves;
    }
    public List<BookShelf> getBookShelves()
    {
        return bookShelves;
    }

    public void setBookShelves(List<BookShelf> bookShelves)
    {
        this.bookShelves = bookShelves;
    }

    @Override
    public String toString()
    {
        return "BookCase{" +
                "bookShelves=" + bookShelves +
                '}';
    }
}
