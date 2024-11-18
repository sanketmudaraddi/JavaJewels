package com.jewels.repository;

import com.jewels.model.SearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SearchHistoryRepository extends MongoRepository<SearchHistory, String> {
    List<SearchHistory> findTop10ByUserIdOrderByTimestampDesc(String userId);
}
