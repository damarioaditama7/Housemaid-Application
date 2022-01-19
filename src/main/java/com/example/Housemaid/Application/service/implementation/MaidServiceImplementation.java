package com.example.Housemaid.Application.service.implementation;

import com.example.Housemaid.Application.entity.Maid;
import com.example.Housemaid.Application.exception.NotFoundException;
import com.example.Housemaid.Application.repository.MaidRepository;
import com.example.Housemaid.Application.service.MaidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MaidServiceImplementation implements MaidService {
    private final MaidRepository maidRepository;

    @Override
    public Maid create(Maid maid) {
        maid.setIsDeleted(false);
        maid.setIsEnable(true);
        return maidRepository.save(maid);
    }

    @Override
    public Maid getById(String id) {
        return maidRepository.findMaidById(id);
    }

    @Override
    public Stream<Maid> getByName(String name) {
        List<Maid> maidList = maidRepository.findMaidByName(name);
        Stream.Builder<Maid> builder = Stream.builder();
        for (Maid maid : maidList) {
            builder.add(maid);
        }
        return builder.build();
    }

    @Override
    public Maid update(Maid maid) {
        Maid validation = getById(maid.getId());
        maid.setCreatedAt(validation.getCreatedAt());
        maid.setIsDeleted(validation.getIsDeleted());
        maid.setIsEnable(validation.getIsEnable());
        return maidRepository.save(maid);
    }

    @Override
    public String deleteById(String id) {
        Maid maid = getById(id);
        if (!maid.getIsDeleted()){
            maid.setIsDeleted(true);
            maidRepository.save(maid);
            return "Delete Success!";
        }
        else{
            throw new NotFoundException("Already deleted!");
        }
    }

    @Override
    public String setIsEnable(String id) {
        Maid maid = getById(id);
        if(maid.getIsEnable()){
            maid.setIsEnable(false);
        }else{
            maid.setIsEnable(true);
        }
        maidRepository.save(maid);
        return String.format("Your enable status is: %b", maid.getIsEnable());
    }

    @Override
    public Stream<Maid> findAll() {
        List<Maid> maidList = maidRepository.findAll();
        Stream.Builder<Maid> builder = Stream.builder();
        for (Maid maid : maidList) {
            builder.add(maid);
        }
        return builder.build();
    }
}
