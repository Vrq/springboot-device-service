package vrq.search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import vrq.models.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceSpecificationBuilder {

    private final List<SearchCriteria> params;

    public DeviceSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public DeviceSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Device> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Device>> specs = new ArrayList<Specification<Device>>();
        for (SearchCriteria param : params) {
            specs.add(new DeviceSpecification(param));
        }

        Specification<Device> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}