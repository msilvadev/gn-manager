package br.com.standard.domains.standard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends JpaRepository<Standard, Long> {

    @Query("SELECT COUNT(a) FROM Standard a WHERE a.standardType = :standardType")
    int countByProcessTypeAndProcessStatus(@Param("standardType") StandardType standardType);
}
