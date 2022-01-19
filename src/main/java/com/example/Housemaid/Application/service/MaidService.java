package com.example.Housemaid.Application.service;

import com.example.Housemaid.Application.entity.Maid;

import java.util.stream.Stream;

public interface MaidService {
    Maid create(Maid maid);

    Maid getById(String id);

    Stream<Maid> getByName(String name);

    Maid update(Maid maid);

    String deleteById(String id);

    String setIsEnable(String id);

    Stream<Maid> findAll();
}
