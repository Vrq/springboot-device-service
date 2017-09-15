package vrq.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import vrq.models.Device;
import vrq.models.DeviceDao;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeviceController {

    @RequestMapping("/devices")
    @ResponseBody
    public List<Device> devices(@RequestParam(value="name") String name) {
        List<Device> devices = deviceDao.findByName(name);
        return devices;
    }

    @RequestMapping("/devicesfromlocations")
    @ResponseBody
    public List<Device> devicesFromLocations(@RequestParam(value="min-locationnumber", required=false) Integer minLocationNumber, @RequestParam(value="max-locationnumber", required=false) Integer maxLocationNumber) {
        List<Device> devices = new ArrayList<>();
        if(minLocationNumber != null) {
            if(maxLocationNumber != null) {
                devices = deviceDao.findByLocationnumberBetween(minLocationNumber, maxLocationNumber);
            } else {
                devices = deviceDao.findByLocationnumberGreaterThan(minLocationNumber);
            }
        } else if(maxLocationNumber != null) {
            devices = deviceDao.findByLocationnumberLessThan(maxLocationNumber);
        }
        return devices;
    }


    @Autowired
    private DeviceDao deviceDao;
}
