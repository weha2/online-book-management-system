package com.weha.online_book_management_system.services;

import com.weha.online_book_management_system.dtos.publisher.CreatePublisherDTO;
import com.weha.online_book_management_system.dtos.publisher.ResponsePublisherDTO;
import com.weha.online_book_management_system.entity.PublisherEntity;
import com.weha.online_book_management_system.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<ResponsePublisherDTO> findAllPublishers() {
        return publisherRepository
                .findAll()
                .stream()
                .map(ResponsePublisherDTO::new)
                .toList();
    }

    public ResponsePublisherDTO findPublisherById(Long id) throws Exception {
        Optional<PublisherEntity> result = publisherRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Not found publisher with id ".concat(String.valueOf(id)));
        }
        return new ResponsePublisherDTO(result.get());
    }

    public List<PublisherEntity> findPublisherByAllId(List<Long> idx) throws Exception {
        List<PublisherEntity> publishers = publisherRepository.findAllById(idx);
        if (publishers.isEmpty()) {
            throw new Exception("Not found any publisher.");
        }
        return publishers;
    }

    public ResponsePublisherDTO createPublisher(CreatePublisherDTO req) {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setPublisherName(req.publisherName());
        PublisherEntity result = publisherRepository.save(publisher);
        return new ResponsePublisherDTO(result);
    }

    public ResponsePublisherDTO updatePublisher(Long id, CreatePublisherDTO res) throws Exception {
        Optional<PublisherEntity> publisher = publisherRepository.findById(id);
        if (publisher.isEmpty()) {
            throw new Exception("Not found publisher with id ".concat(String.valueOf(id)));
        }
        PublisherEntity data = publisher.get();
        data.setPublisherName(res.publisherName());
        data.setModifierDate(LocalDateTime.now());
        PublisherEntity result = publisherRepository.save(data);
        return new ResponsePublisherDTO(result);
    }

    public boolean deletePublisher(Long id) throws Exception {
        if (!publisherRepository.existsById(id)) {
            throw new Exception("Not found publisher with id ".concat(String.valueOf(id)));
        }
        publisherRepository.deleteById(id);
        return true;
    }

}
