package com.houseof.johari.repository;

import com.houseof.johari.model.SearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SearchHistoryRepository extends MongoRepository<SearchHistory, String> {
    List<SearchHistory> findTop10ByUserIdOrderByTimestampDesc(String userId);
}
