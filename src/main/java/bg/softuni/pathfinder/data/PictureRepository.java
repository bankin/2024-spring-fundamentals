package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    Picture findFirstByRoute_Id(Long id);
}
