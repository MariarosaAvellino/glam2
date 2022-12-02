package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.SubRedditDto;
import bcsoft.it.glam.mapper.SubRedditMapper;
import bcsoft.it.glam.model.SubReddit;
import bcsoft.it.glam.repository.SubRedditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubRedditService {

    private final SubRedditRepository subRedditRepository;
    private final SubRedditMapper subRedditMapper;

    public SubRedditDto save (SubRedditDto subRedditDto){
        SubReddit subReddit = subRedditMapper.mapDtoToSubReddit(subRedditDto);
        subRedditRepository.save(subReddit);
        subRedditDto.setId(subReddit.getId());
        return subRedditDto;

    }
}
