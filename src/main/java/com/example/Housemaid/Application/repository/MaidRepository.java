package com.example.Housemaid.Application.repository;

import com.example.Housemaid.Application.entity.Maid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MaidRepository extends JpaRepository<Maid,String> {
    @Query(value = "SELECT * FROM mst_maid WHERE is_deleted=false", nativeQuery = true)
    List<Maid> findAll();

    @Query(value = "SELECT * FROM mst_maid WHERE id = :id AND is_deleted=false", nativeQuery = true)
    Maid findMaidById(String id);

    @Query(value = "SELECT * FROM mst_maid WHERE LOWER(name) LIKE %:name% AND is_deleted=false",nativeQuery = true)
    List<Maid> findMaidByName(String name);
}
