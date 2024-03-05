package com.tom.jpatest.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.tom.jpatest.entity.SoftDeleteEntity;

/**
 * The Spring Data JPA Framework soft delete repository.
 * 
 * @link https://stackoverflow.com/questions/19323557/handling-soft-deletes-with-spring-jpa
 * 		 https://stackoverflow.com/questions/58289970/jpa-soft-delete-repository-auditing
 * @param <T> - the domain type the repository manages
 * @param <ID> - the type of the id of the entity the repository manages
 */
@NoRepositoryBean
public interface SoftDeleteRepository<T extends SoftDeleteEntity<ID, T>, ID extends Serializable> 
				extends CrudRepository<T, ID>, JpaRepository<T, ID> {

	/** Override JpaRepository */
	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.isDeleted = false")
	List<T> findAll();

	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.isDeleted = false")
	List<T> findAll(Sort sort);

	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.id in ?1 and e.isDeleted = false")
	List<T> findAllById(Iterable<ID> ids);

	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 and e.isDeleted = false")
	T getOne(ID id);
	
	/** override CrudRepository */
	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 and e.isDeleted = false")
	Optional<T> findById(ID id);
	
	@Override
	@Transactional(readOnly = true)
	@Query("SELECT count(e)>0 FROM #{#entityName} e WHERE e.id = ?1 and e.isDeleted = false")
	boolean existsById(ID id);

	@Override
	@Transactional(readOnly = true)
	@Query("SELECT count(e) FROM #{#entityName} e WHERE e.isDeleted = false")
	long count();
	
	@Override
	@Deprecated
	void delete(T entity);
	
	@Transactional
	default void softDelete(T entity) {
		entity.setDeleted(true);
		this.save(entity);
	}

	/** @Deprecated 請使用soft delete 資料的方法 */
	@Override
	@Deprecated
	void deleteAll(Iterable<? extends T> entities);
	
	@Transactional
	default void softDeleteAll(Iterable<? extends T> entities) {
	    entities.forEach(entitiy -> entitiy.setDeleted(true));
	    this.saveAll(entities);
	}

	/** @Deprecated 請使用soft delete 資料的方法 */
	@Override
	@Deprecated
	void deleteAll();
	
	@Transactional
	default void softDeleteAll() {
		List<T> entities = this.findAll();
		this.softDeleteAll(entities);
	}
	
	
	
	
	
}
