package sarahguarneri.CAPSTONE.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.Address;
import sarahguarneri.CAPSTONE.payloads.address.NewAddressDTO;
import sarahguarneri.CAPSTONE.payloads.address.NewAddressResponseDTO;
import sarahguarneri.CAPSTONE.services.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    //GET ALL
    @GetMapping
    public List<Address> getAddresses(){
        return addressService.getAllAddress();
    }

    @GetMapping("/{addressId}")
    public Address getAddressById(@PathVariable int id){
        return addressService.findById(id);
    }

    @PostMapping
    public NewAddressResponseDTO saveAddress(@RequestBody @Validated NewAddressDTO body){
        Address newAddress = addressService.save(body);
        return new NewAddressResponseDTO(newAddress.getId());
    }

    @PutMapping("/{addressId}")
    public Address findAndUpdate(@PathVariable int id, @RequestBody Address body){
        return addressService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{addressId}")
    public void findAndDelete(@PathVariable int id){
        addressService.findAndDelete(id);
    }


}
