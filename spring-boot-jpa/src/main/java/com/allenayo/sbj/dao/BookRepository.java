package com.allenayo.sbj.dao;

import com.allenayo.sbj.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
