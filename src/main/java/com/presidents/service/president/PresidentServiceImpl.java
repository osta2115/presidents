package com.presidents.service.president;

import com.presidents.model.dto.PresidentDto;
import com.presidents.model.mapper.PresidentMapper;
import com.presidents.repository.PresidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
public class PresidentServiceImpl implements PresidentService{

    private final PresidentsRepository presidentsRepository;

    @Override
    public List<PresidentDto> getAllPresidents() {
        return presidentsRepository.findAll().stream()
                .map(PresidentMapper::toDto)
                .toList();
    }

    @Override
    public PresidentDto savePresident(PresidentDto presidentDto) {

        return PresidentMapper.toDto(presidentsRepository.save(PresidentMapper.toEntity(presidentDto)));
    }

    @Override
    public PresidentDto updatePresident(PresidentDto presidentDto) {
        presidentsRepository.findById(presidentDto.getId()).map(president -> {
            president.setName(presidentDto.getName());
            president.setSurname(presidentDto.getSurname());
            president.setTermFrom(presidentDto.getTermFrom());
            president.setTermTo(presidentDto.getTermTo());
            president.setPoliticalParty(presidentDto.getPoliticalParty());
            return president;
        });
        return PresidentMapper.toDto(presidentsRepository.getReferenceById(presidentDto.getId()));
    }

    @Override
    public PresidentDto updatePresidentPartial(PresidentDto presidentDto) {
       presidentsRepository.findById(presidentDto.getId()).map(president -> {
            if (nonNull(presidentDto.getName())){
                president.setName(presidentDto.getName());
            }
            if (nonNull(presidentDto.getSurname())){
                president.setSurname(presidentDto.getSurname());
            }
            if (nonNull(presidentDto.getTermFrom())){
                president.setTermFrom(presidentDto.getTermFrom());
            }
            if (nonNull(presidentDto.getTermTo())){
                president.setTermTo(presidentDto.getTermTo());
            }
            if (nonNull(presidentDto.getPoliticalParty())){
                president.setPoliticalParty(presidentDto.getPoliticalParty());
            }
            return president;
        });
        return PresidentMapper.toDto((presidentsRepository.getReferenceById(presidentDto.getId())));
    }

    @Override
    public void deletePresident(Long id) {
        presidentsRepository.deleteById(id);
    }
}
