package uz.pdp.telegraphbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.telegraphbackend.entity.PostEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    List<PostEntity> findPostEntitiesByLinkContainingIgnoreCaseOrderByCreatedDateAsc(String link);
    List<PostEntity> findPostEntitiesByOwnerIdOrderByCreatedDate(UUID id);
}
