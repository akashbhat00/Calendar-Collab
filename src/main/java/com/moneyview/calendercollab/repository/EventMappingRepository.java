package com.moneyview.calendercollab.repository;

import com.moneyview.calendercollab.model.EventMapping;
import com.moneyview.calendercollab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventMappingRepository extends JpaRepository<EventMapping, Long>{
    List<EventMapping> findByUser(User user);

    List<EventMapping> findByUserAndScheduledAtBetween(User user, Date fromDate, Date toDate);

    EventMapping findByUserAndEvent(Optional user, Optional event);
//    List<Article> findAllByPublicationTimeBetween(
//            Date publicationTimeStart,
//            Date publicationTimeEnd);
//
//    @Query("select a from Article a where a.creationDateTime <= :creationDateTime")
//    List<Article> findAllWithCreationDateTimeBefore(
//            @Param("creationDateTime") Date creationDateTime);
}
