package com.hcl.ownerservice.service;

import com.hcl.ownerservice.dao.service.OwnerDaoService;
import com.hcl.ownerservice.dto.OwnerDto;
import com.hcl.ownerservice.dto.PostDto;
import com.hcl.ownerservice.mapper.OwnerMapper;
import com.hcl.ownerservice.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerDaoService ownerDaoService;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public OwnerService(OwnerDaoService ownerDaoService, LoadBalancerClient loadBalancerClient) {
        this.ownerDaoService = ownerDaoService;
        this.loadBalancerClient = loadBalancerClient;
    }

    public List<OwnerDto> getAll() {
        final List<Owner> ownerList = ownerDaoService.getAll();
        return OwnerMapper.toDto(ownerList);
    }

    public OwnerDto getOneById(int id) {
        final Optional<Owner> optionalUserProfile = ownerDaoService.getOneById(id);
        if(optionalUserProfile.isEmpty()) {
            throw new RuntimeException("Owner not found");
        }
        return OwnerMapper.toDto(optionalUserProfile.get());
    }

    public OwnerDto create(OwnerDto ownerDto) {
        final Owner owner = OwnerMapper.toEntity(ownerDto);
        final Owner savedOwner = ownerDaoService.create(owner);
        return OwnerMapper.toDto(savedOwner);
    }

    public OwnerDto update(OwnerDto ownerDto) {
        final Owner owner = OwnerMapper.toEntity(ownerDto);
        final Owner updatedOwner = ownerDaoService.update(owner);
        return OwnerMapper.toDto(updatedOwner);
    }

    public boolean delete(int id) {
        return ownerDaoService.delete(id);
    }

    public List<PostDto> getAllPosts() {
        ServiceInstance userService = loadBalancerClient.choose("USER-SERVICE");
        String baseUrl = userService.getUri().toString();
        System.out.println(baseUrl);

        String urlPath = baseUrl + "/api/profileService/v1/posts";

        ResponseEntity<List<PostDto>> responseEntity = restTemplate.exchange(
                urlPath,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PostDto>>() {
                }
        );

        return responseEntity.getBody();
    }


    public PostDto approvePost(int postId) {
        ServiceInstance userService = loadBalancerClient.choose("USER-SERVICE");
        String baseUrl = userService.getUri().toString();

        String urlPath = baseUrl + "/api/profileBookService/v1/users/" + postId + "/approve";

        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(
                urlPath,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PostDto>() {
                }
        );

        return responseEntity.getBody();
    }

    public PostDto rejectPost(int postId) {
        ServiceInstance userService = loadBalancerClient.choose("USER-SERVICE");
        String baseUrl = userService.getUri().toString();

        String urlPath = baseUrl + "/api/profileBookService/v1/users/" + postId + "/reject";

        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(
                urlPath,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PostDto>() {
                }
        );

        return responseEntity.getBody();
    }
}
