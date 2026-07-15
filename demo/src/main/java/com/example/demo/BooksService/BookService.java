package com.example.demo.BooksService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Books.Books;
import com.example.demo.Repository.BooksRepository;

@Service
public class BookService {

	private BooksRepository repo;
	
	BookService(BooksRepository repo) {
		this.repo = repo;
	}
	
	public Books createBooks(Books book){
	   book.setSoftDelete(false); // byDefault softDelete false either user send true value but it change in to false 
		Books BooksService = repo.save(book);
		return BooksService;
		
	}
	
	
	
	// select * from books where id = 1 and softDeleted = false;
		// findByIdDeletedFalse

	public Optional<Books> getBooksById(Long id) {
		Optional<Books> book = repo.findByBookIdAndSoftDeleteFalse(id);
		if(book.isEmpty()) {
			return null;
		}
		return book;
	}
	
	
	
	
	// 

	public List<Books> getAllBooks() {
		List<Books> AllBooks = repo.findBySoftDeleteIsFalse();
		return AllBooks;
	}
	
	
	

	public boolean deleteBook(Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}



	public Books updateBook(Long id, Books bookReq) {
		Optional<Books> existingBook = repo.findByBookIdAndSoftDeleteFalse(id);
		if(existingBook.isEmpty()) {
			return null;
		}
		
		Books bookToSave = existingBook.get();
		bookToSave.setBookName(bookReq.getBookName());
		bookToSave.setBookAuthorName(bookReq.getBookAuthorName());
		bookToSave.setPrice(bookReq.getPrice());
		bookToSave.setSoftDelete(false);  // if book is softDeleted = true  then not allow to update , it allow when softDelete false
		
		return repo.save(bookToSave);
	}

	public Boolean softDelete(Long id) {
     Optional<Books> existingBook  =repo.findByBookIdAndSoftDeleteFalse(id);
     
     if(existingBook.isEmpty()) {
    	 return false;
     }
     // take all those Book in bookToSave,  which is (select * from books where bookId = ? And SoftDelete = false)
     Books bookToSave = existingBook.get();
     //all those method come in bookToSave whose softDelete true because query allow only true softDelete method 
     // And setSoftDelete True
     bookToSave.setSoftDelete(true);
     //then save bookToSave and return true
     repo.save(bookToSave);
     
		return true;
	}

	

     
	
	

	

	
}
