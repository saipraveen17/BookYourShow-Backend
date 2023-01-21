package com.springboot.BookYourShow.Repository;

import com.springboot.BookYourShow.Models.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<TheatreEntity,Integer> {

    TheatreEntity findByNameAndCity(String name,String city);
}
