package com.houseof.johari.service;

import com.houseof.johari.model.SearchHistory;
import com.houseof.johari.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHistoryService {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    public List<SearchHistory> getRecentSearches(String userId) {
        return searchHistoryRepository.findTop10ByUserIdOrderByTimestampDesc(userId);
    }
}
