package uz.pdp.telegraphbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.telegraphbackend.entity.PostEntity;


import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    Page<PostEntity> findAllByLinkContainingIgnoreCase(Pageable pageable,String link);
    Page<PostEntity> findAllByOwnerId(Pageable pageable,UUID ownerId);
}
