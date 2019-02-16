package com.yellow.test.repository;

import com.yellow.test.entity.Running;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

public interface RunningRepository extends JpaRepository<Running, Long> {

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "50"))
    @Query("select r from Running r join r.user u where u.uuid = ?1")
    Page<Running> findByUserUuid(String userUuid, Pageable pageable);

    @Query("select r from Running r join r.user u where r.uuid= :uuid and u.uuid = :userUuid")
    Running findByUuidAndUserUuid(@Param("uuid") String uuid, @Param("userUuid") String userUuid);

    @Modifying
    @Query("delete from Running r where r.uuid= :uuid and r.user.uuid = :userUuid")
    int deleteByUuidAndUserUuid(@Param("uuid") String uuid, @Param("userUuid") String userUuid);
}
