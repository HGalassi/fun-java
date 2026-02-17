package fun.ports.out.dynamodb.repository;

public interface CRUDOperations<T> {

     T save(T entity);
     T find(T entity);
}
