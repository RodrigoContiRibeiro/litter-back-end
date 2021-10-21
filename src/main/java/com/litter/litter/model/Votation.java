package com.litter.litter.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Votation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "votation")
    private List<Book> booksToVote;

    private boolean isFinished;

    @OneToOne
    @JoinColumn(name = "decided_id", referencedColumnName = "id")
    private Book decidedBook;

    public void evaluateVotes() {
        Book mostVotedbook = (Book) booksToVote.stream().map(book -> {
            Book mostVoted = new Book();
            mostVoted.setVotes(0);
            if (book.getVotes() > mostVoted.getVotes()) {
                mostVoted = book;
            }
            return mostVoted;
        });
    }

    public Votation() {
    }

    public Votation(Long id, List<Book> booksToVote, boolean isFinished, Book decidedBook) {
        this.id = id;
        this.booksToVote = booksToVote;
        this.isFinished = isFinished;
        this.decidedBook = decidedBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooksToVote() {
        return booksToVote;
    }

    public void setBooksToVote(List<Book> booksToVote) {
        this.booksToVote = booksToVote;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Book getDecidedBook() {
        return decidedBook;
    }

    public void setDecidedBook(Book decidedBook) {
        this.decidedBook = decidedBook;
    }
}
