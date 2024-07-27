package com.w3lsolucoes.clientCRUD.services;

import com.w3lsolucoes.clientCRUD.dto.ClientDTO;
import com.w3lsolucoes.clientCRUD.entities.Client;
import com.w3lsolucoes.clientCRUD.exceptions.DataBaseException;
import com.w3lsolucoes.clientCRUD.exceptions.ResourceNotFoundException;
import com.w3lsolucoes.clientCRUD.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(ClientDTO::new);
    }

    public ClientDTO save(ClientDTO dto) {
        try {
            Client client = new Client();
            BeanUtils.copyProperties(dto, client);
            return new ClientDTO(clientRepository.save(client));
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("CPF already exists");
        }
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client client = clientRepository.getReferenceById(id);
            String[] ignoredProperties = {"id"};
            BeanUtils.copyProperties(dto, client, ignoredProperties);
            return new ClientDTO(clientRepository.save(client));
        }catch (EntityNotFoundException | FatalBeanException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
        try {
            clientRepository.deleteById(id);
            clientRepository.flush();
        }catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }

    }
}
