package com.iwlabs.fraud.repository;

import com.iwlabs.fraud.domain.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory,Integer> {
}
