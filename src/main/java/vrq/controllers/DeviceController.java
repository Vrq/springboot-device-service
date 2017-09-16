package vrq.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import vrq.models.Device;
import vrq.search.DeviceRepository;
import vrq.search.DeviceSpecificationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class DeviceController {

    @Autowired
    private DeviceRepository repo;

    static Logger log = Logger.getLogger(DeviceController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/devices")
    @ResponseBody
    public List<Device> search(@RequestParam(value = "search", required = false) String search,
                               @RequestParam(value = "orderby", required = false) String orderBy,
                               @RequestParam(value = "limit", required = false) Integer maxResults,
                               @RequestParam(value = "offset", required = false) Integer offset) {
        List<Device> searchResult = new ArrayList<>();

        DeviceSpecificationBuilder builder = new DeviceSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Device> spec = builder.build();
        try {
            if (orderBy != null) {
                Sort sort = new Sort(Sort.Direction.ASC, orderBy);
                searchResult = repo.findAll(spec, sort);
            } else {
                searchResult = repo.findAll(spec);
            }
            if (offset != null) {
                searchResult = searchResult.stream().skip(offset).collect(Collectors.toList());
            }
            if (maxResults != null) {
                searchResult = searchResult.stream().limit(maxResults).collect(Collectors.toList());
            }
        } catch (InvalidDataAccessApiUsageException e) {
            log.info("Selected search attribute does not exist: " + e);
        } catch (Exception e) {
            log.info("Error in the search query: " + e);
        }
        return searchResult;

    }
}
