package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Parcelamento;

@Repository
public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Long> {

}
