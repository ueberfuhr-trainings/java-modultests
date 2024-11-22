package de.sample.garage.vendors.boundary;

import de.sample.garage.vendors.domain.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService service;
    private final VendorDtoMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<VendorDto> findAll() {
        return service.findAll()
            .map(mapper::map)
            .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VendorDto findByShortName(@PathVariable("id") String shortName) {
        return service.findByShortName(shortName)
            .map(mapper::map)
            .orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{shortName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody VendorDto vendor, @PathVariable("shortName") String shortName) {
        vendor.setShortName(shortName);
        service.save(mapper.map(vendor));
    }

}
