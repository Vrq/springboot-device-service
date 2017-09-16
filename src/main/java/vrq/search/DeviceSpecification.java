package vrq.search;

import org.springframework.data.jpa.domain.Specification;
import vrq.models.Device;
import vrq.search.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DeviceSpecification implements Specification<Device> {

    private SearchCriteria criteria;

    public DeviceSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return cb.greaterThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return cb.lessThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return cb.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return cb.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
