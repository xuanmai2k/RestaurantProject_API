package com.project.restaurant.user.services;

import com.project.restaurant.user.entities.Address;
import com.project.restaurant.user.entities.User;
import com.project.restaurant.user.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Represents address service
 * @author xuanmai
 * @since 2023-11-22
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    /**
     * This method is used to get addresses base on id
     *
     * @param id This is address id
     * @return address base on address id
     */
    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    /**
     * This method is used to save address
     *
     * @param address This is address
     */
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    /**
     * This method is used to delete address by id
     *
     * @param id This is address id
     */
    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    /**
     * This method is used to find list address base on user
     *
     * @param user This is user
     * @return list addresses base on user
     */
    @Override
    public List<Address> findByUser(User user) {
        return addressRepository.findByUser(user);
    }

    /**
     * This method is used to update Default Addresses To False
     *
     * @param userId This is userId
     */
    @Override
    @Transactional
    public void updateDefaultAddressesToFalse(Long userId) {
        Optional<List<Address>> defaultAddressesOptional = addressRepository.findByUserIdAndIsDefaultTrue(userId);
        if (defaultAddressesOptional.isPresent()) {
            List<Address> defaultAddresses = defaultAddressesOptional.get();

            for (Address address : defaultAddresses) {
                address.setDefault(false);
            }

            addressRepository.saveAll(defaultAddresses);
        }
    }

    /**
     * This method is used to get Addresses By UserId
     *
     * @param userId This is userId
     */
    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }
}
