package com.jewels.service;

import com.jewels.model.SearchHistory;
import com.jewels.repository.SearchHistoryRepository;
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
