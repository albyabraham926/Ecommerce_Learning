package com.ecommerce.project.service.impl;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repository.AddressRepository;
import com.ecommerce.project.repository.UserRepository;
import com.ecommerce.project.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {

        Address address = modelMapper.map(addressDTO,Address.class);

        List<Address> addressList = user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);

        address.setUser(user);
        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress,AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        if (addresses.isEmpty()){
            throw new APIException("No Address Exists in the system");
        }
        List<AddressDTO> addressDTOList = addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();

        return addressDTOList;
    }

    @Override
    public AddressDTO getAddresseById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));
        return modelMapper.map(address,AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddresses(User user) {
        List<Address> addresses = user.getAddresses();
        if (addresses.isEmpty()){
            throw new APIException("No Address Exists in the system for the User");
        }
        return addresses.stream().map(address -> modelMapper.map(address,AddressDTO.class)).toList();
    }

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address addressFromDb = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address","addressId",addressId));

        addressFromDb.setCity(addressDTO.getCity());
        addressFromDb.setPincode(addressDTO.getPincode());
        addressFromDb.setCountry(addressDTO.getCountry());
        addressFromDb.setState(addressDTO.getState());
        addressFromDb.setStreet(addressDTO.getStreet());
        addressFromDb.setBuildingName(addressDTO.getBuildingName());

        Address updatedAddress = addressRepository.save(addressFromDb);

        User user = addressFromDb.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);

        return  modelMapper.map(updatedAddress,AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDb = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address","addressId",addressId));

        User user = addressFromDb.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.deleteById(addressId);
        return "Address deleted successfully with addressID:"+addressId;
    }


}
