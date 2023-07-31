package com.github.minsoozz.controller;


import com.github.minsoozz.domain.Book;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 검색용 클라이언트 PC에서 들어오는 요청을 처리하는 Spring MVC 기반 본사 API Server
 */
@RestController
@RequestMapping("/v1/books")
public class SpringMvcHeadOfficeController {

    private final RestTemplate restTemplate;
    private final URI baseUri = UriComponentsBuilder.newInstance()
        .scheme("http")
        .host("localhost")
        .port(7070)
        .path("/v1/books")
        .build()
        .encode()
        .toUri();

    public SpringMvcHeadOfficeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId) {
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri();
        ResponseEntity<Book> response = restTemplate.getForEntity(getBookUri, Book.class);
        return ResponseEntity.ok(response.getBody());
    }
}
