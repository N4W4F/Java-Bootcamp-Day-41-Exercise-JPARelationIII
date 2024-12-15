package com.example.schoolsystem.Service;

import com.example.schoolsystem.ApiResponse.ApiException;
import com.example.schoolsystem.InDTO.AddressDTO;
import com.example.schoolsystem.Model.Address;
import com.example.schoolsystem.Model.Teacher;
import com.example.schoolsystem.Repository.AddressRepository;
import com.example.schoolsystem.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public List<com.example.schoolsystem.OutDTO.AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<com.example.schoolsystem.OutDTO.AddressDTO> addressDTOS = new ArrayList<>();

        for (Address a : addresses)
            addressDTOS.add(new com.example.schoolsystem.OutDTO.AddressDTO(a.getArea(), a.getStreet(), a.getBuildingNumber()));

        return addressDTOS;
    }

    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());
        if (teacher == null)
            throw new ApiException("Teacher with ID: " + addressDTO.getTeacher_id() + " was not found");

        Address address = new Address(null, addressDTO.getArea(), addressDTO.getStreet(), addressDTO.getBuildingNumber(), teacher);
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO) {
        Address oldAddress = addressRepository.findAddressById(addressDTO.getTeacher_id());
        if (oldAddress == null)
            throw new ApiException("Address with ID: " + addressDTO.getTeacher_id() + " was not found");

        oldAddress.setArea(addressDTO.getArea());
        oldAddress.setStreet(addressDTO.getStreet());
        oldAddress.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(oldAddress);
    }

    public void deleteAddress(Integer id) {
        Address address = addressRepository.findAddressById(id);
        if (address == null)
            throw new ApiException("Address with ID: " + id + " was not found");

        addressRepository.delete(address);
    }
}
