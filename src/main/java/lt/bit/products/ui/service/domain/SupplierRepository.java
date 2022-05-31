package lt.bit.products.ui.service.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {

    List<SupplierEntity> findByNameContainingOrIdIs(
            @Param("name") String name,
            @Param("id") UUID uuid
    );

    List<SupplierEntity> findByNameContaining(@Param("name") String name);

    @Query("select p from SupplierEntity p where (:id is null or p.id = :id) and (:name is null or p.name like %:name%)")
    List<SupplierEntity> findByNameAndIdOptional(
            @Param("name") String name,
            @Param("id") UUID uuid
    );

}
