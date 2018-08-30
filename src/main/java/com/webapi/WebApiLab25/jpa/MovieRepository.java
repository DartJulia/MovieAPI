package com.webapi.WebApiLab25.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapi.WebApiLab25.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByCategory(String category);

	List<Movie> findByTitle(String title);

	List<Movie> findByTitleContaining(String keyword);


}
