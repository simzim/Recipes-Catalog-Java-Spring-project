package lt.simzim.recipescatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.simzim.recipescatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
