package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByCategories_Name(CategoryType categoryType);
}
