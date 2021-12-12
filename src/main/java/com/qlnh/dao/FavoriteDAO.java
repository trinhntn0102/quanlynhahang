package com.qlnh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlnh.entity.Favorite;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {
	@Query("SELECT p FROM Favorite p WHERE p.username like '%?1%' ")
	List<Favorite> findByUsername(String username);
	
	@Query(value="INSERT INTO Favorite VALUES (?1, ?2)",nativeQuery = true)
	Favorite insert(Integer id, String username);
}
