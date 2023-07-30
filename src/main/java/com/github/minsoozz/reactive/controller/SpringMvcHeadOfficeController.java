package com.github.minsoozz.reactive.controller;


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

@RestController
@RequestMapping("/v1/books")
public class SpringMvcHeadOfficeController {

    private final RestTemplate restTemplate;
    private final URI baseUri = UriComponentsBuilder.newInstance()
        .scheme("http")
        .host("localhost")
        .port(8080)
        .path("/v1/books")
        .build()
        .encode()
        .toUri();

    public SpringMvcHeadOfficeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<String> getBook(@PathVariable("book-id") long bookId) {
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri();
        ResponseEntity<String> response = restTemplate.getForEntity(getBookUri, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}
