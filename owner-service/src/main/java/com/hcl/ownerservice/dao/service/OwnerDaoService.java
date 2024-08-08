package com.hcl.ownerservice.dao.service;

import com.hcl.ownerservice.model.Owner;
import com.hcl.ownerservice.repository.OwnerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerDaoService {

    private final OwnerRepository ownerRepository;

    public OwnerDaoService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    public boolean isExistById(int id) {
        final Optional<Owner> optionalUserProfile = ownerRepository.findById(id);
        return optionalUserProfile.isPresent();
    }
    public Optional<Owner> getOneById(int id) {
        return ownerRepository.findById(id);
    }

    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    public boolean delete(int id) {
        ownerRepository.deleteById(id);
        return true;
    }


}
