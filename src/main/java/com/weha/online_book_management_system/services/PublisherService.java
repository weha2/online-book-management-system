package com.weha.online_book_management_system.services;

import com.weha.online_book_management_system.dtos.publisher.PublisherRequestDTO;
import com.weha.online_book_management_system.dtos.publisher.PublisherResponseDTO;
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

    public List<PublisherResponseDTO> findAllPublishers() {
        return publisherRepository
                .findAll()
                .stream()
                .map(PublisherResponseDTO::new)
                .toList();
    }

    public PublisherResponseDTO findPublisherById(Long id) throws Exception {
        Optional<PublisherEntity> result = publisherRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Not found publisher with id ".concat(String.valueOf(id)));
        }
        return new PublisherResponseDTO(result.get());
    }

    public List<PublisherEntity> findPublisherByAllId(List<Long> idx) throws Exception {
        List<PublisherEntity> publishers = publisherRepository.findAllById(idx);
        if (publishers.isEmpty()) {
            throw new Exception("Not found any publisher.");
        }
        return publishers;
    }

    public PublisherResponseDTO createPublisher(PublisherRequestDTO req) {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setPublisherName(req.publisherName());
        PublisherEntity result = publisherRepository.save(publisher);
        return new PublisherResponseDTO(result);
    }

    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO res) throws Exception {
        Optional<PublisherEntity> publisher = publisherRepository.findById(id);
        if (publisher.isEmpty()) {
            throw new Exception("Not found publisher with id ".concat(String.valueOf(id)));
        }
        PublisherEntity data = publisher.get();
        data.setPublisherName(res.publisherName());
        data.setModifierDate(LocalDateTime.now());
        PublisherEntity result = publisherRepository.save(data);
        return new PublisherResponseDTO(result);
    }

    public boolean deletePublisher(Long id) throws Exception {
        if (!publisherRepository.existsById(id)) {
            throw new Exception("Not found publisher with id ".concat(String.valueOf(id)));
        }
        publisherRepository.deleteById(id);
        return true;
    }

}
