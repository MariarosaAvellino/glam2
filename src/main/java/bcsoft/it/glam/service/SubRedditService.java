package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.SubRedditDto;
import bcsoft.it.glam.exception.MyException;
import bcsoft.it.glam.mapper.SubRedditMapper;
import bcsoft.it.glam.model.SubReddit;
import bcsoft.it.glam.repository.SubRedditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubRedditService {

    private final SubRedditRepository subRedditRepository;
    private final SubRedditMapper subRedditMapper;

    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto) {
        SubReddit subReddit = subRedditMapper.mapDtoToSubReddit(subRedditDto);
        subRedditRepository.save(subReddit);
        subRedditDto.setId(subReddit.getId());
        return subRedditDto;

    }

    @Transactional
    public List<SubRedditDto> getAll() {
        return subRedditRepository
                .findAll()
                .stream()
                .map(subRedditMapper::mapSubRedditToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubRedditDto getById(long id ){
        return subRedditRepository.findById(id)
                .map(subRedditMapper::mapSubRedditToDto)
                .orElseThrow(()->new MyException("SubReddit non trovato con questo id" +id));
    }

}

