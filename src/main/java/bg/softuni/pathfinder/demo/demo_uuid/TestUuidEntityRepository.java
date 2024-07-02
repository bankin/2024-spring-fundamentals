package bg.softuni.pathfinder.demo.demo_uuid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestUuidEntityRepository extends JpaRepository<TestUuidEntity, UUID> {
}
