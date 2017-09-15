package vrq.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DeviceDao extends CrudRepository<Device, Long> {
    public List<Device> findByName(String name);
    public List<Device> findByLocationnumberGreaterThan(Integer minLocationNumber);
    public List<Device> findByLocationnumberLessThan(Integer maxLocationNumber);
    public List<Device> findByLocationnumberBetween(Integer minLocationNumber, Integer maxLocationNumber);


}
