package com.wolverhampton.campushub.service;

import com.wolverhampton.campushub.dto.AppDTO.TransportDTO;
import com.wolverhampton.campushub.entity.TransportSchedule;
import com.wolverhampton.campushub.repository.TransportScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportService {

    @Autowired
    private TransportScheduleRepository transportRepository;

    public List<TransportDTO> getAll() {
        return transportRepository.findByActive(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<TransportDTO> getAllAdmin() {
        return transportRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<TransportDTO> getByRoute(String from, String to) {
        return transportRepository.findByFromCampusAndToCampus(from, to)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TransportDTO create(TransportDTO dto) {
        TransportSchedule t = new TransportSchedule();
        mapToEntity(dto, t);
        return toDTO(transportRepository.save(t));
    }

    public TransportDTO update(Long id, TransportDTO dto) {
        TransportSchedule t = transportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        mapToEntity(dto, t);
        return toDTO(transportRepository.save(t));
    }

    public void delete(Long id) {
        transportRepository.deleteById(id);
    }

    private void mapToEntity(TransportDTO dto, TransportSchedule t) {
        t.setFromCampus(dto.getFromCampus());
        t.setToCampus(dto.getToCampus());
        t.setDepartureTime(dto.getDepartureTime());
        t.setArrivalTime(dto.getArrivalTime());
        t.setFrequency(dto.getFrequency());
        t.setDaysOfOperation(dto.getDaysOfOperation());
        t.setRouteInfo(dto.getRouteInfo());
        t.setActive(dto.isActive());
        t.setAlertMessage(dto.getAlertMessage());
    }

    private TransportDTO toDTO(TransportSchedule t) {
        TransportDTO dto = new TransportDTO();
        dto.setId(t.getId());
        dto.setFromCampus(t.getFromCampus());
        dto.setToCampus(t.getToCampus());
        dto.setDepartureTime(t.getDepartureTime());
        dto.setArrivalTime(t.getArrivalTime());
        dto.setFrequency(t.getFrequency());
        dto.setDaysOfOperation(t.getDaysOfOperation());
        dto.setRouteInfo(t.getRouteInfo());
        dto.setActive(t.isActive());
        dto.setAlertMessage(t.getAlertMessage());
        return dto;
    }
}
