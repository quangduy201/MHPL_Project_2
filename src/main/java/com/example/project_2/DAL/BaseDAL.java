package com.example.project_2.DAL;

import com.example.project_2.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class BaseDAL<DTO> {
    protected final Class<DTO> type;
    protected Session session;

    protected BaseDAL(Class<DTO> type) {
        this.type = type;
    }

    public Class<DTO> getType() {
        return type;
    }

    public void openSession() {
        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
    }

    public void closeSession() {
        if (session != null) {
            session.close();
        }
        session = null;
    }

    public boolean transact(Consumer<Session> action) {
        openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            System.out.println("Error while transacting object: " + e);
            return false;
        } finally {
            closeSession();
        }
    }

    public boolean persist(DTO object) {
        Consumer<Session> action = session -> session.persist(object);
        return transact(action);
    }

    public boolean merge(DTO object) {
        Consumer<Session> action = session -> session.merge(object);
        return transact(action);
    }

    public boolean remove(DTO object) {
        Consumer<Session> action = session -> session.remove(object);
        return transact(action);
    }

    public long count() {
        openSession();
        long count;
        try {
            count = session.createQuery("SELECT COUNT(e) FROM " + type.getSimpleName() + " e", Long.class).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error while counting records: " + e);
            count = -1;
        } finally {
            closeSession();
        }
        return count;
    }

    public List<DTO> getByPage(int pageNumber, int pageSize) {
        openSession();
        try {
            SelectionQuery<DTO> query = session.createSelectionQuery("FROM " + type.getSimpleName(), type);
            if (pageNumber > 0 || pageSize > 0) {
                query.setFirstResult((pageNumber - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error while retrieving records by page: " + e);
            return List.of();
        } finally {
            closeSession();
        }
    }

    public List<DTO> getAll() {
        openSession();
        try {
            return getByPage(0, 0);
        } catch (Exception e) {
            System.out.println("Error while retrieving record by id: " + e);
            return null;
        } finally {
            closeSession();
        }
    }
    
    public DTO getById(Object id) {
        openSession();
        try {
            return session.find(type, id);
        } catch (Exception e) {
            System.out.println("Error while retrieving record by id: " + e);
            return null;
        } finally {
            closeSession();
        }
    }

    public <T> List<T> executeQuery(String query, Class<T> tClass, Map<String, Object> parameters) {
        openSession();
        try {
            Query<T> customQuery = session.createQuery(query, tClass);
            if (parameters != null) {
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    customQuery.setParameter(entry.getKey(), entry.getValue());
                }
            }
            return customQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Error while executing custom query: " + e);
            return List.of();
        } finally {
            closeSession();
        }
    }
    
    public int executeUpdate(String query, Map<String, Object> parameters) {
        openSession();
        Transaction transaction = null;
        int rowCount = 0;
        try {
            transaction = session.beginTransaction();
            Query<?> customQuery = session.createQuery(query);
            if (parameters != null) {
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    customQuery.setParameter(entry.getKey(), entry.getValue());
                }
            }
            rowCount = customQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while executing update query: " + e);
        } finally {
            closeSession();
        }
        return rowCount;
    }
    
    public <T> List<T> search(String attributeName, Object searchValue, Class<T> entityClass) {
        openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
            Root<T> root = criteriaQuery.from(entityClass);

            // Joining the related entity if needed
            if (attributeName.contains(".")) {
                String[] parts = attributeName.split("\\.");
                Join<T, ?> join = root.join(parts[0], JoinType.INNER);
                for (int i = 1; i < parts.length - 1; i++) {
                    join = join.join(parts[i], JoinType.INNER);
                }
                attributeName = parts[parts.length - 1];
            }

            // Creating the predicate based on the search value type
            Predicate predicate;
            if (searchValue instanceof String) {
                predicate = criteriaBuilder.like(root.get(attributeName).as(String.class), "%" + searchValue + "%");
            } else {
                predicate = criteriaBuilder.equal(root.get(attributeName), searchValue);
            }

            criteriaQuery.select(root).where(predicate);

            Query<T> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error while executing search query: " + e);
            return List.of(); // Return an empty list or handle the exception as appropriate
        } finally {
            closeSession(); // Close the session in the finally block
        }
    }
    
    public <T> List<T> search(Map<String, Object> searchCriteria, Class<T> entityClass) {
        openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
            Root<T> root = criteriaQuery.from(entityClass);

            if (!searchCriteria.isEmpty()) {
                List<Predicate> predicates = new ArrayList<>();
                for (Map.Entry<String, Object> entry : searchCriteria.entrySet()) {
                    String fieldName = entry.getKey();
                    Object value = entry.getValue();
                    if (fieldName.contains(".")) {
                        String[] parts = fieldName.split("\\.");
                        Join<T, ?> join = root.join(parts[0], JoinType.INNER);
                        
                        predicates.add(criteriaBuilder.like(join.get(parts[parts.length - 1]).as(String.class), "%" + value + "%"));
                    } else {
                        if (value instanceof String) {
                            predicates.add(criteriaBuilder.like(root.get(fieldName).as(String.class), "%" + value + "%"));
                        } else if (value instanceof Number) {
                            String stringValue = value.toString();
                            predicates.add(criteriaBuilder.like(root.get(fieldName).as(String.class), "%" + stringValue + "%"));
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(fieldName), value));
                        }
                    }
                }

                Predicate finalPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));

                criteriaQuery.select(root).where(finalPredicate);
            } else {
                criteriaQuery.select(root);
            }

            Query<T> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error while executing search query: " + e);
            return List.of();
        } finally {
            closeSession();
        }
    }

}
