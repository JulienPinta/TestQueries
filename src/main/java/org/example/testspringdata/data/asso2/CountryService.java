package org.example.testspringdata.data.asso2;

import jakarta.transaction.Transactional;

import java.util.List;

public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional
    public void printAll(){
        countryRepository.findAll();//.forEach(country -> System.out.println("got a country "+country.getName()+" and "+country.getCities().size()+" cities"));
    }

    @Transactional
    public List<Country> getAll(){
        return countryRepository.findAll();
    }
}
