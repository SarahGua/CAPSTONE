package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Address;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.address.NewAddressDTO;
import sarahguarneri.CAPSTONE.repositories.AddressDAO;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressDAO addressDAO;

    public List<Address> getAllAddress(){
        return addressDAO.findAll();
    }

    public Address findById(int id){
        return addressDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Address save(NewAddressDTO body){
        Address newAddress = new Address();

        newAddress.setStreet(body.street());
        newAddress.setStreet_number(body.street_number());
        newAddress.setCity(body.city());
        newAddress.setPostal_code(body.postal_code());
        newAddress.setState(body.state());

        return addressDAO.save(newAddress);
    }

    public Address findByIdAndUpdate(int id, Address body){
        Address found = findById(id);

        found.setStreet(body.getStreet());
        found.setStreet_number(body.getStreet_number());
        found.setCity(body.getCity());
        found.setPostal_code(body.getPostal_code());
        found.setState(body.getState());

        return addressDAO.save(found);
    }

    public void findAndDelete(int id){
        Address found = findById(id);
        addressDAO.delete(found);
    }
}
