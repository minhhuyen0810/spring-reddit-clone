package com.company.springredditclone.service;

import com.company.springredditclone.dto.CommentsDto;
import com.company.springredditclone.exception.PostNotFoundException;
import com.company.springredditclone.exception.SpringRedditException;
import com.company.springredditclone.mapper.CommentMapper;
import com.company.springredditclone.model.Comment;
import com.company.springredditclone.model.NotificationEmail;
import com.company.springredditclone.model.Post;
import com.company.springredditclone.model.User;
import com.company.springredditclone.repository.CommentRepository;
import com.company.springredditclone.repository.PostRepository;
import com.company.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommentService {

    private static final String POST_URL ="";
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void createComment(CommentsDto commentsDto) throws SpringRedditException {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto,post,authService.getCurrentUser());
        commentRepository.save(comment);
        String message = mailContentBuilder.build(authService.getClass()+" posted a comment on your post." + POST_URL);
        sendCommentNotification(message,post.getUser());
    }

    private void sendCommentNotification(String message, User user) throws SpringRedditException {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }
    public List<CommentsDto> getAllCommentsForPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(Collectors.toList());
    }
    public List<CommentsDto> getAllCommentsForUser(String userName){
        User user = userRepository.findByUsername(userName)
                .orElseThrow(()-> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
