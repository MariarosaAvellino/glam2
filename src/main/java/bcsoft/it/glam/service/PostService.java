package bcsoft.it.glam.service;

import bcsoft.it.glam.dto.PostRequest;
import bcsoft.it.glam.dto.PostResponse;
import bcsoft.it.glam.exception.MyException;
import bcsoft.it.glam.mapper.PostMapper;
import bcsoft.it.glam.model.Post;
import bcsoft.it.glam.model.SubReddit;
import bcsoft.it.glam.model.Utente;
import bcsoft.it.glam.repository.PostRepository;
import bcsoft.it.glam.repository.SubRedditRepository;
import bcsoft.it.glam.repository.UtenteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubRedditRepository subredditRepository;
    private final UtenteRepository utenteRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        SubReddit subreddit = subredditRepository.findByNome(postRequest.getSubRedditName())
                .orElseThrow(() -> new MyException("SubReddit non trovato " + postRequest.getSubRedditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getUtenteCorrente()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new MyException("Post non trovato" + id.toString()));
        return postMapper.mapToDTO(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDTO)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        SubReddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new MyException("SubReddit non trovato " + subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubReddit(subreddit);
        return posts.stream().map(postMapper::mapToDTO).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        Utente user = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUtente(user)
                .stream()
                .map(postMapper::mapToDTO)
                .collect(toList());
    }
}