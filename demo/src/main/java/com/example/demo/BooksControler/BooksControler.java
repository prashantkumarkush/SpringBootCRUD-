package com.example.demo.BooksControler;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Books.Books;
import com.example.demo.BooksService.BookService;

@RestController
@RequestMapping("/books")
public class BooksControler {
   
	
	private BookService service;
	
	
	public BooksControler(BookService service){
		this.service = service;
	}
	
	@PostMapping("/createBooks")
	public ResponseEntity<Books> createBooks(@RequestBody Books book){
	
	      Books createdBook =  service.createBooks(book);
	      return ResponseEntity
	    		  .status(HttpStatus.CREATED)
	    		  .body(createdBook);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Books>> getBooksById(@PathVariable Long id){
		Optional<Books> books = service.getBooksById(id);

		if(books == null) {
			return ResponseEntity.notFound().build();
		}
//		return ResponseEntity
//				.status(HttpStatus.OK)
//				.body(books);
		return ResponseEntity.ok(books);
		
	}
	
	
	
	
	
	
	@GetMapping
	public ResponseEntity<List<Books>> getAllBooks(){
		List<Books> getAllBooks = service.getAllBooks();
		
		if(getAllBooks == null) {
			return ResponseEntity.notFound().build();
		}
		
		return   ResponseEntity.ok(getAllBooks);
		
	}
	
	   @PutMapping("/{id}")
		public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books bookReq){
			Books updatedBook = service.updateBook(id, bookReq);
			
			if(updatedBook == null) {
			    return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(updatedBook);
			
		}

	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id){
		Boolean deletedBook = service.deleteBook(id);
		if(!deletedBook) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PatchMapping("/delete-soft/{id}")
	public ResponseEntity<String> softDelete(@PathVariable Long id){  //we use another annotation like @RequestParam but some condition is when user send id or anything in the url 
		                                                              // like - delete books/delete-soft/id = 1 ? softDelete = false 
		Boolean softDelete = service.softDelete(id);
		
		if(!softDelete) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok("record Deleted");
		
	}
	
	
}
