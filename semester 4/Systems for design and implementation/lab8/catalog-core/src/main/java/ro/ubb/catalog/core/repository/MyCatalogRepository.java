package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.Query;
import ro.ubb.catalog.core.model.Catalog;

public interface MyCatalogRepository extends CatalogRepository<Catalog, Integer> {
}
