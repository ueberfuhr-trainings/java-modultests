package de.sample.garage.persistence.vendors;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "Vendor")
@Table(name = "vendors")
public class VendorEntity {

    @Id
    private String shortName;
    private String name;
    private String city;

}
