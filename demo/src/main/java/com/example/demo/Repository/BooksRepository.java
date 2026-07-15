package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.example.demo.Books.Books;

//@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

	// select * from Books where bookId = 1 and softDelete = false;
	Optional<Books> findByBookIdAndSoftDeleteFalse(Long bookId);

	//select * from Books where softDeleteFalse;
	List<Books> findBySoftDeleteIsFalse();
	
	//JPA automatic run this mySql query 

}
