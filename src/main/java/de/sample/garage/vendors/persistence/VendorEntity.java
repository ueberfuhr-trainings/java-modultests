package de.sample.garage.vendors.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
