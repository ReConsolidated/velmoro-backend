package io.github.reconsolidated.velmorobackend.application;

import io.github.reconsolidated.velmorobackend.domain.hotel.Hotel;
import io.github.reconsolidated.velmorobackend.domain.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelByUrlName(String name) {
        return hotelRepository.findByUrlName(name);
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setDisplayName(hotelDetails.getDisplayName());
                    hotel.setUrlName(hotelDetails.getUrlName());
                    hotel.setEmailAddress(hotelDetails.getEmailAddress());
                    hotel.setWorkStartHour(hotelDetails.getWorkStartHour());
                    hotel.setWorkEndHour(hotelDetails.getWorkEndHour());
                    return hotelRepository.save(hotel);
                })
                .orElseThrow(() -> new RuntimeException("Hotel not found with id " + id));
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}