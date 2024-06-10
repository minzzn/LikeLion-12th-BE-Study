package com.example.springstudy.Repository;

import com.example.springstudy.Entity.LoanEntity;
import com.example.springstudy.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

  @Query("SELECT l FROM LoanEntity l WHERE l.book.id = :bookId")
  List<LoanEntity> findByBookId(@Param("bookId") Long bookId);

  List<LoanEntity> findByUserId(Long userId);
  List<LoanEntity> findByUserIdAndBookIdAndReturnedFalse(Long userId, Long bookId);

  boolean existsByUserIdAndBookId(Long userId, Long bookId);

  long countByBookId(Long bookId);

  int countByUserId(Long userId);
}
