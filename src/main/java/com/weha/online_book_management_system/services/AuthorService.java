package com.weha.online_book_management_system.services;

import com.weha.online_book_management_system.dtos.author.CreateAuthorDTO;
import com.weha.online_book_management_system.dtos.author.ResponseAuthorDTO;
import com.weha.online_book_management_system.entity.AuthorEntity;
import com.weha.online_book_management_system.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<ResponseAuthorDTO> findAllAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(ResponseAuthorDTO::new)
                .toList();
    }

    public ResponseAuthorDTO findAuthorById(Long id) throws Exception {
        Optional<AuthorEntity> result = authorRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Not found author with id ".concat(String.valueOf(id)));
        }
        return new ResponseAuthorDTO(result.get());
    }

    public ResponseAuthorDTO createAuthor(CreateAuthorDTO req) {
        AuthorEntity author = new AuthorEntity();
        author.setAuthorName(req.authorName());
        AuthorEntity result = authorRepository.save(author);
        return new ResponseAuthorDTO(result);
    }

    public ResponseAuthorDTO updateAuthor(Long id, CreateAuthorDTO res) throws Exception {
        Optional<AuthorEntity> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            throw new Exception("Not found author with id ".concat(String.valueOf(id)));
        }
        AuthorEntity data = author.get();
        data.setAuthorName(res.authorName());
        data.setModifierDate(LocalDateTime.now());
        AuthorEntity result = authorRepository.save(data);
        return new ResponseAuthorDTO(result);
    }

    public boolean deleteAuthor(Long id) throws Exception {
        if (!authorRepository.existsById(id)) {
            throw new Exception("Not found author with id ".concat(String.valueOf(id)));
        }
        authorRepository.deleteById(id);
        return true;
    }

    public List<AuthorEntity> findAuthorsAllId(List<Long> idx) throws Exception {
        List<AuthorEntity> authors = authorRepository.findAllById(idx);
        if (authors.isEmpty()) {
            throw new Exception("Not found any author.");
        }
        return authors;
    }
}
