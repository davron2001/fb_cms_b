package uz.ages.fb_cms_b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ages.fb_cms_b.entity.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
}
