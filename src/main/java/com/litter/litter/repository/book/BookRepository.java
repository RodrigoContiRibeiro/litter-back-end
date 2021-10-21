package com.litter.litter.repository.book;

import com.litter.litter.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public boolean existsByTitle(String title);
}
