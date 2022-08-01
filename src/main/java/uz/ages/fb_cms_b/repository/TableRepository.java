package uz.ages.fb_cms_b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ages.fb_cms_b.entity.Table;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
}
