package com.hcl.ownerservice.mapper;

import com.hcl.ownerservice.dto.OwnerDto;
import com.hcl.ownerservice.model.Owner;

import java.util.List;
import java.util.stream.Collectors;

public class OwnerMapper {

    public static List<OwnerDto> toDto(List<Owner> ownerList) {
        return ownerList.stream()
                .map(owner -> toDto(owner))
                .collect(Collectors.toList());
    }

    public static OwnerDto toDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setUsername(owner.getUsername());
        ownerDto.setPassword(owner.getPassword());
        ownerDto.setEmail(owner.getEmail());

        return ownerDto;
    }

    public static Owner toEntity(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setId(ownerDto.getId());
        owner.setUsername(ownerDto.getUsername());
        owner.setPassword(ownerDto.getPassword());
        owner.setEmail(ownerDto.getEmail());

        return owner;
    }
}
