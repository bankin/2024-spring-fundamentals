package bg.softuni.pathfinder.demo.demo_uuid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class TestUuidInitService implements CommandLineRunner {

    private final TestUuidEntityRepository testUuidEntityRepository;

    public TestUuidInitService(TestUuidEntityRepository testUuidEntityRepository) {
        this.testUuidEntityRepository = testUuidEntityRepository;
    }

    @Override
    public void run(String... args) {
        TestUuidEntity testUuidEntity = new TestUuidEntity();
        testUuidEntity.setContent("Test content");

        TestUuidEntity testEntity =  testUuidEntityRepository.save(testUuidEntity);

        System.out.println();
    }
}
